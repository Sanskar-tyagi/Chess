package com.chess.engine.Pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.chess.engine.board.Move.*;

public class Pawn extends  Piece{

    private static final int [] Candidate_Move_Coordinates={8,16,7,9};
    Pawn(final int piecePosition,final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves=new ArrayList<>();
            for(final  int CurrentCandidateOffset: Candidate_Move_Coordinates){
              final   int candidateDestinationCoordinate=this.piecePosition+ (this.pieceAlliance.getDirection()*CurrentCandidateOffset);
                if(!BoardUtils.IsValidTileCoordinate(candidateDestinationCoordinate)){
                    continue;
                }
                if(CurrentCandidateOffset==8 && !board.getTile(candidateDestinationCoordinate).isOccupied()){
                    //Need to work on promotion Of Pawn
                legalMoves.add(new MajorMove(board,this,candidateDestinationCoordinate));
                } else if (CurrentCandidateOffset == 16 && this.isFirstMove() &&
                        (BoardUtils.Second_Row[this.piecePosition] && this.pieceAlliance.isBlack())||
                        (BoardUtils.Seventh_Row[this.piecePosition] && this.pieceAlliance.isWhite())) {
                final  int behindCandidateDestinationCoordinate=this.piecePosition+(this.pieceAlliance.getDirection()*8);
                if(!board.getTile(behindCandidateDestinationCoordinate).isOccupied() &&
                        !board.getTile(candidateDestinationCoordinate).isOccupied()){
                    legalMoves.add(new MajorMove(board,this,candidateDestinationCoordinate));
                }
                }
                else if (CurrentCandidateOffset==7
                        && !((BoardUtils.Eight_Column[this.piecePosition]
                        && this.pieceAlliance.isWhite()) ||
                        (BoardUtils.First_Column[this.piecePosition]
                        && this.pieceAlliance.isBlack()))) {
                    if (board.getTile(candidateDestinationCoordinate).isOccupied()){
                        final Piece pieceOnCandidate=board.getTile(candidateDestinationCoordinate).getPiece();
                        if (this.pieceAlliance!=pieceOnCandidate.pieceAlliance){
                            //todo add attack
                            legalMoves.add(new MajorMove(board,this,candidateDestinationCoordinate));
                        }
                    }
                }else if (CurrentCandidateOffset==9
                        && !((BoardUtils.First_Column[this.piecePosition]
                        && this.pieceAlliance.isWhite()) ||
                        (BoardUtils.Eight_Column[this.piecePosition]
                                && this.pieceAlliance.isBlack()))) {
                    if (board.getTile(candidateDestinationCoordinate).isOccupied()){
                        final Piece pieceOnCandidate=board.getTile(candidateDestinationCoordinate).getPiece();
                        if (this.pieceAlliance!=pieceOnCandidate.pieceAlliance){
                            //todo add attack
                            legalMoves.add(new MajorMove(board,this,candidateDestinationCoordinate));
                        }
                        }
                }
            }
        return ImmutableList.copyOf(legalMoves);
    }
}
