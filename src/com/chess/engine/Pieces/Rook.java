package com.chess.engine.Pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rook extends Piece {

    private final static int[] CANDIDATE_MOVES_VECTOR_COORDINATES={-8,-1,1,8};
    Rook(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves=new ArrayList<>();

        for(final int candidateCoordinateOffSet :CANDIDATE_MOVES_VECTOR_COORDINATES){
            int candidateDestinationCoordinate=this.piecePosition;
            while (BoardUtils.IsValidTileCoordinate(candidateDestinationCoordinate)){
                if (isFirstColumnException(candidateDestinationCoordinate,candidateCoordinateOffSet)||isEightColumnException(candidateDestinationCoordinate,candidateCoordinateOffSet)){break; }

                candidateDestinationCoordinate+=candidateCoordinateOffSet;
                if (BoardUtils.IsValidTileCoordinate(candidateDestinationCoordinate)){
                    final Tile candidateDestiantionTile=board.getTile(candidateDestinationCoordinate);  // Get the tile at the candidate destination coordinate.
                    if(!candidateDestiantionTile.isOccupied()){
                        legalMoves.add(new Move.MajorMove(board,this,candidateDestinationCoordinate));// If the candidate destination tile is not occupied, a new move object is created and added to the legalMoves list.
                    }
                    else{
                        final Piece pieceAtDestination=candidateDestiantionTile.getPiece(); // Get the Piece that has occupied the Tile.
                        final Alliance pieceAlliance= pieceAtDestination.getPieceAlliance();// Get its Alliance
                        if (this.pieceAlliance != pieceAlliance){ // If they are opposite alliances, create a new valid Move.
                            legalMoves.add(new Move.AttackMove(board,this,candidateDestinationCoordinate,pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }
    private static boolean isFirstColumnException(int CurrentPosition,final int candidateOffset ){
        return  BoardUtils.First_Column[CurrentPosition]&& (candidateOffset ==-1);
    }
    private static boolean isEightColumnException(int CurrentPosition,final int candidateOffset ){
        return  BoardUtils.Eight_Column[CurrentPosition]&& (candidateOffset ==-1);
    }
}
