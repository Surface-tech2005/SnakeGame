package com.surface2022.shake;

import com.badlogic.gdx.Input;

public class Settings {

    public static int speed = 8;
    public static int snake_size_x = 30, snake_size_y = 30;
    public static int apple_area = 20; // 2/3 * snake_size      /if snake_size_x == snake_size_y

    public static boolean isGame_over;
    public static boolean isSpacePressed;

    public static boolean android;

    public static int button_size = 120;
    public static int indent_size = 30;
    public static boolean isButton_visible = false;


    // Inputs
    public static final int leftKey     = Input.Keys.A;
    public static final int rightKey    = Input.Keys.D;
    public static final int upKey       = Input.Keys.W;
    public static final int downKey     = Input.Keys.S;

    public static final int leftArrow   = Input.Keys.LEFT;
    public static final int rightArrow  = Input.Keys.RIGHT;
    public static final int upArrow     = Input.Keys.UP;
    public static final int downArrow   = Input.Keys.DOWN;

    public static final int escapeKey   = Input.Keys.ESCAPE;
    public static final int spaceKey    = Input.Keys.SPACE;

}
