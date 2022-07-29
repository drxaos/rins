package com.github.drxaos.ncpu.vm;

import java.util.ArrayList;
import java.util.List;

public class TestMul {
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    /*

    XOR A A
    IN X 1
    IN Y 2
    loop:
    JIZ end
    ADD A X
    DEC Y
    JMP loop
    end:
    OUT 3 A

    A = 0
    X <= 1
    Y <= 2
    loop:
    IF zero GOTO end
    A += X
    Y -= 1
    GOTO loop
    end:
    A => 3

    while(Y>0)

     */

    public static void main(String[] args) {
        final NanoMemory memory = new NanoMemory();
        final NanoIo io = new NanoIo();
        io.setDebug(true);
        io.setInput(1, (byte) 7);
        io.setInput(2, (byte) 9);
        final NanoCpu cpu = new NanoCpu(memory, io);
        cpu.setDebug(true);

        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        List<Integer> end = new ArrayList<>();
        List<Integer> loop = new ArrayList<>();

        int pc = 0;
        memory.write(pc++, NanoInstruction.XOR_AA.getCode());
        memory.write(pc++, NanoInstruction.IN_DL.getCode());
        x.add(pc++);
        memory.write(pc++, 1);
        memory.write(pc++, NanoInstruction.IN_DL.getCode());
        y.add(pc++);
        memory.write(pc++, 2);
        memory.write(pc++, NanoInstruction.AND_DL.getCode());
        x.add(pc++);
        memory.write(pc++, 0b1111);
        memory.write(pc++, NanoInstruction.AND_DL.getCode());
        y.add(pc++);
        memory.write(pc++, 0b1111);
        memory.write(pc++, NanoInstruction.OUT_LD.getCode());
        memory.write(pc++, 3);
        x.add(pc++);
        memory.write(pc++, NanoInstruction.OUT_LD.getCode());
        memory.write(pc++, 3);
        y.add(pc++);
        int loopPc = pc;
        memory.write(pc++, NanoInstruction.JIZ.getCode());
        end.add(pc++);
        memory.write(pc++, NanoInstruction.ADD_AD.getCode());
        x.add(pc++);
        memory.write(pc++, NanoInstruction.DEC_D.getCode());
        y.add(pc++);
        memory.write(pc++, NanoInstruction.JMP.getCode());
        loop.add(pc++);
        int endPc = pc;
        memory.write(pc++, NanoInstruction.OUT_LA.getCode());
        memory.write(pc++, 3);
        memory.write(pc++, NanoInstruction.HALT.getCode());
        int xPc = pc++;
        int yPc = pc++;

        x.forEach(a -> memory.write(a, xPc));
        y.forEach(a -> memory.write(a, yPc));
        end.forEach(a -> memory.write(a, endPc));
        loop.forEach(a -> memory.write(a, loopPc));

        System.out.println("program length: " + pc);
        System.out.println(bytesToHex(memory.block));
        final long startTime = System.nanoTime();
        cpu.cycleUntil((byte) NanoInstruction.HALT.getCode());
        final long endTime = System.nanoTime();
        System.out.println(bytesToHex(memory.block));
        System.out.println(endTime - startTime);
    }
}
