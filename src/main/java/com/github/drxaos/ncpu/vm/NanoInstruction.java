package com.github.drxaos.ncpu.vm;

import com.github.drxaos.ncpu.vm.instr.Add;
import com.github.drxaos.ncpu.vm.instr.And;
import com.github.drxaos.ncpu.vm.instr.Dec;
import com.github.drxaos.ncpu.vm.instr.Halt;
import com.github.drxaos.ncpu.vm.instr.In;
import com.github.drxaos.ncpu.vm.instr.Inc;
import com.github.drxaos.ncpu.vm.instr.Jin;
import com.github.drxaos.ncpu.vm.instr.JinShort;
import com.github.drxaos.ncpu.vm.instr.Jio;
import com.github.drxaos.ncpu.vm.instr.JioShort;
import com.github.drxaos.ncpu.vm.instr.Jiz;
import com.github.drxaos.ncpu.vm.instr.JizShort;
import com.github.drxaos.ncpu.vm.instr.Jmp;
import com.github.drxaos.ncpu.vm.instr.JmpShort;
import com.github.drxaos.ncpu.vm.instr.Mov;
import com.github.drxaos.ncpu.vm.instr.Neg;
import com.github.drxaos.ncpu.vm.instr.Nop;
import com.github.drxaos.ncpu.vm.instr.Not;
import com.github.drxaos.ncpu.vm.instr.Or;
import com.github.drxaos.ncpu.vm.instr.Out;
import com.github.drxaos.ncpu.vm.instr.Rol;
import com.github.drxaos.ncpu.vm.instr.Ror;
import com.github.drxaos.ncpu.vm.instr.Ssr;
import com.github.drxaos.ncpu.vm.instr.Sub;
import com.github.drxaos.ncpu.vm.instr.Xor;

public enum NanoInstruction {

    NOP(0b0____0000_0000, Nop.IMPL),

    ROL_I(0b0___0000_0001, Rol.IMPL_I),
    ROL_D(0b0___0000_0101, Rol.IMPL_D),
    ROL_F(0b0___0000_1001, Rol.IMPL_F),
    ROL_A(0b0___0000_1101, Rol.IMPL_A),
    ROR_I(0b0___0000_0010, Ror.IMPL_I),
    ROR_D(0b0___0000_0110, Ror.IMPL_D),
    ROR_F(0b0___0000_1010, Ror.IMPL_F),
    ROR_A(0b0___0000_1110, Ror.IMPL_A),
    SSR_I(0b0___0000_0011, Ssr.IMPL_I),
    SSR_D(0b0___0000_0111, Ssr.IMPL_D),
    SSR_F(0b0___0000_1011, Ssr.IMPL_F),
    SSR_A(0b0___0000_1111, Ssr.IMPL_A),

    MOV_II(0b0_0001_0000, Mov.IMPL_II),
    MOV_ID(0b0_0001_0001, Mov.IMPL_ID),
    MOV_IL(0b0_0001_0010, Mov.IMPL_IL),
    MOV_IA(0b0_0001_0011, Mov.IMPL_IA),
    MOV_DI(0b0_0001_0100, Mov.IMPL_DI),
    MOV_DD(0b0_0001_0101, Mov.IMPL_DD),
    MOV_DL(0b0_0001_0110, Mov.IMPL_DL),
    MOV_DA(0b0_0001_0111, Mov.IMPL_DA),
    MOV_FI(0b0_0001_1000, Mov.IMPL_FI),
    MOV_FD(0b0_0001_1001, Mov.IMPL_FD),
    MOV_FL(0b0_0001_1010, Mov.IMPL_FL),
    MOV_FA(0b0_0001_1011, Mov.IMPL_FA),
    MOV_AI(0b0_0001_1100, Mov.IMPL_AI),
    MOV_AD(0b0_0001_1101, Mov.IMPL_AD),
    MOV_AL(0b0_0001_1110, Mov.IMPL_AL),
    MOV_AA(0b0_0001_1111, Mov.IMPL_AA),

