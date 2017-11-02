package com.github.drxaos.rinsworld;

import com.github.drxaos.spriter.Spriter;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Animator {

    private Locality locality;
    private ArrayList<ThingSprite> thingSprites = new ArrayList<>();
    private Spriter.Control control;

    public Animator(Locality locality) {
        this.locality = locality;
    }

    public void runLoop() throws InterruptedException {
        Spriter spriter = new Spriter("Rins world");
        spriter.setViewportWidth(15);
        spriter.setViewportHeight(15);

        spriter.setBackgroundColor(Color.BLACK);
        spriter.setDebug(true);

        control = spriter.getControl();


        for (Thing thing : locality.getThings()) {

        }

        while (true) {
            spriter.beginFrame();


            spriter.endFrame();
            TimeUnit.MILLISECONDS.sleep(25);
        }
    }
}
