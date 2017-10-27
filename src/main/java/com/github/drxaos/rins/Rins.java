package com.github.drxaos.rins;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class Rins {

    public final static class Instruction {
        private static final int SHIFT = 1000;
        private static final int INDIRECT = 10000;
        private static final Map<Integer, Instruction> MAP = new HashMap<>();

        public static final Instruction GET = create(1, "GET");
        public static final Instruction SET = create(2, "SET");
        public static final Instruction ADD = create(3, "ADD");
        public static final Instruction SUB = create(4, "SUB");
        public static final Instruction INC = create(5, "INC");
        public static final Instruction DEC = create(6, "DEC");
        public static final Instruction JMP = create(7, "JMP");
        public static final Instruction JIZ = create(8, "JIZ");
        public static final Instruction JIN = create(9, "JIN");
        public static final Instruction HLT = create(10, "HLT");

        private int value;
        private String mnemonic;

        private Instruction(int value, String mnemonic) {
            this.value = value;
            this.mnemonic = mnemonic;
        }

        private static Instruction create(int number, String mnemonic) {
            Instruction instruction = new Instruction(number * SHIFT, mnemonic);
            Instruction indirect = new Instruction(number * SHIFT + INDIRECT, mnemonic + "P");
            MAP.put(instruction.value, instruction);
            MAP.put(indirect.value, indirect);
            return instruction;
        }

        public String getMnemonic() {
            return mnemonic;
        }

        public static int minPointer() {
            return 0;
        }

        public static int maxPointer() {
            return SHIFT - 1;
        }

        public Instruction toIndirect() {
            return MAP.get(isDirect() ? this.value + INDIRECT : this.value);
        }

        public Instruction toDirect() {
            return MAP.get(isDirect() ? this.value : this.value - INDIRECT);
        }

        public static Instruction get(int value) {
            Instruction i = MAP.get(value / SHIFT * SHIFT);
            if (i == null) {
                i = HLT;
            }
            return i;
        }

        public int withPointer(int pointer) {
            return this.value + pointer;
        }

        public static int arg(int value) {
            return value % SHIFT;
        }

        public boolean isDirect() {
            return this.value - INDIRECT < 0;
        }

        public boolean isIndirect() {
            return this.value - INDIRECT >= 0;
        }
    }

    private int[] memory;
    private int register;
    private int cursor;

    public Rins(int size) throws IllegalArgumentException {
        if (size > Instruction.maxPointer() || size <= Instruction.minPointer()) {
            throw new IllegalArgumentException("size must be " + Instruction.minPointer() + ".." + Instruction.maxPointer());
        }
        memory = new int[size];
        register = 0;
        cursor = 0;
    }

    public int getSize() {
        return memory.length;
    }

    public int cycleAddr(int addr) {
        return addr % getSize() + (addr < 0 ? getSize() : 0);
    }

    public synchronized void set(int addr, int value) {
        memory[cycleAddr(addr)] = value;
    }

    public synchronized void set(int addr, Instruction instruction, int pointer) {
        memory[cycleAddr(addr)] = instruction.withPointer(pointer);
    }

    public synchronized int get(int addr) {
        return memory[cycleAddr(addr)];
    }

    public synchronized Instruction getIns(int addr) {
        return Instruction.get(memory[cycleAddr(addr)]);
    }

    public synchronized int getArg(int addr) {
        return Instruction.arg(memory[cycleAddr(addr)]);
    }

    public synchronized int getCursor() {
        return cursor;
    }

    public synchronized int getRegister() {
        return register;
    }

    public synchronized void step() {
        Instruction ins = getIns(cursor);
        int pointer = getArg(cursor);

        if (ins.isIndirect()) {
            pointer = get(pointer);
            ins = ins.toDirect();
        }

        if (Instruction.HLT == ins) {
            return;
        }

        if (Instruction.GET == ins) {
            register = get(pointer);
        }

        if (Instruction.SET == ins) {
            set(pointer, register);
        }

        if (Instruction.ADD == ins) {
            register = register + get(pointer);
        }

        if (Instruction.SUB == ins) {
            register = register - get(pointer);
        }

        if (Instruction.INC == ins) {
            set(pointer, get(pointer) + 1);
        }

        if (Instruction.DEC == ins) {
            set(pointer, get(pointer) - 1);
        }

        cursor = cycleAddr(cursor + 1);

        if (Instruction.JMP == ins) {
            cursor = cycleAddr(pointer);
        }

        if (Instruction.JIZ == ins) {
            cursor = register == 0 ? cycleAddr(pointer) : cursor;
        }

        if (Instruction.JIN == ins) {
            cursor = register < 0 ? cycleAddr(pointer) : cursor;
        }
    }

    public synchronized byte[] toByteArray() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(4 * (memory.length + 2));
        buffer.putInt(cursor);
        buffer.putInt(register);
        for (int value : memory) {
            buffer.putInt(value);
        }
        buffer.clear();
        return buffer.array();
    }

    public synchronized void writeTo(OutputStream out) throws IOException {
        out.write(toByteArray());
    }

    public static Rins readFrom(InputStream in) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = in.read(data, 0, data.length)) != -1) {
            baos.write(data, 0, nRead);
        }
        baos.flush();
        return fromByteArray(baos.toByteArray());
    }

    public static Rins fromByteArray(byte[] bytes) throws IOException {
        Rins rins = new Rins(bytes.length / 4 - 2);
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        rins.cursor = buffer.getInt();
        rins.register = buffer.getInt();
        for (int i = 0; i < rins.memory.length; i++) {
            rins.memory[i] = buffer.getInt();
        }
        return rins;
    }
}
