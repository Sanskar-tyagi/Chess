package com.chess.engine.Pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;
import static com.chess.engine.board.Move.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class King extends Piece {

    private static final int [] Candidate_Move_Coordinates={-9,-8,-7,-1,1,7,8,9};
    King(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves=new ArrayList<>();
        for(final int CurrentCandidateOffset: Candidate_Move_Coordinates){
            final int candidateDestinationCoordinate=this.piecePosition+CurrentCandidateOffset;
if(){
    continue;
}
                if(BoardUtils.IsValidTileCoordinate(candidateDestinationCoordinate)){
                    final Tile candidateDestinationTile=board.getTile(candidateDestinationCoordinate);
                    if(!candidateDestinationTile.isOccupied()){
                        legalMoves.add(new MajorMove(board,this,candidateDestinationCoordinate));// If the candidate destination tile is not occupied, a new move object is created and added to the legalMoves list.
                    }
                    else{
                        final Piece pieceAtDestination=candidateDestinationTile.getPiece(); // Get the Piece that has occupied the Tile.
                        final Alliance pieceAlliance= pieceAtDestination.pieceAlliance();// Get its Alliance
                        if (this.pieceAlliance != pieceAlliance){ // If they are opposite alliances, create a new valid Move.
                            legalMoves.add(new  AttackMove(board,this,candidateDestinationCoordinate,pieceAtDestination));
                        }
                    }
                }

        }
        return ImmutableList.copyOf(legalMoves);
    }
    private static boolean isFirstColumnExclusion(final  int CurrentPosition,final int candidateOffset){
        // If the knight is on the first column, we need to check if the candidate move is valid
        return BoardUtils.First_Column[CurrentPosition] && (candidateOffset == -17  ||  candidateOffset == -10
                ||  candidateOffset==6 || candidateOffset==15);
    }
    private static boolean isEighthColumnExclusion  (final  int CurrentPosition,final int candidateOffset){
        // If the knight is on the second column, we need to check if the candidate move is valid
        return BoardUtils.Second_Column[CurrentPosition] && (candidateOffset == -10 || candidateOffset == 6) ;
    }

}
