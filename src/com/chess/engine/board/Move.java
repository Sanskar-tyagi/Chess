package com.chess.engine.board;

import com.chess.engine.Pieces.Piece;

/**
 *
 * Represents a move made on the chess board.
 *
 */
public abstract class Move {
    // The board on which the move is being made.
    final Board board;

    // The piece that is being moved.
    final Piece movedPiece;

    // The destination coordinate to which the piece is being moved.
    final int destinationCoordinate;

    /**
     * Constructs a new move object.
     * 
     * @param board                 the board on which the move is being made
     * @param movedPiece            the piece that is being moved
     * @param destinationCoordinate the destination coordinate to which the piece is
     *                              being moved
     *
     */
    private Move(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
        this.board = board;
    }

    public static final class MajorMove extends Move {

        public MajorMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }

    public static final class AttackMove extends Move {

        final Piece attackedPiece;

        /**
         * Constructs a new attack move object.
         * 
         * @param board                 the board on which the move is being made
         * @param movedPiece            the piece that is being moved
         * @param destinationCoordinate the destination coordinate to which the piece is
         *                              being moved
         * @param attackedPiece         the piece being attacked
         */
        public AttackMove(final Board board,
                final Piece movedPiece,
                final int destinationCoordinate, final Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate);
            this.attackedPiece = attackedPiece;
        }
    }

}
