package com.mygdx.chess.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

/**
 * Helper for drawing textures uniformly.
 */
public class TextureManager {
    private final AssetManager assets = AssetManager.getInstance();

    /**
     * Draws the given texture at board coords.
     */
    public void draw(SpriteBatch batch, String assetPath,
                     float x, float y, float width, float height) {
        Texture tex = assets.getTexture(assetPath);
        batch.draw(tex, x, y, width, height);
    }
}
