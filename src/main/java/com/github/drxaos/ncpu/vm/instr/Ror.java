package com.github.drxaos.ncpu.vm.instr;

import com.github.drxaos.ncpu.vm.Addressing;
import com.github.drxaos.ncpu.vm.NanoCpu;
import com.github.drxaos.ncpu.vm.NanoExecution;
import com.github.drxaos.ncpu.vm.NanoIo;
import com.github.drxaos.ncpu.vm.NanoMemory;
import com.github.drxaos.ncpu.vm.addr.Direct;
import com.github.drxaos.ncpu.vm.addr.Flags;
import com.github.drxaos.ncpu.vm.addr.Indirect;
import com.github.drxaos.ncpu.vm.addr.Literal;
import com.github.drxaos.ncpu.vm.addr.VarA;

public class Ror implements NanoExecution {
    public static final Ror IMPL_I = new Ror(Indirect.IMPL);
    public static final Ror IMPL_D = new Ror(Direct.IMPL);
    public static final Ror IMPL_F = new Ror(Flags.IMPL);
    public static final Ror IMPL_A = new Ror(VarA.IMPL);

    private final Addressing target;

    public Ror(Addressing target) {
        this.target = target;
    }

    public String getName() {
        return "ROR";
    }

    @Override
    public void execute(NanoCpu nanoCpu, int pc, byte ins, NanoMemory mem, NanoIo io) {
        final int targetAddr = target.load(mem);
        final byte data = target.read(mem, targetAddr);
        final byte count = mem.incPcAndReadData();
        final byte result = (byte) (((data & 0xff) >>> count) | ((data & 0xff) << (8 - count)));
        target.write(mem, targetAddr, result);
        mem.writeFlags(flags(result, false));
        mem.incPc();
        nanoCpu.afterExecution(pc, mem.pc() - pc, this, ins, target, targetAddr, Literal.IMPL, count);
    }
}
