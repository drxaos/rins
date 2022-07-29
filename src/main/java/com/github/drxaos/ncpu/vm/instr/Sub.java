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

public class Sub implements NanoExecution {
    public static final Sub IMPL_II = new Sub(Indirect.IMPL, Indirect.IMPL);
    public static final Sub IMPL_ID = new Sub(Indirect.IMPL, Direct.IMPL);
    public static final Sub IMPL_IL = new Sub(Indirect.IMPL, Literal.IMPL);
    public static final Sub IMPL_IA = new Sub(Indirect.IMPL, VarA.IMPL);

    public static final Sub IMPL_DI = new Sub(Direct.IMPL, Indirect.IMPL);
    public static final Sub IMPL_DD = new Sub(Direct.IMPL, Direct.IMPL);
    public static final Sub IMPL_DL = new Sub(Direct.IMPL, Literal.IMPL);
    public static final Sub IMPL_DA = new Sub(Direct.IMPL, VarA.IMPL);

    public static final Sub IMPL_FI = new Sub(Flags.IMPL, Indirect.IMPL);
    public static final Sub IMPL_FD = new Sub(Flags.IMPL, Direct.IMPL);
    public static final Sub IMPL_FL = new Sub(Flags.IMPL, Literal.IMPL);
    public static final Sub IMPL_FA = new Sub(Flags.IMPL, VarA.IMPL);

    public static final Sub IMPL_AI = new Sub(VarA.IMPL, Indirect.IMPL);
    public static final Sub IMPL_AD = new Sub(VarA.IMPL, Direct.IMPL);
    public static final Sub IMPL_AL = new Sub(VarA.IMPL, Literal.IMPL);
    public static final Sub IMPL_AA = new Sub(VarA.IMPL, VarA.IMPL);

    private final Addressing target;
    private final Addressing operand;

    public Sub(Addressing target, Addressing operand) {
        this.target = target;
        this.operand = operand;
    }

    public String getName() {
        return "SUB";
    }

    @Override
    public void execute(NanoCpu nanoCpu, int pc, byte ins, NanoMemory mem, NanoIo io) {
        final int targetAddr = target.load(mem);
        final int operandAddr = operand.load(mem);
        final byte data1 = target.read(mem, targetAddr);
        final byte data2 = operand.read(mem, operandAddr);
        final byte result = (byte) (data1 - data2);
        target.write(mem, targetAddr, result);
        mem.writeFlags(flags(result, result > data1));
        mem.incPc();
        nanoCpu.afterExecution(pc, mem.pc() - pc, this, ins, target, targetAddr, operand, operandAddr);
    }
}
