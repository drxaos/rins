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

public class And implements NanoExecution {
    public static final And IMPL_II = new And(Indirect.IMPL, Indirect.IMPL);
    public static final And IMPL_ID = new And(Indirect.IMPL, Direct.IMPL);
    public static final And IMPL_IL = new And(Indirect.IMPL, Literal.IMPL);
    public static final And IMPL_IA = new And(Indirect.IMPL, VarA.IMPL);

    public static final And IMPL_DI = new And(Direct.IMPL, Indirect.IMPL);
    public static final And IMPL_DD = new And(Direct.IMPL, Direct.IMPL);
    public static final And IMPL_DL = new And(Direct.IMPL, Literal.IMPL);
    public static final And IMPL_DA = new And(Direct.IMPL, VarA.IMPL);

    public static final And IMPL_FI = new And(Flags.IMPL, Indirect.IMPL);
    public static final And IMPL_FD = new And(Flags.IMPL, Direct.IMPL);
    public static final And IMPL_FL = new And(Flags.IMPL, Literal.IMPL);
    public static final And IMPL_FA = new And(Flags.IMPL, VarA.IMPL);

    public static final And IMPL_AI = new And(VarA.IMPL, Indirect.IMPL);
    public static final And IMPL_AD = new And(VarA.IMPL, Direct.IMPL);
    public static final And IMPL_AL = new And(VarA.IMPL, Literal.IMPL);
    public static final And IMPL_AA = new And(VarA.IMPL, VarA.IMPL);

    private final Addressing target;
    private final Addressing operand;

    public And(Addressing target, Addressing operand) {
        this.target = target;
        this.operand = operand;
    }

    public String getName() {
        return "AND";
    }

    @Override
    public void execute(NanoCpu nanoCpu, int pc, byte ins, NanoMemory mem, NanoIo io) {
        final int targetAddr = target.load(mem);
        final int operandAddr = operand.load(mem);
        final byte data1 = target.read(mem, targetAddr);
        final byte data2 = operand.read(mem, operandAddr);
        final byte result = (byte) (data1 & data2);
        target.write(mem, targetAddr, result);
        mem.writeFlags(flags(result, false));
        mem.incPc();
        nanoCpu.afterExecution(pc, mem.pc() - pc, this, ins, target, targetAddr, operand, operandAddr);
    }
}
