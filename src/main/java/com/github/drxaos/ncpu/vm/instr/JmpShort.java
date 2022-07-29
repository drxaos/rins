package com.github.drxaos.ncpu.vm.instr;

import com.github.drxaos.ncpu.vm.NanoCpu;
import com.github.drxaos.ncpu.vm.NanoExecution;
import com.github.drxaos.ncpu.vm.NanoIo;
import com.github.drxaos.ncpu.vm.NanoMemory;
import com.github.drxaos.ncpu.vm.addr.Literal;

public class JmpShort implements NanoExecution {
    public static final JmpShort IMPL = new JmpShort();

    public String getName() {
        return "JMP";
    }

    public boolean condition(NanoMemory mem) {
        return true;
    }

    @Override
    public void execute(NanoCpu nanoCpu, int pc, byte ins, NanoMemory mem, NanoIo io) {
        final byte shift = (byte) ((byte) ((ins & 0xF) << 4) >> 4);
        if (condition(mem)) {
            mem.write(mem.PC, pc + shift);
        } else {
            mem.incPc();
        }
        nanoCpu.afterExecution(pc, mem.pc() - pc, this, ins, Literal.IMPL, (pc + shift) & 0xFF, null, 0);
    }
}
