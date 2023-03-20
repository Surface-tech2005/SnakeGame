package com.surface2022.shake.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class MyPacker {
    public static void main (String[] args) throws Exception {
        TexturePacker.process("images", "images_atlas", "snake_atlas");
    }
}