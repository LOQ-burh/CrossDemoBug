package com.demo.mainapp.crossyroaddemo;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;

import java.awt.*;
import java.security.SecureRandom;
import java.util.ArrayList;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.texture;

import com.demo.mainapp.crossyroaddemo.Config.Config;

public final class EntityGameFactory implements EntityFactory {
//    static TerrainBuilder         build = Config.builder;
    private SecureRandom rand = new SecureRandom();

    static ArrayList<Image> obstacleImage = new ArrayList<Image>();
    static ArrayList<Point>       obstacle = new ArrayList<Point>();
    static ArrayList<Point>       obstacleLines = new ArrayList<Point>();
    static ArrayList<Integer>     obstacleLineType = new ArrayList<Integer>();
    static ArrayList<Boolean>     lineDirection = new ArrayList<Boolean>();
    static ArrayList<Boolean>     direction = new ArrayList<Boolean>();
    static ArrayList<Boolean>     bigObstacle = new ArrayList<Boolean>();

    //Train
    static ArrayList<Point>       trainObs = new ArrayList<Point>();
    static ArrayList<Boolean>     trainDir = new ArrayList<Boolean>();
    static ArrayList<Image>       trainImg = new ArrayList<Image>();

    private static final int      LINE_ROAD = 0;
    private static final int      LINE_WATER = 1;
    private static final int      LINE_TRAIN = 2;


    private long                  lastTimeGenerated = 0;
    public Entity newBackground(){
        return entityBuilder()
                .at(-10, -10)
                .view(texture("assets/minecraft-crossy-road.png"))
                .zIndex(-500)
                .build();
    }
}
