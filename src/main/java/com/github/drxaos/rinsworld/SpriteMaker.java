package com.github.drxaos.rinsworld;

import com.github.drxaos.rinsworld.things.Thing;
import com.github.drxaos.spriter.Proto;
import com.github.drxaos.spriter.Sprite;
import com.github.drxaos.spriter.Spriter;
import com.github.drxaos.spriter.Utils;

import java.io.IOException;

public class SpriteMaker {

    private Spriter spriter;
    private Proto wallProto;
    private Proto startProto;
    private Proto finishProto;
    private Proto chassisProto;
    private Proto boxProto;
    private Proto botProto;

    public SpriteMaker(Spriter spriter) {
        this.spriter = spriter;

        try {
            wallProto = spriter.createProto(Utils.loadImageFromResource("/wall.png"), 25.5, 25.5);
            boxProto = spriter.createProto(Utils.loadImageFromResource("/box.png"), 25.5, 25.5);
            chassisProto = spriter.createProto(Utils.loadImageFromResource("/chassis.png"), 25.5, 25.5);
            botProto = spriter.createProto(Utils.loadImageFromResource("/bot.png"), 25.5, 25.5);
            startProto = spriter.createProto(Utils.loadImageFromResource("/start.png"), 25.5, 25.5);
            finishProto = spriter.createProto(Utils.loadImageFromResource("/finish.png"), 25.5, 25.5);
        } catch (IOException e) {
            throw new ResourcesException(e);
        }
    }

    public ThingSprite make(Thing thing) {
        Sprite sprite;
        switch (thing.getType()) {
            case WALL:
                sprite = makeWall();
                break;
            case START:
                sprite = makeStart();
                break;
            case FINISH:
                sprite = makeFinish();
                break;
            default:
                throw new UnsupportedOperationException("unknown type");
        }

        sprite.setPos(thing.getPos().getX(), thing.getPos().getY()).setAngle(thing.getPos().getAngle());
        return new ThingSprite();
    }

    private Sprite makeWall() {
        return wallProto.newInstance(1);
    }

    private Sprite makeStart() {
        return startProto.newInstance(1);
    }

    private Sprite makeFinish() {
        return finishProto.newInstance(1);
    }
}
