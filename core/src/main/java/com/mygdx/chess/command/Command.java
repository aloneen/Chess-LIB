package com.mygdx.chess.command;

/**
 * Represents an executable and undoable action.
 */
public interface Command {
    void execute();
    void undo();
}
