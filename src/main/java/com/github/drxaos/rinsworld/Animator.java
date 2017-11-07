package com.github.drxaos.rinsworld;

import com.github.drxaos.rinsworld.things.Thing;
import com.github.drxaos.spriter.Control;
import com.github.drxaos.spriter.Spriter;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Animator {

    private Locality locality;
    private ArrayList<ThingSprite> thingSprites = new ArrayList<>();
    private Control control;

    public Animator(Locality locality) {
        this.locality = locality;
    }

    public void runLoop() throws InterruptedException {
        Spriter spriter = Spriter.createDefault("Rins world");
        spriter.setViewportWidth(15);
        spriter.setViewportHeight(15);

        spriter.setBackgroundColor(Color.GRAY);
        spriter.setDebug(true);
        spriter.setShowCursor(true);

        control = spriter.getControl();

        SpriteMaker spriteMaker = new SpriteMaker(spriter);

        for (Thing thing : locality.getThings()) {
            thingSprites.add(spriteMaker.make(thing));
        }

        while (true) {
            spriter.beginFrame();


            spriter.endFrame();
            TimeUnit.MILLISECONDS.sleep(25);
        }
    }
}
