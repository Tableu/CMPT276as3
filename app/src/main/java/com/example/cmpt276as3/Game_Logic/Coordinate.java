package com.example.cmpt276as3.Game_Logic;
//Holds row and column data for board dimensions
public class Coordinate {
    private int x;
    private int y;
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }

}
