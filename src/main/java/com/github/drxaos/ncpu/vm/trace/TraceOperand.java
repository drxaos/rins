package com.github.drxaos.ncpu.vm.trace;

import com.github.drxaos.ncpu.vm.Addressing;
import lombok.Value;

@Value
public class TraceOperand {
    int address;
    String name;
    Addressing addressing;

    public TraceOperand(int address, Addressing addressing) {
        this.address = address;
        this.addressing = addressing;
        this.name = addressing.toName(address);
    }
}
