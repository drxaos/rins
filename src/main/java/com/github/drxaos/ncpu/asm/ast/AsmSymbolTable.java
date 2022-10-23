package com.github.drxaos.ncpu.asm.ast;

import com.github.drxaos.ncpu.asm.eval.Eval;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsmSymbolTable {

    Map<String, Integer> literals = new HashMap<>();
    Map<String, AsmInstruction> variables = new HashMap<>();
    Map<String, List<AsmOperand>> references = new HashMap<>();

    public void putVariable(AsmOperand variable) {
        variables.put(variable.getVariable(), variable.getAsmInstruction());
    }

    public List<VariableMatch> getMatchedVariables() {
        return variables.entrySet()
                .stream()
                .flatMap(ve -> references.getOrDefault(ve.getKey(), List.of()).stream()
                        .map(r -> new VariableMatch(ve.getKey(), ve.getValue(), r)))
                .toList();
    }

    public List<LiteralMatch> getMatchedLiterals() {
        return literals.entrySet()
                .stream()
                .flatMap(ve -> references.getOrDefault(ve.getKey(), List.of()).stream()
                        .map(r -> new LiteralMatch(ve.getKey(), ve.getValue(), r)))
                .toList();
    }

    public List<AsmOperand> getNotMatched() {
        return references.keySet().stream()
                .filter(name -> !variables.containsKey(name))
                .filter(name -> !literals.containsKey(name))
                .flatMap(name -> references.get(name).stream())
                .toList();
    }

    public AsmInstruction getVariable(String name) {
        return variables.get(name);
    }

    public void putReference(AsmOperand reference) {
        references.computeIfAbsent(reference.getVariable(), k -> new ArrayList<>()).add(reference);
    }

    public void populateLiterals() {
        literals.clear();
        final List<AsmOperand> notMatched = getNotMatched();
        notMatched.forEach(op -> {
            final Integer value = Eval.calculate(op, this);
            literals.put(op.getVariable(), value);
        });
    }

    @Data
    public static class NamedAddress {
        AsmInstruction asmInstruction;

        public NamedAddress(AsmInstruction asmInstruction) {
            this.asmInstruction = asmInstruction;
        }
    }

    @Data
    @AllArgsConstructor
    public static class VariableMatch {
        String name;
        AsmInstruction variable;
        AsmOperand reference;
    }

    @Data
    @AllArgsConstructor
    public static class LiteralMatch {
        String name;
        Integer literal;
        AsmOperand reference;
    }
}
