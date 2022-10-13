package com.github.drxaos.ncpu.vm.instr;

import com.github.drxaos.ncpu.vm.NanoCpu;
import com.github.drxaos.ncpu.vm.NanoExecution;
import com.github.drxaos.ncpu.vm.NanoIo;
import com.github.drxaos.ncpu.vm.NanoMemory;
import com.github.drxaos.ncpu.vm.addr.Literal;

public class Jmp implements NanoExecution {
    public static final Jmp IMPL = new Jmp();

    public String getName() {
        return "JMP";
    }

    public boolean condition(NanoMemory mem) {
        return true;
    }

    @Override
    public void execute(NanoCpu nanoCpu, int pc, byte ins, NanoMemory mem, NanoIo io) {
        final int targetAddr = mem.incPcAndReadData();
        if (condition(mem)) {
            if (targetAddr >= mem.A) {
                mem.write(mem.PC, mem.read(mem.A));
            } else {
                mem.write(mem.PC, targetAddr);
            }
        } else {
            mem.incPc();
        }
        nanoCpu.afterExecution(pc, mem.pc() - pc, this, ins, Literal.IMPL, targetAddr & 0xFF, null, 0);
    }
}
