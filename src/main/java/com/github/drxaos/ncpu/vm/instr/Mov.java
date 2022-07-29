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

public class Mov implements NanoExecution {
    public static final Mov IMPL_II = new Mov(Indirect.IMPL, Indirect.IMPL);
    public static final Mov IMPL_ID = new Mov(Indirect.IMPL, Direct.IMPL);
    public static final Mov IMPL_IL = new Mov(Indirect.IMPL, Literal.IMPL);
    public static final Mov IMPL_IA = new Mov(Indirect.IMPL, VarA.IMPL);

    public static final Mov IMPL_DI = new Mov(Direct.IMPL, Indirect.IMPL);
    public static final Mov IMPL_DD = new Mov(Direct.IMPL, Direct.IMPL);
    public static final Mov IMPL_DL = new Mov(Direct.IMPL, Literal.IMPL);
    public static final Mov IMPL_DA = new Mov(Direct.IMPL, VarA.IMPL);

    public static final Mov IMPL_FI = new Mov(Flags.IMPL, Indirect.IMPL);
    public static final Mov IMPL_FD = new Mov(Flags.IMPL, Direct.IMPL);
    public static final Mov IMPL_FL = new Mov(Flags.IMPL, Literal.IMPL);
    public static final Mov IMPL_FA = new Mov(Flags.IMPL, VarA.IMPL);

    public static final Mov IMPL_AI = new Mov(VarA.IMPL, Indirect.IMPL);
    public static final Mov IMPL_AD = new Mov(VarA.IMPL, Direct.IMPL);
    public static final Mov IMPL_AL = new Mov(VarA.IMPL, Literal.IMPL);
    public static final Mov IMPL_AA = new Mov(VarA.IMPL, VarA.IMPL);

    private final Addressing target;
    private final Addressing operand;

    public Mov(Addressing target, Addressing operand) {
        this.target = target;
        this.operand = operand;
    }

    public String getName() {
        return "MOV";
    }

    @Override
    public void execute(NanoCpu nanoCpu, int pc, byte ins, NanoMemory mem, NanoIo io) {
        final int targetAddr = target.load(mem);
        final int operandAddr = operand.load(mem);
        final byte data = operand.read(mem, operandAddr);
        target.write(mem, targetAddr, data);
        mem.writeFlags(flags(data, false));
        mem.incPc();
        nanoCpu.afterExecution(pc, mem.pc() - pc, this, ins, target, targetAddr, operand, operandAddr);
    }
}
