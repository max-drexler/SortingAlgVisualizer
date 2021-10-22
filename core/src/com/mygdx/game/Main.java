package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Arrays;

public class Main extends ApplicationAdapter {
    private static final int TOTAL_RECS = 30;
    private static final int REC_HEIGHT = 90;

    ShapeRenderer renderer;
    SortManager manager;

    int[] data;
    int padding = 10;
    int recWidth;

    private float timeSeconds = 0f;
    private float period = .2f;

    @Override
    public void create() {
        recWidth = Gdx.graphics.getWidth() / (TOTAL_RECS + padding);
        data = randomArray();
        renderer = new ShapeRenderer();
        manager = new SortManager(data,SortAlg.BUBBLE);
    }

    @Override
    public void render() {
        timeSeconds += Gdx.graphics.getDeltaTime();
        if(timeSeconds >= period){
            timeSeconds -= period;
            handleSteps();
        }
        ScreenUtils.clear(0, 0, 0, 1);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        for (int i = 0; i < data.length; i++) {
            renderer.rect(i * (this.recWidth + this.padding), 0, this.recWidth, Gdx.graphics.getHeight() / 100 * data[i], Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE);
        }

        renderer.end();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    private void handleSteps(){
        this.data = manager.step();
        /*int[] ret = manager.step();
        if(data.equals(ret))
            data = randomArray();
        else
            data = ret;
         */
    }
    private int[] randomArray() {
        int[] arr = new int[this.TOTAL_RECS - 5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (REC_HEIGHT * Math.random() + 1);
        }
        return arr;
    }
}
