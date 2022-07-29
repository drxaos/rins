package com.github.drxaos.ncpu.vm;

import com.github.drxaos.ncpu.vm.trace.TraceInstruction;
import com.github.drxaos.ncpu.vm.trace.TraceOperand;
import com.github.drxaos.ncpu.vm.trace.TraceOperation;

public interface NanoExecution {
    void execute(NanoCpu nanoCpu, int pc, byte ins, NanoMemory memory, NanoIo io);

    default byte flags(byte data, boolean overflow) {
        return (byte) (
                (data == 0
                        ? 0b001
                        : 0) |
                (data < 0
                        ? 0b010
                        : 0) |
                (overflow
                        ? 0b100
                        : 0)
        );
    }

    String getName();

    default TraceOperation trace(byte ins, Addressing target, int targetAddr, Addressing operand, int operandAddr) {
        return new TraceOperation(
                new TraceInstruction(ins, this),
                target != null
                        ? new TraceOperand(targetAddr, target)
                        : null,
                operand != null
                        ? new TraceOperand(operandAddr, operand)
                        : null
        );
    }
}