    ADD_II(0b0_0010_0000, Add.IMPL_II),
    ADD_ID(0b0_0010_0001, Add.IMPL_ID),
    ADD_IL(0b0_0010_0010, Add.IMPL_IL),
    ADD_IA(0b0_0010_0011, Add.IMPL_IA),
    ADD_DI(0b0_0010_0100, Add.IMPL_DI),
    ADD_DD(0b0_0010_0101, Add.IMPL_DD),
    ADD_DL(0b0_0010_0110, Add.IMPL_DL),
    ADD_DA(0b0_0010_0111, Add.IMPL_DA),
    ADD_FI(0b0_0010_1000, Add.IMPL_FI),
    ADD_FD(0b0_0010_1001, Add.IMPL_FD),
    ADD_FL(0b0_0010_1010, Add.IMPL_FL),
    ADD_FA(0b0_0010_1011, Add.IMPL_FA),
    ADD_AI(0b0_0010_1100, Add.IMPL_AI),
    ADD_AD(0b0_0010_1101, Add.IMPL_AD),
    ADD_AL(0b0_0010_1110, Add.IMPL_AL),
    ADD_AA(0b0_0010_1111, Add.IMPL_AA),

    SUB_II(0b0_0011_0000, Sub.IMPL_II),
    SUB_ID(0b0_0011_0001, Sub.IMPL_ID),
    SUB_IL(0b0_0011_0010, Sub.IMPL_IL),
    SUB_IA(0b0_0011_0011, Sub.IMPL_IA),
    SUB_DI(0b0_0011_0100, Sub.IMPL_DI),
    SUB_DD(0b0_0011_0101, Sub.IMPL_DD),
    SUB_DL(0b0_0011_0110, Sub.IMPL_DL),
    SUB_DA(0b0_0011_0111, Sub.IMPL_DA),
    SUB_FI(0b0_0011_1000, Sub.IMPL_FI),
    SUB_FD(0b0_0011_1001, Sub.IMPL_FD),
    SUB_FL(0b0_0011_1010, Sub.IMPL_FL),
    SUB_FA(0b0_0011_1011, Sub.IMPL_FA),
    SUB_AI(0b0_0011_1100, Sub.IMPL_AI),
    SUB_AD(0b0_0011_1101, Sub.IMPL_AD),
    SUB_AL(0b0_0011_1110, Sub.IMPL_AL),
    SUB_AA(0b0_0011_1111, Sub.IMPL_AA),

    AND_II(0b0_0100_0000, And.IMPL_II),
    AND_ID(0b0_0100_0001, And.IMPL_ID),
    AND_IL(0b0_0100_0010, And.IMPL_IL),
    AND_IA(0b0_0100_0011, And.IMPL_IA),
    AND_DI(0b0_0100_0100, And.IMPL_DI),
    AND_DD(0b0_0100_0101, And.IMPL_DD),
    AND_DL(0b0_0100_0110, And.IMPL_DL),
    AND_DA(0b0_0100_0111, And.IMPL_DA),
    AND_FI(0b0_0100_1000, And.IMPL_FI),
    AND_FD(0b0_0100_1001, And.IMPL_FD),
    AND_FL(0b0_0100_1010, And.IMPL_FL),
    AND_FA(0b0_0100_1011, And.IMPL_FA),
    AND_AI(0b0_0100_1100, And.IMPL_AI),
    AND_AD(0b0_0100_1101, And.IMPL_AD),
    AND_AL(0b0_0100_1110, And.IMPL_AL),
    AND_AA(0b0_0100_1111, And.IMPL_AA),

