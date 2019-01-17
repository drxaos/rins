package com.github.drxaos.rins.t10;

public abstract class Device {
    public static final int CTRL_READ = 0;
    public static final int CTRL_WRITE = 1;
    public static final int CTRL_RESULT = 2;

    private Bus bus = new Bus();
    private int addrNext, dataNext, ctrlNext, waitNext;

    void setBus(Bus bus) {
        this.bus = bus;
    }

    public final void pulse() {
        if (waitNext > 0) {
            waitNext--;
            if (waitNext == 0) {
                bus.setNext(addrNext, dataNext, ctrlNext);
            }
        } else {
            handleInput(bus.getAddr(), bus.getData(), bus.getCtrl());
        }
    }

    public final void initialize() {
        setNextWithLatency(0, 0, 0, 0);
    }

    protected void setNextWithLatency(int addrNext, int dataNext, int ctrlNext, int latency) {
        this.addrNext = addrNext;
        this.dataNext = dataNext;
        this.ctrlNext = ctrlNext;
        this.waitNext = latency;
    }

    public abstract void handleInput(int addr, int data, int ctrl);

    public void initState() {
    }

}
