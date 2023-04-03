package com.chess.engine.Pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.Collection;

public abstract class Piece {
        //EveryPiece will have its own coordinate
        //And a piece enum Alliance to determine it is on Black or white
    protected final int piecePosition;
    public int getPiecePosition(){
        return this.piecePosition;
    }
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;
        Piece(final int piecePosition,final  Alliance pieceAlliance){
            this.pieceAlliance=pieceAlliance;
            this.piecePosition=piecePosition;
            this.isFirstMove=false;
        }
        public Alliance pieceAlliance(){
            return this.pieceAlliance;
        }
        public boolean isFirstMove(){
            return  this.isFirstMove;
        }
        public abstract Collection<Move> calculateLegalMoves(final Board board); // To get the collection of Legal Moves of a piece
}
