package com.github.drxaos.ncpu.vm;

import com.github.drxaos.ncpu.vm.trace.TraceOperation;

public class NanoCpu {
    NanoMemory mem;
    NanoIo io;

    private boolean halted;
    private boolean debug;

    public NanoCpu(NanoMemory mem, NanoIo io) {
        this.mem = mem;
        this.io = io;
    }

    public void cycle(long count) {
        for (int i = 0; i < count; i++) {
            tick();
        }
    }

    void cycleUntil(byte ins) {
        while (tick() != ins) ;
    }

    byte tick() {
        if (!halted) {
            final byte programCounter = mem.read(mem.PC);
            final byte instruction = mem.read(programCounter);
            NanoInstruction.execute(this, programCounter, instruction, mem, io);
            if ((instruction & 0xFF) == NanoInstruction.HALT.getCode()) {
                halted = true;
            }
            return instruction;
        } else {
            return (byte) NanoInstruction.HALT.getCode();
        }
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void afterExecution(
            int pc,
            int length,
            NanoExecution execution,
            byte ins,
            Addressing target,
            int targetAddr,
            Addressing operand,
            int operandAddr
    ) {
        if (debug) {
            final TraceOperation ast = execution.trace(ins, target, targetAddr, operand, operandAddr);
            final StringBuilder sb = new StringBuilder();
            sb.append(String.format("%02x", pc & 0xFF));
            sb.append(": ");
            sb.append(ast.getInstruction().getName());
            if (ast.getOperand1() != null) {
                sb.append(" ");
                sb.append(ast.getOperand1().getName());
            }
            if (ast.getOperand2() != null) {
                sb.append(" ");
                sb.append(ast.getOperand2().getName());
            }
            System.out.println(sb);
        }
    }
}
