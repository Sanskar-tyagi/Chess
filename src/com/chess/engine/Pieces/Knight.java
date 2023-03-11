package com.chess.engine.Pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{

    private final static int [] Candidate_Moves_Coordinate={-17,-15,-10,-6,6,10,15,17};  // Offset coordinates for the possible moves by a knight
    Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }
    // Calculates legal moves for the Knight piece.
    @Override
    public List<Move> calculateLegalMoves(Board board) {

         int candidateDestinationCoordinate;  // Variable to calculate the destination coordinate for a candidate move.
         final List<Move>legalMoves=new ArrayList<>(); // List to store valid moves for the piece being considered.
         for(final  int CurrentCandidate:Candidate_Moves_Coordinate){
            candidateDestinationCoordinate=this.piecePosition+CurrentCandidate; // Calculate the possible move that the current Knight Piece can make.
            if(true /*Is a valid Coordinate*/)  {   // Check whether the candidate destination coordinate is a valid coordinate on the board.
                final Tile candidateDestiantionTile=board.getTile(candidateDestinationCoordinate);  // Get the tile at the candidate destination coordinate.
                if(!candidateDestiantionTile.isOccupied()){
                legalMoves.add(new Move());// If the candidate destination tile is not occupied, a new move object is created and added to the legalMoves list.
                }
                else{
                     final Piece pieceAtDestination=candidateDestiantionTile.getPiece(); // Get the Piece that has occupied the Tile.
                    final Alliance pieceAlliance= pieceAtDestination.getPieceAlliance();// Get its Alliance
                    if (this.pieceAlliance != pieceAlliance){ // If they are opposite alliances, create a new valid Move.
                        legalMoves.add(new Move());
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves); // Return an immutable list of legal moves for the Knight piece.
    }
}

