// ==== util/AssetManager.java ====
package com.mygdx.chess.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton for loading and caching game assets (textures, sounds, etc.).
 */
public class AssetManager {
    private static final AssetManager INSTANCE = new AssetManager();
    private final Map<String, Texture> textures = new HashMap<>();

    private AssetManager() { }

    public static AssetManager getInstance() {
        return INSTANCE;
    }

    /**
     * Returns a cached Texture, loading it on first request.
     */
    public Texture getTexture(String path) {
        return textures.computeIfAbsent(path, p -> new Texture(Gdx.files.internal(p)));
    }

    /**
     * Dispose of all loaded assets.
     */
    public void dispose() {
        textures.values().forEach(Texture::dispose);
        textures.clear();
    }
}
