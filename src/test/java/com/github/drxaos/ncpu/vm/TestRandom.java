package com.github.drxaos.ncpu.vm;

import java.util.Random;

public class TestRandom {
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
        final NanoCpu cpu = new NanoCpu(memory, io);
        cpu.setDebug(true);

        final Random rnd = new Random();
        for (int i = 0; i < 256; i++) {
            io.setInput(i, (byte) rnd.nextInt());
        }
        for (int i = 0; i < 255; i++) {
            memory.write(i, (byte) rnd.nextInt());
        }

        System.out.println(bytesToHex(memory.block));
        final long start = System.nanoTime();
        cpu.cycle(1000);
        final long end = System.nanoTime();
        System.out.println(bytesToHex(memory.block));
        System.out.println(end - start);
    }
}
