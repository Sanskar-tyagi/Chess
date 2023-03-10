
package com.chess.engine.board;
import com.chess.engine.Pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;
public abstract class Tile {
    //Member field for Tile Number
    protected final int tileCoordinate; //  Constructor for creating individual tiles.
    // Protected to make it accessible only to subclasses.
    // Final to ensure that it can only be set once, at the time of construction.
    // Protecting the tileCoordinate field further ensures that clients using our API
    // cannot create any instance of this class, making it immutable.

private static final Map<Integer,EmptyTile> Empty_Tiles =createAllPossibleEmptyTiles();
    /**
     * Creates a map containing all possible empty tiles on the chess board.
     * The map is immutable to prevent modification after creation.
     *
     * @return an immutable map containing all empty tiles
     */
    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        // Create a new HashMap to store empty tiles.
        final Map<Integer,EmptyTile> emptyTileMap=new HashMap<>();
        for(int i=0;i<64;i++){
            emptyTileMap.put(i,new EmptyTile(i));
        }
        // Create an immutable copy of the map, using the Guava ImmutableMap method.
        return ImmutableMap.copyOf(emptyTileMap);
    }

    /**
     *  Creates a new tile with the specified tile coordinate and piece, if any.
     * If the piece is null, returns one of the cached empty tiles instead.
     *
     * Parameters:
     * -tileCoordinate: the coordinate of the tile to create
     * - piece: the piece to place on the tile, or null for an empty tile
     */
    public static Tile createTile(final int tileCoordinate, final Piece piece){
        return piece!=null ? new OccupiedTile(tileCoordinate,piece):Empty_Tiles.get(tileCoordinate);
    }
    private Tile(int tileCoordinate){
        this.tileCoordinate=tileCoordinate;
        // So whenever we construct a new instance of the Tile it will be assigned to tile coordinate.
    }
    public abstract boolean isOccupied(); // Defining a method to be abstract means that it won't be defined in this class
                                         //rather it will be defined in a subclass

    public  abstract Piece getPiece(); // To return the piece that has occupied that tile.

    // The above two are key methods of the class to know whether the class is occupied or not and if it is by which piece.
    // And if we do we will occupy that piece.
    public static final class EmptyTile extends Tile{ //First Subclass
            EmptyTile( final int coordinate){
            super(coordinate);
        }

        @Override
        public  boolean isOccupied(){    //As we are on empty tile , so it is not Occupied Currently
            return false;
        }
        @Override
        public Piece getPiece(){    //As we are on empty tile So there is no Piece
            return null;
    }

    }
    public static final class OccupiedTile extends Tile{//Another Subclass
     private final Piece pieceOnTile;
    OccupiedTile(int tileCoordinate ,  Piece pieceOnTile ){
    super(tileCoordinate);
    this.pieceOnTile=pieceOnTile;
}
        @Override
        public  boolean isOccupied(){
            return true;
        }
        @Override
        public Piece getPiece(){
            return this.pieceOnTile;
        }
    }
}





