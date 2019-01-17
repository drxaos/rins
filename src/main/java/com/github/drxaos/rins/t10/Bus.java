package com.github.drxaos.rins.t10;

import java.util.ArrayList;
import java.util.List;

public class Bus {
    private List<Device> devices = new ArrayList<>();

    private int addr, data, ctrl, addrNext, dataNext, ctrlNext;

    public void nextNanoSecond() {
        addr = addrNext;
        addrNext = 0;
        data = dataNext;
        dataNext = 0;
        ctrl = ctrlNext;
        ctrlNext = 0;

        for (Device device : devices) {
            device.pulse();
        }
    }

    public void attachDevice(Device device) {
        devices.add(device);
        device.setBus(this);
    }

    public void initialize() {
        for (Device device : devices) {
            device.initialize();
        }
    }

    public int getAddr() {
        return addr;
    }

    public int getData() {
        return data;
    }

    public int getCtrl() {
        return ctrl;
    }

    public int getAddrNext() {
        return addrNext;
    }

    public int getDataNext() {
        return dataNext;
    }

    public int getCtrlNext() {
        return ctrlNext;
    }

    public void setNext(int addrNext, int dataNext, int ctrlNext) {
        this.addrNext = addrNext;
        this.dataNext = dataNext;
        this.ctrlNext = ctrlNext;
    }
}
