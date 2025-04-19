// File: MoveCommand.java
package com.mygdx.chess.command;

import com.mygdx.chess.actors.ChessPiece;
import com.mygdx.chess.model.IBoardModel;

/**
 * Command to move a piece, supporting undo.
 */
public class MoveCommand implements Command {
    private final IBoardModel board;
    private final ChessPiece piece;
    private final int fromX, fromY;
    private final int toX, toY;
    private ChessPiece captured;

    public MoveCommand(IBoardModel board, ChessPiece piece, int toX, int toY) {
        this.board = board;
        this.piece = piece;
        this.fromX = piece.getXPos();
        this.fromY = piece.getYPos();
        this.toX = toX;
        this.toY = toY;
    }

    @Override
    public void execute() {
        // capture if present
        for (ChessPiece p : board.getPieces()) {
            if (p.getXPos() == toX && p.getYPos() == toY && !p.equals(piece)) {
                captured = p;
                board.getPieces().remove(p);
                break;
            }
        }
        piece.setPosition(toX, toY);
        board.getGameLogic().toggleTurn();
    }

    @Override
    public void undo() {
        // restore position
        piece.setPosition(fromX, fromY);
        board.getGameLogic().toggleTurn();
        // restore captured piece
        if (captured != null) {
            board.getPieces().add(captured);
            captured = null;
        }
    }
}
