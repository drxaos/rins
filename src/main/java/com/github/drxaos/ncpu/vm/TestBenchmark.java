package com.github.drxaos.ncpu.vm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class TestBenchmark {
    public static void main(String[] args) {
        List<NanoCpu> cpus = new ArrayList<>();

        Random rnd = new Random();
        for (int i = 0; i < 50000; i++) {
            int port = 22 + rnd.nextInt(50);
            int x = 45 + rnd.nextInt(50);
            int add = 5 + rnd.nextInt(50);
            int out = 99;

            final NanoCpu cpu = makeCpu(port, x, add, out);
            cpus.add(cpu);
        }

        final ForkJoinPool pool = new ForkJoinPool(4);

        for (int i = 0; i < 100; i++) {
            final long start = System.currentTimeMillis();
            cpus.stream().map(c -> pool.submit((() -> c.cycle(100)))).toList().forEach(ForkJoinTask::join);
            final long end = System.currentTimeMillis();
            System.out.println(end - start);
        }
    }

    private static NanoCpu makeCpu(int port, int x, int add, int out) {
        final NanoMemory memory = new NanoMemory();
        final NanoIo io = new NanoIo();
        final Random rnd = new Random();
        for (int i = 0; i < 256; i++) {
            io.setInput(i, (byte) rnd.nextInt());
        }
        final NanoCpu cpu = new NanoCpu(memory, io);

        int pc = 0;
        memory.write(pc++, NanoInstruction.IN_AL.getCode());
        memory.write(pc++, port);

        memory.write(pc++, NanoInstruction.ADD_AL.getCode());
        memory.write(pc++, add);

        memory.write(pc++, NanoInstruction.MOV_DA.getCode());
        memory.write(pc++, x);

        memory.write(pc++, NanoInstruction.OUT_LD.getCode());
        memory.write(pc++, port);
        memory.write(pc++, x);

        memory.write(pc++, NanoInstruction.OUT_LL.getCode());
        memory.write(pc++, port + 1);
        memory.write(pc++, out);

        memory.write(pc++, NanoInstruction.MOV_DL.getCode());
        memory.write(pc++, x);
        memory.write(pc++, 0);

        memory.write(pc++, NanoInstruction.MOV_AL.getCode());
        memory.write(pc++, 0);

        memory.write(pc++, NanoInstruction.JMP.getCode());
        memory.write(pc++, 0);

        return cpu;
    }
}
