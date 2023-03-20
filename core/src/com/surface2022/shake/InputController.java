package com.surface2022.shake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.util.Set;

public class InputController implements InputProcessor {

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Settings.rightKey || keycode == Settings.rightArrow) {
            SnakeGame.dimension = 0;
        }
        if (keycode == Settings.leftKey || keycode == Settings.leftArrow) {
            SnakeGame.dimension = 1;
        }
        if (keycode == Settings.upKey || keycode == Settings.upArrow) {
            SnakeGame.dimension = 2;
        }
        if (keycode == Settings.downKey || keycode == Settings.downArrow) {
            SnakeGame.dimension = 3;
        }

        if (keycode == Settings.escapeKey) Gdx.app.exit();

        if (keycode == Settings.spaceKey) Settings.isSpacePressed = true;

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (Settings.android) {
            if (Settings.isButton_visible) {
                // Buttons control
                if (screenX >= Settings.indent_size && screenX <= Settings.indent_size + Settings.button_size && screenY <= Gdx.graphics.getHeight() - Settings.indent_size && screenY >= Gdx.graphics.getHeight() - (Settings.indent_size + Settings.button_size)) {
                    keyDown(Settings.leftArrow);
                }
                if (screenX >= Settings.indent_size * 2 + Settings.button_size && screenX <= Settings.indent_size * 2 + Settings.button_size * 2 && screenY <= Gdx.graphics.getHeight() - Settings.indent_size && screenY >= Gdx.graphics.getHeight() - (Settings.indent_size + Settings.button_size)) {
                    keyDown(Settings.downArrow);
                }
                if (screenX >= Settings.indent_size * 3 + Settings.button_size * 2 && screenX <= Settings.indent_size * 3 + Settings.button_size * 3 && screenY <= Gdx.graphics.getHeight() - Settings.indent_size && screenY >= Gdx.graphics.getHeight() - (Settings.indent_size + Settings.button_size)) {
                    keyDown(Settings.rightArrow);
                }
                if (screenX >= Settings.indent_size * 2 + Settings.button_size && screenX <= Settings.indent_size * 2 + Settings.button_size * 2 && screenY <= Gdx.graphics.getHeight() - (Settings.indent_size * 2 + Settings.button_size) && screenY >= Gdx.graphics.getHeight() - (Settings.indent_size * 2 + Settings.button_size * 2)) {
                    keyDown(Settings.upArrow);
                }
            } else {
                // Gestures control
                if (screenX >= 0 && screenX <= Gdx.graphics.getWidth() / 3) {
                    keyDown(Settings.leftArrow);
                }
                if (screenX >= Gdx.graphics.getWidth() / 3 * 2 && screenX <= Gdx.graphics.getWidth()) {
                    keyDown(Settings.rightArrow);
                }
                if (screenX >= Gdx.graphics.getWidth() / 3 && screenX <= Gdx.graphics.getWidth() / 3 * 2 && screenY >= Gdx.graphics.getHeight() / 2) {
                    keyDown(Settings.downArrow);
                }
                if (screenX >= Gdx.graphics.getWidth() / 3 && screenX <= Gdx.graphics.getWidth() / 3 * 2 && screenY <= Gdx.graphics.getHeight() / 2) {
                    keyDown(Settings.upArrow);
                }
            }

            if (Settings.isGame_over) Settings.isSpacePressed = true;
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
