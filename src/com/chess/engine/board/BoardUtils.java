package com.chess.engine.board;

public class BoardUtils {
    public static boolean[] First_Column=initColumn(0);
    public static boolean[] Second_Column=initColumn(1);
    public static boolean[] Seventh_Column=initColumn(6);
    public static boolean[] Eight_Column=initColumn(7);

    public static final int Num_Tiles=64;
    public static final int Num_Tiles_Per_Row=8;

    private static boolean[] initColumn(int columnNumber) {
        final boolean column []=new boolean[Num_Tiles];
        while(columnNumber<Num_Tiles){
            column[columnNumber]=true;
            columnNumber+=Num_Tiles_Per_Row;
        }
        return column;
    }

    private  BoardUtils(){
        throw new RuntimeException("You Can't Initiate The BoardUtils! HuH. ");
    }
    public static boolean IsValidTileCoordinate(final int coordinate) {
        return coordinate >= 0 && coordinate < Num_Tiles;
    }
}
