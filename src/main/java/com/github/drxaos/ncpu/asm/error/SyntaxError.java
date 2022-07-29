package com.github.drxaos.ncpu.asm.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SyntaxError {
    int line, pos;
    String message;
}
