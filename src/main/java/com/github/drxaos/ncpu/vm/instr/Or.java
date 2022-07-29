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

public class Or implements NanoExecution {
    public static final Or IMPL_II = new Or(Indirect.IMPL, Indirect.IMPL);
    public static final Or IMPL_ID = new Or(Indirect.IMPL, Direct.IMPL);
    public static final Or IMPL_IL = new Or(Indirect.IMPL, Literal.IMPL);
    public static final Or IMPL_IA = new Or(Indirect.IMPL, VarA.IMPL);

    public static final Or IMPL_DI = new Or(Direct.IMPL, Indirect.IMPL);
    public static final Or IMPL_DD = new Or(Direct.IMPL, Direct.IMPL);
    public static final Or IMPL_DL = new Or(Direct.IMPL, Literal.IMPL);
    public static final Or IMPL_DA = new Or(Direct.IMPL, VarA.IMPL);

    public static final Or IMPL_FI = new Or(Flags.IMPL, Indirect.IMPL);
    public static final Or IMPL_FD = new Or(Flags.IMPL, Direct.IMPL);
    public static final Or IMPL_FL = new Or(Flags.IMPL, Literal.IMPL);
    public static final Or IMPL_FA = new Or(Flags.IMPL, VarA.IMPL);

    public static final Or IMPL_AI = new Or(VarA.IMPL, Indirect.IMPL);
    public static final Or IMPL_AD = new Or(VarA.IMPL, Direct.IMPL);
    public static final Or IMPL_AL = new Or(VarA.IMPL, Literal.IMPL);
    public static final Or IMPL_AA = new Or(VarA.IMPL, VarA.IMPL);

    private final Addressing target;
    private final Addressing operand;

    public Or(Addressing target, Addressing operand) {
        this.target = target;
        this.operand = operand;
    }

    public String getName() {
        return "OR";
    }

    @Override
    public void execute(NanoCpu nanoCpu, int pc, byte ins, NanoMemory mem, NanoIo io) {
        final int targetAddr = target.load(mem);
        final int operandAddr = operand.load(mem);
        final byte data1 = target.read(mem, targetAddr);
        final byte data2 = operand.read(mem, operandAddr);
        final byte result = (byte) (data1 | data2);
        target.write(mem, targetAddr, result);
        mem.writeFlags(flags(result, false));
        mem.incPc();
        nanoCpu.afterExecution(pc, mem.pc() - pc, this, ins, target, targetAddr, operand, operandAddr);
    }
}
