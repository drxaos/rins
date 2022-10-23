package com.github.drxaos.ncpu.asm.opcode;

import com.github.drxaos.ncpu.asm.opcode.opcode.AbstractOpcode;
import com.github.drxaos.ncpu.asm.opcode.opcode.AddOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.AndOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.VarDirective;
import com.github.drxaos.ncpu.asm.opcode.opcode.DecOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.HaltOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.IncOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.JinOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.JioOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.JizOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.JmpOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.LabelDirective;
import com.github.drxaos.ncpu.asm.opcode.opcode.MovOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.NegOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.NopOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.NotOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.OrOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.RolOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.RorOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.SsrOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.StartDirective;
import com.github.drxaos.ncpu.asm.opcode.opcode.SubOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.XorOperation;
import lombok.Getter;

public enum OpcodeTable {
    // directive
    $START(new StartDirective()),
    $VAR(new VarDirective()),
    $ARRAY(new VarDirective()),
    $LABEL(new LabelDirective()),

    // shift
    ROL(new RolOperation()),
    ROR(new RorOperation()),
    SSR(new SsrOperation()),

    // system
    NOP(new NopOperation()),
    HALT(new HaltOperation()),

    // binary
    MOV(new MovOperation()),
    ADD(new AddOperation()),
    SUB(new SubOperation()),
    AND(new AndOperation()),
    OR(new OrOperation()),
    XOR(new XorOperation()),

    // unary
    INC(new IncOperation()),
    DEC(new DecOperation()),
    NEG(new NegOperation()),
    NOT(new NotOperation()),

    // branch
    JMP(new JmpOperation()),
    JIN(new JinOperation()),
    JIO(new JioOperation()),
    JIZ(new JizOperation());

    // io
    // IN
    // OUT

    @Getter
    private final AbstractOpcode opcode;

    OpcodeTable(AbstractOpcode opcode) {
        this.opcode = opcode;
    }
}
