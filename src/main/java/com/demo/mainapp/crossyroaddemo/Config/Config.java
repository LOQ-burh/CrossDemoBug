package com.demo.mainapp.crossyroaddemo.Config;

import java.awt.*;

public class Config {
    public static final int HEIGHT = 720;
    public static final int WIDTH = 1280;

    public static final String SAVE_DATA_NAME = "./Score.dat";

//    static TerrainBuilder       builder = new TerrainBuilder();

    static Point player;
    static boolean              isOver;
    static boolean              isPaused;
    static boolean              isReadyToLoadGraphics;
    static boolean              isSTAT;
    static boolean              isOnWater;
    static long                 thread_sleep_param;
    static int                  PLAYER_DIRECTION;
    static int                  SCORE;
    static final int            UP = 0;
    static final int            DOWN = 1;
    static final int            LEFT = 2;
    static final int            RIGHT = 3;
}
