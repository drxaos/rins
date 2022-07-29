package com.github.drxaos.ncpu.vm;

public class Test {
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

    public static void main(String[] args) {
        final NanoMemory memory = new NanoMemory();
        final NanoIo io = new NanoIo();
        io.setDebug(true);
        io.setInput(22, (byte) 100);
        final NanoCpu cpu = new NanoCpu(memory, io);
        cpu.setDebug(true);

        int port = 22;
        int x = 25;

        int pc = 0;
        memory.write(pc++, NanoInstruction.IN_AL.getCode()); // IN A 22
        memory.write(pc++, port);

        memory.write(pc++, NanoInstruction.ADD_AL.getCode()); // ADD A 5
        memory.write(pc++, 5);

        memory.write(pc++, NanoInstruction.MOV_DA.getCode()); // MOV X A
        memory.write(pc++, x);

        memory.write(pc++, NanoInstruction.OUT_LD.getCode()); // OUT 22 X
        memory.write(pc++, port);
        memory.write(pc++, x);

        memory.write(pc++, NanoInstruction.OUT_LL.getCode()); // OUT 22 99
        memory.write(pc++, port);
        memory.write(pc++, 99);

        memory.write(pc++, NanoInstruction.MOV_DL.getCode()); // MOV [0] 0xFF
        memory.write(pc++, 0);
        memory.write(pc++, NanoInstruction.HALT.getCode());

        memory.write(pc++, NanoInstruction.HALT.getCode()); // HALT

        System.out.println("program length: " + pc);
        System.out.println(bytesToHex(memory.block));
        final long start = System.nanoTime();
        cpu.cycle(10);
        final long end = System.nanoTime();
        System.out.println(bytesToHex(memory.block));
        System.out.println(end - start);
    }
}
