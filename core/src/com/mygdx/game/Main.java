package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
    private static final int TOTAL_RECS = 30;
    private static final int REC_HEIGHT = 90;
    private static final float PERIOD = 0.1F;

    private ShapeRenderer renderer;
    private SortManager manager;

    private int[] data;
    private int padding = 10;
    private int recWidth;
    private float timeSeconds = 0.0F;

    public void create() {
        this.recWidth = Gdx.graphics.getWidth() / (30 + this.padding);
        this.data = this.randomArray();
        this.renderer = new ShapeRenderer();
        this.manager = new SortManager(this.data, SortAlg.BUBBLE);
    }

    public void render() {
        this.timeSeconds += Gdx.graphics.getDeltaTime();
        if (this.timeSeconds >= PERIOD) {
            this.timeSeconds -= PERIOD;
            this.handleSteps();
        }

        ScreenUtils.clear(0.0F, 0.0F, 0.0F, 1.0F);
        this.renderer.begin(ShapeType.Filled);

        for (int i = 0; i < this.data.length; ++i) {
            this.renderer.rect((float) (i * (this.recWidth + this.padding)), 0.0F, (float) this.recWidth, (float) ((Gdx.graphics.getHeight() - Gdx.graphics.getSafeInsetTop()) / 100 * this.data[i]), Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE);
        }

        this.renderer.end();
    }

    public void dispose() {
        this.renderer.dispose();
    }

    private void handleSteps() {
        if(this.manager.step()) {
            this.data = randomArray();
            manager.setArray(data);
        }

    }

    private int[] randomArray() {
        int[] arr = new int[30 - 5];
        int dist = REC_HEIGHT / arr.length;
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = (i + 1) * dist;
        }
        for(int i = 0; i < arr.length; ++i){
            int temp = arr[i];
            int ind = (int) (arr.length * Math.random());
            arr[i] = arr[ind];
            arr[ind] = temp;
        }
        return arr;
    }
}
