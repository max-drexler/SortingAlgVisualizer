package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GUI {

    private Stage stage;
    private ImageButton pausePlayButton;
    private Slider speedSlider;

    public GUI(SpriteBatch batch, Viewport view){
        stage = new Stage(view, batch);

        Table table = new Table();
        table.align(Align.topLeft);
        table.setFillParent(true);

        SelectBox<String> sortSelector = new SelectBox<String>(Main.SKIN);
        sortSelector.setItems(new Array<String>(SortAlg.names()));
        table.add(sortSelector).pad(10f);

        Label sliderLabel = new Label("Speed:",Main.SKIN);
        table.add(sliderLabel).pad(10f);

        this.speedSlider = new Slider(1,10,1,false,Main.SKIN);
        this.speedSlider.setValue(5f);
        table.add(this.speedSlider).pad(10f);

        SpriteDrawable pauseDrawable = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("pause-icon.png"))));
        SpriteDrawable playDrawable = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("play-icon.png"))));
        SpriteDrawable pressDrawable = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("press-icon.png"))));
        this.pausePlayButton = new ImageButton(new ImageButton.ImageButtonStyle(new Button.ButtonStyle(pauseDrawable,pressDrawable,playDrawable)));
        table.add(pausePlayButton).size(50,50).pad(10f);

        pausePlayButton.addListener(new ClickListener(){
           @Override
           public void clicked(InputEvent e, float x, float y){
               boolean checked = ((ImageButton)e.getListenerActor()).isChecked();
               ((ImageButton)e.getListenerActor()).setChecked(checked);
           }
        });

        sortSelector.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Main.MANAGER.setSort(SortAlg.valueOf(((SelectBox<String>)actor).getSelected()));
                ImageButton plpaButton = ((ImageButton)actor.getParent().getChild(3)); // change if add new ui elem
                if(!plpaButton.isChecked())
                    plpaButton.setChecked(true);
            }
        });

        stage.addActor(table);
    }

    public void render(float delta){
        Gdx.input.setInputProcessor(stage);
        stage.act(delta);
        stage.draw();
    }

    public boolean isPaused(){
        return this.pausePlayButton.isChecked();
    }

    public float getSpeed(){
        return this.speedSlider.getValue();
    }

    public void dispose(){
        this.stage.dispose();
    }
}
