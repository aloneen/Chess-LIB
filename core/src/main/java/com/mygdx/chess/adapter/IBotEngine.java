package com.mygdx.chess.adapter;

import java.util.List;

/**
 * Adapter interface for chess engine integrations.
 */
public interface IBotEngine {
    /**
     * Provide the bot with current move history in UCI format.
     */
    void setMoveHistory(List<String> history);

    /**
     * Asks the engine for the best move, in UCI notation (e.g., "e2e4").
     */
    String getBestMove() throws Exception;

    /**
     * Dispose of any engine resources.
     */
    void dispose();
}
