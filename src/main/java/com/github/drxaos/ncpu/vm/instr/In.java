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

public class In implements NanoExecution {
    public static final In IMPL_II = new In(Indirect.IMPL, Indirect.IMPL);
    public static final In IMPL_ID = new In(Indirect.IMPL, Direct.IMPL);
    public static final In IMPL_IL = new In(Indirect.IMPL, Literal.IMPL);
    public static final In IMPL_IA = new In(Indirect.IMPL, VarA.IMPL);
    public static final In IMPL_DI = new In(Direct.IMPL, Indirect.IMPL);
    public static final In IMPL_DD = new In(Direct.IMPL, Direct.IMPL);
    public static final In IMPL_DL = new In(Direct.IMPL, Literal.IMPL);
    public static final In IMPL_DA = new In(Direct.IMPL, VarA.IMPL);
    public static final In IMPL_FI = new In(Flags.IMPL, Indirect.IMPL);
    public static final In IMPL_FD = new In(Flags.IMPL, Direct.IMPL);
    public static final In IMPL_FL = new In(Flags.IMPL, Literal.IMPL);
    public static final In IMPL_FA = new In(Flags.IMPL, VarA.IMPL);
    public static final In IMPL_AI = new In(VarA.IMPL, Indirect.IMPL);
    public static final In IMPL_AD = new In(VarA.IMPL, Direct.IMPL);
    public static final In IMPL_AL = new In(VarA.IMPL, Literal.IMPL);
    public static final In IMPL_AA = new In(VarA.IMPL, VarA.IMPL);

    private final Addressing target;
    private final Addressing port;

    public In(Addressing target, Addressing port) {
        this.target = target;
        this.port = port;
    }

    public String getName() {
        return "IN";
    }

    @Override
    public void execute(NanoCpu nanoCpu, int pc, byte ins, NanoMemory mem, NanoIo io) {
        final int targetAddr = target.load(mem);
        final int portAddr = port.load(mem);
        final byte data = io.read(portAddr);
        target.write(mem, targetAddr, data);
        mem.writeFlags(flags(data, false));
        mem.incPc();
        nanoCpu.afterExecution(pc, mem.pc() - pc, this, ins, target, targetAddr, port, portAddr);
    }
}
