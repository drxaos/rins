package com.github.drxaos.rinsworld;

import com.github.drxaos.spriter.Spriter;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Animator {

    public void runLoop() throws InterruptedException {
        Spriter spriter = new Spriter("Rins world");
        spriter.setViewportWidth(15);
        spriter.setViewportHeight(15);

        spriter.setBackgroundColor(Color.BLACK);
        spriter.setDebug(true);

        Spriter.Control control = spriter.getControl();




        while (true) {
            spriter.beginFrame();




            spriter.endFrame();
            TimeUnit.MILLISECONDS.sleep(25);
        }
    }

}
