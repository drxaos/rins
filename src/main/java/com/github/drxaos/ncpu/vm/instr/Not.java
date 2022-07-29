package com.github.drxaos.ncpu.vm.instr;

import com.github.drxaos.ncpu.vm.Addressing;
import com.github.drxaos.ncpu.vm.NanoCpu;
import com.github.drxaos.ncpu.vm.NanoExecution;
import com.github.drxaos.ncpu.vm.NanoIo;
import com.github.drxaos.ncpu.vm.NanoMemory;
import com.github.drxaos.ncpu.vm.addr.Direct;
import com.github.drxaos.ncpu.vm.addr.Flags;
import com.github.drxaos.ncpu.vm.addr.Indirect;
import com.github.drxaos.ncpu.vm.addr.VarA;

public class Not implements NanoExecution {
    public static final Not IMPL_I = new Not(Indirect.IMPL);
    public static final Not IMPL_D = new Not(Direct.IMPL);
    public static final Not IMPL_F = new Not(Flags.IMPL);
    public static final Not IMPL_A = new Not(VarA.IMPL);

    private final Addressing target;

    public Not(Addressing target) {
        this.target = target;
    }

    public String getName() {
        return "NOT";
    }

    @Override
    public void execute(NanoCpu nanoCpu, int pc, byte ins, NanoMemory mem, NanoIo io) {
        final int targetAddr = target.load(mem);
        final byte data = target.read(mem, targetAddr);
        final byte result = (byte) (~data);
        target.write(mem, targetAddr, result);
        mem.writeFlags(flags(result, false));
        mem.incPc();
        nanoCpu.afterExecution(pc, mem.pc() - pc, this, ins, target, targetAddr, null, 0);
    }
}
