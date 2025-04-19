package com.mygdx.chess.factory;

import com.mygdx.chess.actors.ChessPiece;


/**
 * Factory Method for creating ChessPiece instances, including promotion.
 */
public class PieceFactory {
    /**
     * Creates a ChessPiece of given color and type at coordinates x,y.
     * @param color "white" or "black"
     * @param type  "pawn", "rook", "knight", "bishop", "queen", or "king"
     */
    public static ChessPiece create(String color, String type, int x, int y) {
        switch (type.toLowerCase()) {
            case "pawn":   return new ChessPiece(color, "pawn",   x, y);
            case "rook":   return new ChessPiece(color, "rook",   x, y);
            case "knight": return new ChessPiece(color, "knight", x, y);
            case "bishop": return new ChessPiece(color, "bishop", x, y);
            case "queen":  return new ChessPiece(color, "queen",  x, y);
            case "king":   return new ChessPiece(color, "king",   x, y);
            default:
                throw new IllegalArgumentException("Unknown piece type: " + type);
        }
    }
}
