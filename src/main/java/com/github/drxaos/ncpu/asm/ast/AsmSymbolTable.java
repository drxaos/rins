package com.github.drxaos.ncpu.asm.ast;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsmSymbolTable {

    Map<String, AsmInstruction> variables = new HashMap<>();
    Map<String, List<AsmOperand>> references = new HashMap<>();

    public void putVariable(AsmOperand variable) {
        variables.put(variable.getVariable(), variable.getAsmInstruction());
    }

    public List<Match> getMatched() {
        return variables.entrySet()
                .stream()
                .flatMap(ve -> references.getOrDefault(ve.getKey(), List.of()).stream()
                        .map(r -> new Match(ve.getKey(), ve.getValue(), r)))
                .toList();
    }

    public List<AsmOperand> getNotMatched() {
        return references.keySet().stream()
                .filter(name -> !variables.containsKey(name))
                .flatMap(name -> references.get(name).stream())
                .toList();
    }

    public AsmInstruction getReferences(String name) {
        return variables.get(name);
    }

    public void putReference(AsmOperand reference) {
        references.computeIfAbsent(reference.getVariable(), k -> new ArrayList<>()).add(reference);
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
    public static class Match {
        String name;
        AsmInstruction variable;
        AsmOperand reference;
    }
}
