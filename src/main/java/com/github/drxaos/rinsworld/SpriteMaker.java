package com.github.drxaos.rinsworld;

import com.github.drxaos.spriter.Spriter;
import com.github.drxaos.spriter.SpriterUtils;

import java.io.IOException;

public class SpriteMaker {

    private Spriter spriter;
    private Spriter.Sprite wallProto;
    private Spriter.Sprite chassisProto;
    private Spriter.Sprite boxProto;
    private Spriter.Sprite botProto;

    public SpriteMaker(Spriter spriter) {
        this.spriter = spriter;

        try {
            wallProto = spriter.createSpriteProto(SpriterUtils.loadImageFromResource("wall.png"), 25.5, 25.5).setWidthProportional(50);
            boxProto = spriter.createSpriteProto(SpriterUtils.loadImageFromResource("box.png"), 25.5, 25.5).setWidthProportional(50);
            chassisProto = spriter.createSpriteProto(SpriterUtils.loadImageFromResource("chassis.png"), 25.5, 25.5).setWidthProportional(50);
            botProto = spriter.createSpriteProto(SpriterUtils.loadImageFromResource("bot.png"), 25.5, 25.5).setWidthProportional(50);
        } catch (IOException e) {
            throw new ResourcesException(e);
        }
    }

    public ThingSprite make(Thing thing) {
        switch (thing.getType()) {
            case WALL:
                return makeWall();
        }
        throw new UnsupportedOperationException("unknown type");
    }

    private ThingSprite makeWall() {
        wallProto.createGhost();
        return null;
    }


}
