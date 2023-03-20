package com.surface2022.shake;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.LinkedList;
import java.util.Random;

public class SnakeGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private TextureAtlas textureAtlas;
    private Texture b_up_tex, b_down_tex, b_left_tex, b_right_tex;
    private Sprite snakeSprite, background, apple;
    private Sprite button_up, button_down, button_right, button_left;

    protected Label infoLabel, gameOver;
    protected BitmapFont font;
    protected Stage stage;

    int t = 0;
    int x, y;
    int score = 0;
    int width, height;

    private LinkedList<BodyCoords> body = new LinkedList<>();

    Random random = new Random();
    static short dimension = 0;

    @Override
    public void create() {
        if(Gdx.app.getType() == ApplicationType.Android) {
            Settings.android = true;
        }

        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        InputController myInput = new InputController();
        Gdx.input.setInputProcessor(myInput);

        if (Settings.android && Settings.isButton_visible) {
            // Textures
            b_up_tex = new Texture(Gdx.files.internal("button_up.png"));
            b_down_tex = new Texture(Gdx.files.internal("button_down.png"));
            b_right_tex = new Texture(Gdx.files.internal("button_right.png"));
            b_left_tex = new Texture(Gdx.files.internal("button_left.png"));

            // Button sprites
            button_left = new Sprite(b_left_tex);
            button_left.setSize(Settings.button_size, Settings.button_size);
            button_left.setPosition(Settings.indent_size, Settings.indent_size);
            button_left.setAlpha(0.5f);

            button_down = new Sprite(b_down_tex);
            button_down.setSize(Settings.button_size, Settings.button_size);
            button_down.setPosition(Settings.indent_size * 2 + Settings.button_size, Settings.indent_size);
            button_down.setAlpha(0.5f);

            button_right = new Sprite(b_right_tex);
            button_right.setSize(Settings.button_size, Settings.button_size);
            button_right.setPosition(Settings.indent_size * 3 + Settings.button_size * 2, Settings.indent_size);
            button_right.setAlpha(0.5f);

            button_up = new Sprite(b_up_tex);
            button_up.setSize(Settings.button_size, Settings.button_size);
            button_up.setPosition(Settings.indent_size * 2 + Settings.button_size, Settings.indent_size * 2 + Settings.button_size);
            button_up.setAlpha(0.5f);
        }

        // Text labels
        font = new BitmapFont();
        infoLabel = new Label(" ", new Label.LabelStyle(font, Color.BLACK));
        infoLabel.setPosition(10, height - 25);

        gameOver = new Label("GAME OVER", new Label.LabelStyle(font, Color.RED));
        gameOver.setPosition((float) width / 4, (float) height / 2);
        gameOver.setFontScale(6);
        gameOver.setVisible(false);

        stage = new Stage();
        stage.addActor(infoLabel);
        stage.addActor(gameOver);

        // Other work
        batch = new SpriteBatch();

        textureAtlas = new TextureAtlas(Gdx.files.internal("snake_atlas.atlas"));

        dimension = (short) random.nextInt(4);

        snakeSprite = textureAtlas.createSprite("snake_body");
        snakeSprite.setSize(Settings.snake_size_x, Settings.snake_size_y);
        y = height / 2;
        x = width / 2;

        apple = textureAtlas.createSprite("apple");
        apple.setSize(20, 20);
        apple.setPosition(random.nextInt(width - 20), random.nextInt(height - 20));

        background = textureAtlas.createSprite("background");
        background.setSize(width, height);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        int FPS = Gdx.graphics.getFramesPerSecond();

        if (Settings.isSpacePressed && Settings.isGame_over) {
            Settings.isSpacePressed = false;
            Settings.isGame_over = false;
            SpacePressed();
        }

        if (!Settings.isGame_over) {
            t += 1;

            switch (dimension) {
                case 0:
                    if (t >= Settings.speed) {
                        x += snakeSprite.getWidth();
                        t = 0;
                        if (body.size() > 0) {
                            BodyUpdate();
                            body.remove(body.size() - 1);
                        }
                    }
                    break;
                case 1:
                    if (t >= Settings.speed) {
                        x -= snakeSprite.getWidth();
                        t = 0;
                        if (body.size() > 0) {
                            BodyUpdate();
                            body.remove(body.size() - 1);
                        }
                    }
                    break;
                case 2:
                    if (t >= Settings.speed) {
                        y += snakeSprite.getWidth();
                        t = 0;
                        if (body.size() > 0) {
                            BodyUpdate();
                            body.remove(body.size() - 1);
                        }
                    }
                    break;
                case 3:
                    if (t >= Settings.speed) {
                        y -= snakeSprite.getWidth();
                        t = 0;
                        if (body.size() > 0) {
                            BodyUpdate();
                            body.remove(body.size() - 1);
                        }
                    }
                    break;
            }


            // Render
            batch.begin();

            background.draw(batch);

            apple.draw(batch);

            snakeSprite.setPosition(x, y);
            snakeSprite.draw(batch);

            for (int i = 0; i < body.size(); i++) {
                Sprite b = textureAtlas.createSprite("snake_body");
                b.setSize(Settings.snake_size_x, Settings.snake_size_y);
                b.setPosition(body.get(i).x_coord, body.get(i).y_coord);
                b.draw(batch);
            }

            if (Settings.android && Settings.isButton_visible) {
                button_up.draw(batch);
                button_down.draw(batch);
                button_right.draw(batch);
                button_left.draw(batch);
            }

            batch.end();


            // Collision
            if (snakeSprite.getX() <= 0 || snakeSprite.getX() >= width) {
                Settings.isGame_over = true;
                gameOver.setVisible(true);
                body = new LinkedList<>();
            }
            if (snakeSprite.getY() <= 0 || snakeSprite.getY() >= height) {
                Settings.isGame_over = true;
                gameOver.setVisible(true);
                body = new LinkedList<>();
            }
            for (int i = 0; i < body.size(); i++) {
                if (snakeSprite.getX() == body.get(i).x_coord && snakeSprite.getY() == body.get(i).y_coord) {
                    Settings.isGame_over = true;
                    gameOver.setVisible(true);
                    body = new LinkedList<>();
                }
            }

            if (Math.abs(snakeSprite.getX() - apple.getX()) <= Settings.apple_area && Math.abs(snakeSprite.getY() - apple.getY()) <= Settings.apple_area) {
                score += 10;
                apple.setPosition(random.nextInt(width - 20), random.nextInt(height - 20));
                BodyUpdate();
            }


            // Some information
            StringBuilder builder = new StringBuilder();
            builder.append(" FPS: ").append(FPS);
            builder.append(" | SCORE: ").append(score);
            infoLabel.setText(builder);
            stage.draw();
        } else stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();

        if (Settings.android && Settings.isButton_visible) {
            b_left_tex.dispose();
            b_right_tex.dispose();
            b_down_tex.dispose();
            b_up_tex.dispose();
        }
    }

    public void BodyUpdate() {
        switch (dimension) {
            case 0:
                body.add(0, new BodyCoords(x - snakeSprite.getWidth(), snakeSprite.getY()));
                break;
            case 1:
                body.add(0, new BodyCoords(x + snakeSprite.getWidth(), snakeSprite.getY()));
                break;
            case 2:
                body.add(0, new BodyCoords(snakeSprite.getX(), y - snakeSprite.getHeight()));
                break;
            case 3:
                body.add(0, new BodyCoords(snakeSprite.getX(), y + snakeSprite.getHeight()));
                break;
        }
    }

    private void SpacePressed() {
        Settings.isGame_over = false;
        gameOver.setVisible(false);
        score = 0;

        y = height / 2;
        x = width / 2;

        dimension = (short) random.nextInt(4);

        snakeSprite.setSize(Settings.snake_size_x, Settings.snake_size_y);
        snakeSprite.setPosition(x, y);

        apple.setPosition(random.nextInt(width - 20), random.nextInt(height - 20));
    }
}