    OR_II(0b0__0101_0000, Or.IMPL_II),
    OR_ID(0b0__0101_0001, Or.IMPL_ID),
    OR_IL(0b0__0101_0010, Or.IMPL_IL),
    OR_IA(0b0__0101_0011, Or.IMPL_IA),
    OR_DI(0b0__0101_0100, Or.IMPL_DI),
    OR_DD(0b0__0101_0101, Or.IMPL_DD),
    OR_DL(0b0__0101_0110, Or.IMPL_DL),
    OR_DA(0b0__0101_0111, Or.IMPL_DA),
    OR_FI(0b0__0101_1000, Or.IMPL_FI),
    OR_FD(0b0__0101_1001, Or.IMPL_FD),
    OR_FL(0b0__0101_1010, Or.IMPL_FL),
    OR_FA(0b0__0101_1011, Or.IMPL_FA),
    OR_AI(0b0__0101_1100, Or.IMPL_AI),
    OR_AD(0b0__0101_1101, Or.IMPL_AD),
    OR_AL(0b0__0101_1110, Or.IMPL_AL),
    OR_AA(0b0__0101_1111, Or.IMPL_AA),

    XOR_II(0b0_0110_0000, Xor.IMPL_II),
    XOR_ID(0b0_0110_0001, Xor.IMPL_ID),
    XOR_IL(0b0_0110_0010, Xor.IMPL_IL),
    XOR_IA(0b0_0110_0011, Xor.IMPL_IA),
    XOR_DI(0b0_0110_0100, Xor.IMPL_DI),
    XOR_DD(0b0_0110_0101, Xor.IMPL_DD),
    XOR_DL(0b0_0110_0110, Xor.IMPL_DL),
    XOR_DA(0b0_0110_0111, Xor.IMPL_DA),
    XOR_FI(0b0_0110_1000, Xor.IMPL_FI),
    XOR_FD(0b0_0110_1001, Xor.IMPL_FD),
    XOR_FL(0b0_0110_1010, Xor.IMPL_FL),
    XOR_FA(0b0_0110_1011, Xor.IMPL_FA),
    XOR_AI(0b0_0110_1100, Xor.IMPL_AI),
    XOR_AD(0b0_0110_1101, Xor.IMPL_AD),
    XOR_AL(0b0_0110_1110, Xor.IMPL_AL),
    XOR_AA(0b0_0110_1111, Xor.IMPL_AA),

    INC_I(0b0_0111_0000, Inc.IMPL_I),
    INC_D(0b0_0111_0001, Inc.IMPL_D),
    INC_F(0b0_0111_0010, Inc.IMPL_F),
    INC_A(0b0_0111_0011, Inc.IMPL_A),

    DEC_I(0b0_0111_0100, Dec.IMPL_I),
    DEC_D(0b0_0111_0101, Dec.IMPL_D),
    DEC_F(0b0_0111_0110, Dec.IMPL_F),
    DEC_A(0b0_0111_0111, Dec.IMPL_A),

    NEG_I(0b0_0111_1000, Neg.IMPL_I),
    NEG_D(0b0_0111_1001, Neg.IMPL_D),
    NEG_F(0b0_0111_1010, Neg.IMPL_F),
    NEG_A(0b0_0111_1011, Neg.IMPL_A),

    NOT_I(0b0_0111_1100, Not.IMPL_I),
    NOT_D(0b0_0111_1101, Not.IMPL_D),
    NOT_F(0b0_0111_1110, Not.IMPL_F),
    NOT_A(0b0_0111_1111, Not.IMPL_A),

    JMP(0b0___1000_0000, Jmp.IMPL),
    JMP_S(0b0_1000_0001, 0b0_1000_1111, JmpShort.IMPL),

    JIZ(0b0___1001_0000, Jiz.IMPL),
    JIZ_S(0b0_1001_0001, 0b0_1001_1111, JizShort.IMPL),

    JIN(0b0___1010_0000, Jin.IMPL),
    JIN_S(0b0_1010_0001, 0b0_1010_1111, JinShort.IMPL),

    JIO(0b0___1011_0000, Jio.IMPL),
    JIO_S(0b0_1011_0001, 0b0_1011_1111, JioShort.IMPL),

