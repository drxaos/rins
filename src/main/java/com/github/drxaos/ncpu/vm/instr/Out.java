package com.github.drxaos.ncpu.vm.instr;

import com.github.drxaos.ncpu.vm.Addressing;
import com.github.drxaos.ncpu.vm.NanoCpu;
import com.github.drxaos.ncpu.vm.NanoExecution;
import com.github.drxaos.ncpu.vm.NanoIo;
import com.github.drxaos.ncpu.vm.NanoMemory;
import com.github.drxaos.ncpu.vm.addr.Direct;
import com.github.drxaos.ncpu.vm.addr.Indirect;
import com.github.drxaos.ncpu.vm.addr.Literal;
import com.github.drxaos.ncpu.vm.addr.VarA;

public class Out implements NanoExecution {
    public static final Out IMPL_II = new Out(Indirect.IMPL, Indirect.IMPL);
    public static final Out IMPL_DI = new Out(Direct.IMPL, Indirect.IMPL);
    public static final Out IMPL_LI = new Out(Literal.IMPL, Indirect.IMPL);
    public static final Out IMPL_AI = new Out(VarA.IMPL, Indirect.IMPL);
    public static final Out IMPL_ID = new Out(Indirect.IMPL, Direct.IMPL);
    public static final Out IMPL_DD = new Out(Direct.IMPL, Direct.IMPL);
    public static final Out IMPL_LD = new Out(Literal.IMPL, Direct.IMPL);
    public static final Out IMPL_AD = new Out(VarA.IMPL, Direct.IMPL);
    public static final Out IMPL_IL = new Out(Indirect.IMPL, Literal.IMPL);
    public static final Out IMPL_DL = new Out(Direct.IMPL, Literal.IMPL);
    public static final Out IMPL_LL = new Out(Literal.IMPL, Literal.IMPL);
    public static final Out IMPL_AL = new Out(VarA.IMPL, Literal.IMPL);
    public static final Out IMPL_IA = new Out(Indirect.IMPL, VarA.IMPL);
    public static final Out IMPL_DA = new Out(Direct.IMPL, VarA.IMPL);
    public static final Out IMPL_LA = new Out(Literal.IMPL, VarA.IMPL);
    public static final Out IMPL_AA = new Out(VarA.IMPL, VarA.IMPL);

    private final Addressing port;
    private final Addressing operand;

    public Out(Addressing port, Addressing operand) {
        this.port = port;
        this.operand = operand;
    }

    public String getName() {
        return "OUT";
    }

    @Override
    public void execute(NanoCpu nanoCpu, int pc, byte ins, NanoMemory mem, NanoIo io) {
        final int portAddr = port.load(mem);
        final int operandAddr = operand.load(mem);
        final byte data = operand.read(mem, operandAddr);
        io.write(portAddr, data);
        mem.writeFlags(flags(data, false));
        mem.incPc();
        nanoCpu.afterExecution(pc, mem.pc() - pc, this, ins, port, portAddr, operand, operandAddr);
    }
}
