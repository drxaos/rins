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

public class Inc implements NanoExecution {
    public static final Inc IMPL_I = new Inc(Indirect.IMPL);
    public static final Inc IMPL_D = new Inc(Direct.IMPL);
    public static final Inc IMPL_F = new Inc(Flags.IMPL);
    public static final Inc IMPL_A = new Inc(VarA.IMPL);

    private final Addressing target;

    public Inc(Addressing target) {
        this.target = target;
    }

    public String getName() {
        return "INC";
    }

    @Override
    public void execute(NanoCpu nanoCpu, int pc, byte ins, NanoMemory mem, NanoIo io) {
        final int targetAddr = target.load(mem);
        final byte data = target.read(mem, targetAddr);
        final byte result = (byte) (data + 1);
        target.write(mem, targetAddr, result);
        mem.writeFlags(flags(result, result == 0));
        mem.incPc();
        nanoCpu.afterExecution(pc, mem.pc() - pc, this, ins, target, targetAddr, null, 0);
    }
}