    JIL(0b0___1100_0000, Jio.IMPL),
    JIL_S(0b0_1100_0001, 0b0_1011_1111, JioShort.IMPL),

    JIG(0b0___1101_0000, Jio.IMPL),
    JIG_S(0b0_1101_0001, 0b0_1011_1111, JioShort.IMPL),

    IN_II(0b0___1110_0000, In.IMPL_II),
    IN_ID(0b0___1110_0001, In.IMPL_ID),
    IN_IL(0b0___1110_0010, In.IMPL_IL),
    IN_IA(0b0___1110_0011, In.IMPL_IA),
    IN_DI(0b0___1110_0100, In.IMPL_DI),
    IN_DD(0b0___1110_0101, In.IMPL_DD),
    IN_DL(0b0___1110_0110, In.IMPL_DL),
    IN_DA(0b0___1110_0111, In.IMPL_DA),
    IN_FI(0b0___1110_1000, In.IMPL_FI),
    IN_FD(0b0___1110_1001, In.IMPL_FD),
    IN_FL(0b0___1110_1010, In.IMPL_FL),
    IN_FA(0b0___1110_1011, In.IMPL_FA),
    IN_AI(0b0___1110_1100, In.IMPL_AI),
    IN_AD(0b0___1110_1101, In.IMPL_AD),
    IN_AL(0b0___1110_1110, In.IMPL_AL),
    IN_AA(0b0___1110_1111, In.IMPL_AA),

    OUT_II(0b0___1101_0000, Out.IMPL_II),
    OUT_ID(0b0___1101_0001, Out.IMPL_ID),
    OUT_IL(0b0___1101_0010, Out.IMPL_IL),
    OUT_IA(0b0___1101_0011, Out.IMPL_IA),
    OUT_DI(0b0___1101_0100, Out.IMPL_DI),
    OUT_DD(0b0___1101_0101, Out.IMPL_DD),
    OUT_DL(0b0___1101_0110, Out.IMPL_DL),
    OUT_DA(0b0___1101_0111, Out.IMPL_DA),
    OUT_LI(0b0___1101_1000, Out.IMPL_LI),
    OUT_LD(0b0___1101_1001, Out.IMPL_LD),
    OUT_LL(0b0___1101_1010, Out.IMPL_LL),
    OUT_LA(0b0___1101_1011, Out.IMPL_LA),
    OUT_AI(0b0___1101_1100, Out.IMPL_AI),
    OUT_AD(0b0___1101_1101, Out.IMPL_AD),
    OUT_AL(0b0___1101_1110, Out.IMPL_AL),
    OUT_AA(0b0___1101_1111, Out.IMPL_AA),

    HALT(0b0_1111_1111, Halt.IMPL),
    ;

    private final static NanoExecution[] executions;

    static {
        executions = new NanoExecution[256];
        for (NanoInstruction instruction : values()) {
            for (int code : instruction.codes) {
                executions[code] = instruction.execution;
            }
        }
        for (int i = 0; i < 255; i++) {
            if (executions[i] == null) {
                executions[i] = Nop.IMPL;
            }
        }
    }

    private final int[] codes;
    private final NanoExecution execution;

    NanoInstruction(int code, NanoExecution execution) {
        this.codes = new int[]{code & 0xFF};
        this.execution = execution;
    }

    NanoInstruction(int code, int codeTo, NanoExecution execution) {
        this.codes = new int[codeTo - code + 1];
        for (int nextCode = code; nextCode <= codeTo; nextCode++) {
            this.codes[nextCode - code] = nextCode & 0xFF;
        }
        this.execution = execution;
    }

    public int getCode() {
        return this.codes[0];
    }

    public static void execute(NanoCpu nanoCpu, int programCounter, int instruction, NanoMemory mem, NanoIo io) {
        executions[instruction & 0xFF].execute(nanoCpu, programCounter, (byte) instruction, mem, io);
    }
}
