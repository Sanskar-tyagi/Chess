package com.chess.engine.board;

import com.chess.engine.Alliance;
import com.chess.engine.Pieces.*;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Board {

    private final List <Tile> gameBoard;
    private final Collection<Piece> whitePiece;
    private final Collection<Piece> blackPiece;
        private Board(Builder builder){
            this.gameBoard= createGameBoard(builder);
            this.whitePiece= calculateActivePieces(this.gameBoard,Alliance.WHITE);
            this.blackPiece= calculateActivePieces(this.gameBoard,Alliance.BLACK);
        }

    private static Collection<Piece> calculateActivePieces(List<Tile> gameBoard, Alliance alliance) {
    final List<Piece>activePieces=new ArrayList<>();
    for(final Tile tile:gameBoard){
        if(tile.isOccupied()){
            final Piece piece=tile.getPiece();
            if(piece.pieceAlliance()== alliance){
                activePieces.add(piece);
            }
        }
        }
    return ImmutableList.copyOf(activePieces);
    }
    public Tile getTile(final int tileCoordinate) {
            return  gameBoard.get(tileCoordinate);
        }
        private  static  List<Tile>createGameBoard(final Builder builder){
            final Tile[] tile=new Tile[BoardUtils.Num_Tiles];
            for(int i=0;i<BoardUtils.Num_Tiles;i++){
                tile[i]=Tile.createTile(i,builder.boardConfig.get(i));
            }
            return ImmutableList.copyOf(tile);
        }
        public static Board createStandardBoard(){
            final Builder builder=new Builder();
            // Black Layout
            builder.setPiece(new Rook(Alliance.BLACK, 0));
            builder.setPiece(new Knight(Alliance.BLACK, 1));
            builder.setPiece(new Bishop(Alliance.BLACK, 2));
            builder.setPiece(new Queen(Alliance.BLACK, 3));
            builder.setPiece(new King(Alliance.BLACK, 4));
            builder.setPiece(new Bishop(Alliance.BLACK, 5));
            builder.setPiece(new Knight(Alliance.BLACK, 6));
            builder.setPiece(new Rook(Alliance.BLACK, 7));
            builder.setPiece(new Pawn(Alliance.BLACK, 8));
            builder.setPiece(new Pawn(Alliance.BLACK, 9));
            builder.setPiece(new Pawn(Alliance.BLACK, 10));
            builder.setPiece(new Pawn(Alliance.BLACK, 11));
            builder.setPiece(new Pawn(Alliance.BLACK, 12));
            builder.setPiece(new Pawn(Alliance.BLACK, 13));
            builder.setPiece(new Pawn(Alliance.BLACK, 14));
            builder.setPiece(new Pawn(Alliance.BLACK, 15));
            // White Layout
            builder.setPiece(new Pawn(Alliance.WHITE, 48));
            builder.setPiece(new Pawn(Alliance.WHITE, 49));
            builder.setPiece(new Pawn(Alliance.WHITE, 50));
            builder.setPiece(new Pawn(Alliance.WHITE, 51));
            builder.setPiece(new Pawn(Alliance.WHITE, 52));
            builder.setPiece(new Pawn(Alliance.WHITE, 53));
            builder.setPiece(new Pawn(Alliance.WHITE, 54));
            builder.setPiece(new Pawn(Alliance.WHITE, 55));
            builder.setPiece(new Rook(Alliance.WHITE, 56));
            builder.setPiece(new Knight(Alliance.WHITE, 57));
            builder.setPiece(new Bishop(Alliance.WHITE, 58));
            builder.setPiece(new Queen(Alliance.WHITE, 59));
            builder.setPiece(new King(Alliance.WHITE, 60));
            builder.setPiece(new Bishop(Alliance.WHITE, 61));
            builder.setPiece(new Knight(Alliance.WHITE, 62));
            builder.setPiece(new Rook(Alliance.WHITE, 63));
            //white to move
            builder.setMoveMaker(Alliance.WHITE);
            //build the board
            return builder.build();
        }
        public  static class Builder{
        Map<Integer, Piece> boardConfig;
           Alliance nextMoveMaker;
           public Builder(){

           }

        public Builder setPiece(final Piece piece){
               this.boardConfig.put(piece.getPiecePosition(),piece);
               return this;
    }
    public Builder setMoveMaker(final Alliance nextMoveMaker){
               this.nextMoveMaker=nextMoveMaker;
                return this;
         }
                 public Board build(){
                return new Board(this);
    }
    }
}
