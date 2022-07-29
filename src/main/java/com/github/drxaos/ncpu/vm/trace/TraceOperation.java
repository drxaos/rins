package com.github.drxaos.ncpu.vm.trace;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class TraceOperation {
    TraceInstruction instruction;
    TraceOperand operand1;
    TraceOperand operand2;
}
