package com.github.drxaos.rins;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Demo {
    public static void main(String[] args) throws IOException {

        Rins rins = new Rins(10);

        rins.set(0, Rins.Instruction.INC, 9);
        rins.set(1, Rins.Instruction.GET, 9);
        rins.set(2, Rins.Instruction.JIZ, 0);
        rins.set(3, Rins.Instruction.ADD, 9);
        rins.set(4, Rins.Instruction.SET, 9);
        rins.set(5, Rins.Instruction.JMP, 1);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        rins.writeTo(baos);
        byte[] bytes = baos.toByteArray();
        System.out.println(Arrays.toString(bytes));

        Rins old = rins;
        rins = Rins.readFrom(new ByteArrayInputStream(bytes));

        print(rins);

        while (true) {
            System.in.read();
            rins.step();
            print(rins);
        }
    }

    static void print(Rins rins) {
        System.out.println();
        System.out.println();

        System.out.println("----------");
        System.out.println("reg: [" + rins.getRegister() + "]");
        System.out.println();

        int cursor = rins.getCursor();
        for (int i = 0; i < rins.getSize(); i++) {
            String s = "";
            Rins.Instruction ins = rins.getIns(i);
            if (ins != Rins.Instruction.HLT) {
                s = i + ": " + ins.getMnemonic() + "(" + rins.getArg(i) + ")";
            } else {
                s = i + ": [" + rins.get(i) + "]";
            }
            if (cursor == i) {
                s = s + " <=";
            }
            System.out.println(s);
        }
    }
}
