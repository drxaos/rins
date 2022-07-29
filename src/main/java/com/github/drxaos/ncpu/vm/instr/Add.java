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

public class Add implements NanoExecution {
    public static final Add IMPL_II = new Add(Indirect.IMPL, Indirect.IMPL);
    public static final Add IMPL_ID = new Add(Indirect.IMPL, Direct.IMPL);
    public static final Add IMPL_IL = new Add(Indirect.IMPL, Literal.IMPL);
    public static final Add IMPL_IA = new Add(Indirect.IMPL, VarA.IMPL);

    public static final Add IMPL_DI = new Add(Direct.IMPL, Indirect.IMPL);
    public static final Add IMPL_DD = new Add(Direct.IMPL, Direct.IMPL);
    public static final Add IMPL_DL = new Add(Direct.IMPL, Literal.IMPL);
    public static final Add IMPL_DA = new Add(Direct.IMPL, VarA.IMPL);

    public static final Add IMPL_FI = new Add(Flags.IMPL, Indirect.IMPL);
    public static final Add IMPL_FD = new Add(Flags.IMPL, Direct.IMPL);
    public static final Add IMPL_FL = new Add(Flags.IMPL, Literal.IMPL);
    public static final Add IMPL_FA = new Add(Flags.IMPL, VarA.IMPL);

    public static final Add IMPL_AI = new Add(VarA.IMPL, Indirect.IMPL);
    public static final Add IMPL_AD = new Add(VarA.IMPL, Direct.IMPL);
    public static final Add IMPL_AL = new Add(VarA.IMPL, Literal.IMPL);
    public static final Add IMPL_AA = new Add(VarA.IMPL, VarA.IMPL);

    private final Addressing target;
    private final Addressing operand;

    public Add(Addressing target, Addressing operand) {
        this.target = target;
        this.operand = operand;
    }

    public String getName() {
        return "ADD";
    }

    @Override
    public void execute(NanoCpu nanoCpu, int pc, byte ins, NanoMemory mem, NanoIo io) {
        final int targetAddr = target.load(mem);
        final int operandAddr = operand.load(mem);
        final byte data1 = target.read(mem, targetAddr);
        final byte data2 = operand.read(mem, operandAddr);
        final byte result = (byte) (data1 + data2);
        target.write(mem, targetAddr, result);
        mem.writeFlags(flags(result, result < data1));
        mem.incPc();
        nanoCpu.afterExecution(pc, mem.pc() - pc, this, ins, target, targetAddr, operand, operandAddr);
    }
}
