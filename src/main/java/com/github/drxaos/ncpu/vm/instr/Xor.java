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

public class Xor implements NanoExecution {
    public static final Xor IMPL_II = new Xor(Indirect.IMPL, Indirect.IMPL);
    public static final Xor IMPL_ID = new Xor(Indirect.IMPL, Direct.IMPL);
    public static final Xor IMPL_IL = new Xor(Indirect.IMPL, Literal.IMPL);
    public static final Xor IMPL_IA = new Xor(Indirect.IMPL, VarA.IMPL);

    public static final Xor IMPL_DI = new Xor(Direct.IMPL, Indirect.IMPL);
    public static final Xor IMPL_DD = new Xor(Direct.IMPL, Direct.IMPL);
    public static final Xor IMPL_DL = new Xor(Direct.IMPL, Literal.IMPL);
    public static final Xor IMPL_DA = new Xor(Direct.IMPL, VarA.IMPL);

    public static final Xor IMPL_FI = new Xor(Flags.IMPL, Indirect.IMPL);
    public static final Xor IMPL_FD = new Xor(Flags.IMPL, Direct.IMPL);
    public static final Xor IMPL_FL = new Xor(Flags.IMPL, Literal.IMPL);
    public static final Xor IMPL_FA = new Xor(Flags.IMPL, VarA.IMPL);

    public static final Xor IMPL_AI = new Xor(VarA.IMPL, Indirect.IMPL);
    public static final Xor IMPL_AD = new Xor(VarA.IMPL, Direct.IMPL);
    public static final Xor IMPL_AL = new Xor(VarA.IMPL, Literal.IMPL);
    public static final Xor IMPL_AA = new Xor(VarA.IMPL, VarA.IMPL);

    private final Addressing target;
    private final Addressing operand;

    public Xor(Addressing target, Addressing operand) {
        this.target = target;
        this.operand = operand;
    }

    public String getName() {
        return "XOR";
    }

    @Override
    public void execute(NanoCpu nanoCpu, int pc, byte ins, NanoMemory mem, NanoIo io) {
        final int targetAddr = target.load(mem);
        final int operandAddr = operand.load(mem);
        final byte data1 = target.read(mem, targetAddr);
        final byte data2 = operand.read(mem, operandAddr);
        final byte result = (byte) (data1 ^ data2);
        target.write(mem, targetAddr, result);
        mem.writeFlags(flags(result, false));
        mem.incPc();
        nanoCpu.afterExecution(pc, mem.pc() - pc, this, ins, target, targetAddr, operand, operandAddr);
    }
}
