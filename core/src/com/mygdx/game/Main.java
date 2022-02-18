package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import java.util.Arrays;

public class Main extends Game {

    public static SortManager MANAGER;
    public static Skin SKIN;

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 480;
    private static final int DRAW_HEIGHT = SCREEN_HEIGHT - 80;

    private FitViewport view;
    private ShapeRenderer renderer;
    private SpriteBatch batch;

    private int[] data;
    private int padding = 10;
    private int recWidth;
    private float timeSeconds = 0.0F;
    private GUI gui;
    private float period = 0.25F;

    public void create() {
        Main.SKIN = new Skin(Gdx.files.internal("skin/uiskin.json"));
        this.view = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.recWidth = Gdx.graphics.getWidth() / (30 + this.padding);

        this.data = new int[Gdx.graphics.getWidth()/(this.recWidth+this.padding)];
        //populate array
        for (int i = 0; i < data.length; ++i) {
            data[i] = (i + 1) * DRAW_HEIGHT/data.length;
        }
        this.randomize(data);
        Main.MANAGER = new SortManager(this.data, SortAlg.BUBBLE);

        this.renderer = new ShapeRenderer();
        this.batch = new SpriteBatch();
        this.gui = new GUI(batch,view);
    }

    public void render() {
        period = gui.getSpeed()/20;
        this.timeSeconds += Gdx.graphics.getDeltaTime();
        if (this.timeSeconds >= period) {
            this.timeSeconds -= period;
            if(!gui.isPaused())
                this.handleSteps();
        }

        ScreenUtils.clear(0.0F, 0.0F, 0.0F, 1.0F);
        batch.setProjectionMatrix(view.getCamera().projection);
        this.renderer.begin(ShapeType.Filled);

        for (int i = 0; i < this.data.length; ++i) {
            this.renderer.rect((float) (i * (this.recWidth + this.padding)), 0.0F, (float) this.recWidth, this.data[i], Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE);
        }
        this.gui.render(Gdx.graphics.getDeltaTime());

        this.renderer.end();
    }

    @Override
    public void resize(int width, int height) {
        this.view.update(width,height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }


    public void dispose() {
        this.renderer.dispose();
        this.batch.dispose();
        this.gui.dispose();
    }

    private void handleSteps() {
        if(Main.MANAGER.step()) {
            this.randomize(this.data);
        }
    }

    private void randomize(int[] arr) {
        for(int i = 0; i < arr.length; ++i){
            int temp = arr[i];
            int ind = (int) (arr.length * Math.random());
            arr[i] = arr[ind];
            arr[ind] = temp;
        }
    }
}
