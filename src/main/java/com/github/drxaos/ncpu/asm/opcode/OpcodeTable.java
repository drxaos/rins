package com.github.drxaos.ncpu.asm.opcode;

import com.github.drxaos.ncpu.asm.opcode.opcode.AbstractOpcode;
import com.github.drxaos.ncpu.asm.opcode.opcode.AddOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.AndOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.ArrayDirective;
import com.github.drxaos.ncpu.asm.opcode.opcode.HaltOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.JinOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.JioOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.JizOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.JmpOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.LabelDirective;
import com.github.drxaos.ncpu.asm.opcode.opcode.MovOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.NopOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.OrOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.StartDirective;
import com.github.drxaos.ncpu.asm.opcode.opcode.SubOperation;
import com.github.drxaos.ncpu.asm.opcode.opcode.VarDirective;
import com.github.drxaos.ncpu.asm.opcode.opcode.XorOperation;
import lombok.Getter;

public enum OpcodeTable {
    // directive
    $START(new StartDirective()),
    $VAR(new VarDirective()),
    $ARRAY(new ArrayDirective()),
    $LABEL(new LabelDirective()),

    // shift
    // ROL
    // ROR
    // SSR

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
    // INC
    // DEC
    // NEG
    // NOT

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
