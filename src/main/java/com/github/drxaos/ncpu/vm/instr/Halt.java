package com.github.drxaos.ncpu.vm.instr;

import com.github.drxaos.ncpu.vm.NanoCpu;
import com.github.drxaos.ncpu.vm.NanoExecution;
import com.github.drxaos.ncpu.vm.NanoIo;
import com.github.drxaos.ncpu.vm.NanoMemory;

public class Halt implements NanoExecution {
    public static final Halt IMPL = new Halt();

    public String getName() {
        return "HALT";
    }

    @Override
    public void execute(NanoCpu nanoCpu, int pc, byte ins, NanoMemory mem, NanoIo io) {
        mem.write(mem.PC, 0);
        mem.write(mem.FLAGS, 0);
        nanoCpu.afterExecution(pc, mem.pc() - pc, this, ins, null, 0, null, 0);
    }
}
