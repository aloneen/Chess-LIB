
package com.mygdx.chess.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.chess.ChessGame;

/**
 * Displays end-of-game status and options.
 */
public class GameOverScreen implements Screen {
    private final ChessGame game;
    private final Stage stage;
    private final Skin  skin;

    public GameOverScreen(ChessGame game, String message) {
        this.game  = game;
        this.stage = new Stage(new ScreenViewport());
        this.skin  = new Skin(Gdx.files.internal("skins/uiskin.json"));

        Image bg = new Image(new com.badlogic.gdx.graphics.Texture(
            Gdx.files.internal("images/main_bg.jpg")));
        bg.setFillParent(true);
        stage.addActor(bg);

        Table table = new Table(); table.setFillParent(true); stage.addActor(table);
        Label msg = new Label(message, skin);
        msg.setFontScale(1.5f);
        table.add(msg).padBottom(30f).row();

        TextButton playW = new TextButton("Play as White", skin);
        playW.addListener(new ClickListener(){ @Override public void clicked(InputEvent e, float x, float y) { game.setScreen(new GameScreen(game, false)); }});
        TextButton playB = new TextButton("Play as Black", skin);
        playB.addListener(new ClickListener(){ @Override public void clicked(InputEvent e, float x, float y) { game.setScreen(new GameScreen(game, true)); }});
        TextButton mainM = new TextButton("Main Menu", skin);
        mainM.addListener(new ClickListener(){ @Override public void clicked(InputEvent e, float x, float y) { game.setScreen(new MainMenuScreen(game)); }});
        TextButton quit  = new TextButton("Quit", skin);
        quit.addListener(new ClickListener(){ @Override public void clicked(InputEvent e, float x, float y) { Gdx.app.exit(); }});

        table.add(playW).width(180).pad(5).row();
        table.add(playB).width(180).pad(5).row();
        table.add(mainM).width(180).pad(5).row();
        table.add(quit).width(180).pad(5);
    }

    @Override public void show()   { Gdx.input.setInputProcessor(stage); }
    @Override public void render(float dt) { Gdx.gl.glClearColor(0,0,0,1); Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); stage.act(dt); stage.draw(); }
    @Override public void resize(int w,int h) { stage.getViewport().update(w,h,true); }
    @Override public void pause()  {}
    @Override public void resume() {}
    @Override public void hide()   {}
    @Override public void dispose(){ stage.dispose(); skin.dispose(); }
}
