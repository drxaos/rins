package com.github.drxaos.ncpu.vm.trace;

import com.github.drxaos.ncpu.vm.NanoExecution;
import lombok.Value;

@Value
public class TraceInstruction {
    int code;
    String name;
    NanoExecution execution;

    public TraceInstruction(int code, NanoExecution execution) {
        this.code = code;
        this.execution = execution;
        this.name = execution.getName();
    }
}
