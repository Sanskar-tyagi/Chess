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

import static com.chess.engine.board.Move.*;

public class Knight extends Piece{

    private final static int [] Candidate_Moves_Coordinate={-17,-15,-10,-6,6,10,15,17};  // Offset coordinates for the possible moves by a knight
    Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }
    // Calculates legal moves for the Knight piece.
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

         final List<Move>legalMoves=new ArrayList<>(); // List to store valid moves for the piece being considered.

         for(final  int CurrentCandidateOffset:Candidate_Moves_Coordinate){
            final int candidateDestinationCoordinate=this.piecePosition+CurrentCandidateOffset; // Calculate the possible move that the current Knight Piece can make.
                if(BoardUtils.IsValidTileCoordinate(candidateDestinationCoordinate))  {   // Check whether the candidate destination coordinate is a valid coordinate on the board.
                if(isFirstColumnExclusion(this.piecePosition,CurrentCandidateOffset)
                        || isSecondColumnExclusion(this.piecePosition,CurrentCandidateOffset)
                        || isSeventhColumnExclusion(this.piecePosition,CurrentCandidateOffset)
                        || isEighthColumnExclusion(this.piecePosition,CurrentCandidateOffset)){
                    continue;
                }
                final Tile candidateDestinationTile=board.getTile(candidateDestinationCoordinate);  // Get the tile at the candidate destination coordinate.
                if(!candidateDestinationTile.isOccupied()){
                legalMoves.add(new MajorMove(board,this,candidateDestinationCoordinate));// If the candidate destination tile is not occupied, a new move object is created and added to the legalMoves list.
                }
                else{
                     final Piece pieceAtDestination=candidateDestinationTile.getPiece(); // Get the Piece that has occupied the Tile.
                    final Alliance pieceAlliance= pieceAtDestination.pieceAlliance();// Get its Alliance
                    if (this.pieceAlliance != pieceAlliance){ // If they are opposite alliances, create a new valid Move.
                        legalMoves.add(new AttackMove(board,this,candidateDestinationCoordinate,pieceAtDestination));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves); // Return an immutable list of legal moves for the Knight piece.
    }
/**
 * Methods To capture Edge Cases for Our Knight Algorithm
 * The first edge case checks if the knight is in the first column, which will break our algorithm for getting valid moves.
 * The second edge case checks if the knight is in the second column and can only move to certain positions to avoid going off the board.
 * The third edge case checks if the knight is in the seventh column and can only move to certain positions to avoid going off the board.
 * The fourth edge case checks if the knight is in the eighth column and can only move to certain positions to avoid going off the board.
 */
    private static boolean isFirstColumnExclusion(final  int CurrentPosition,final int candidateOffset){
    // If the knight is on the first column, we need to check if the candidate move is valid
    return BoardUtils.First_Column[CurrentPosition] && (candidateOffset == -17  ||  candidateOffset == -10
            ||  candidateOffset==6 || candidateOffset==15);
}

    /**
     * Checks if the knight is on the first column and if the candidate move would cause the algorithm to break.
     * @param CurrentPosition the current position of the knight on the board
     * @param candidateOffset the offset being tested for a valid move
     * @return true if the move is invalid, false otherwise
     */
    private static boolean isSecondColumnExclusion(final  int CurrentPosition,final int candidateOffset){
        // If the knight is on the second column, we need to check if the candidate move is valid
        return BoardUtils.Second_Column[CurrentPosition] && (candidateOffset == -10 || candidateOffset == 6) ;
    }

    /**
     * Checks if the knight is on the second column and if the candidate move would cause the algorithm to break.
     * @param CurrentPosition the current position of the knight on the board
     * @param candidateOffset the offset being tested for a valid move
     * @return true if the move is invalid, false otherwise
     */

    private static boolean isSeventhColumnExclusion(final  int CurrentPosition,final int candidateOffset){
        // If the knight is on the seventh column, we need to check if the candidate move is valid
        return BoardUtils.Seventh_Column[CurrentPosition] && (candidateOffset == -6  ||  candidateOffset == 10);
    }
    /**
     * Checks if the knight is on the seventh column and if the candidate move would cause the algorithm to break.
     * @param CurrentPosition the current position of the knight on the board
     * @param candidateOffset the offset being tested for a valid move
     * @return true if the move is invalid, false otherwise
     */
    private static boolean isEighthColumnExclusion(final  int CurrentPosition,final int candidateOffset){
        // If the knight is on the eighth column, we need to check if the candidate move is valid
        return BoardUtils.Eight_Column[CurrentPosition] && ( candidateOffset == -15   ||  candidateOffset == -6  ||  candidateOffset == 10 || candidateOffset == 17);
    }
    /*
     * Checks if the knight is on the eighth column and if the candidate move would cause the algorithm to break.
     *  @param CurrentPosition the current position of the knight on the board
     *  @param candidateOffset the offset being tested for a valid move
     *  @return true if the move is invalid, false otherwise
     */
}
