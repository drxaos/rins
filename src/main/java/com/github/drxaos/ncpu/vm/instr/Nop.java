package com.github.drxaos.ncpu.vm.instr;

import com.github.drxaos.ncpu.vm.NanoCpu;
import com.github.drxaos.ncpu.vm.NanoExecution;
import com.github.drxaos.ncpu.vm.NanoIo;
import com.github.drxaos.ncpu.vm.NanoMemory;

public class Nop implements NanoExecution {
    public static final Nop IMPL = new Nop();

    public String getName() {
        return "NOP";
    }

    @Override
    public void execute(NanoCpu nanoCpu, int pc, byte ins, NanoMemory mem, NanoIo io) {
        mem.incPc();
        nanoCpu.afterExecution(pc, mem.pc() - pc, this, ins, null, 0, null, 0);
    }
}
