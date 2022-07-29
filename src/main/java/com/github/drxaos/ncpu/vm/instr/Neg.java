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

public class Neg implements NanoExecution {
    public static final Neg IMPL_I = new Neg(Indirect.IMPL);
    public static final Neg IMPL_D = new Neg(Direct.IMPL);
    public static final Neg IMPL_F = new Neg(Flags.IMPL);
    public static final Neg IMPL_A = new Neg(VarA.IMPL);

    private final Addressing target;

    public Neg(Addressing target) {
        this.target = target;
    }

    public String getName() {
        return "NEG";
    }

    @Override
    public void execute(NanoCpu nanoCpu, int pc, byte ins, NanoMemory mem, NanoIo io) {
        final int targetAddr = target.load(mem);
        final byte data = target.read(mem, targetAddr);
        final byte result = (byte) (-data);
        target.write(mem, targetAddr, result);
        mem.writeFlags(flags(result, false));
        mem.incPc();
        nanoCpu.afterExecution(pc, mem.pc() - pc, this, ins, target, targetAddr, null, 0);
    }
}
