package com.github.drxaos.ncpu.vm;

public class NanoIo {
    final byte[] in = new byte[256];
    final byte[] out = new byte[256];

    private boolean debug;

    public byte read(int port) {
        final byte data = in[port & 0xFF];
        if (debug) {
            System.out.println("port " + (port & 0xff) + " read " + data);
        }
        return data;
    }

    public void write(int port, byte data) {
        if (debug) {
            System.out.println("port " + (port & 0xff) + " write " + data);
        }
        out[port & 0xFF] = data;
    }

    public void setInput(int port, byte data) {
        in[port & 0xFF] = data;
    }

    public byte getOutput(int port) {
        return out[port & 0xFF];
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
