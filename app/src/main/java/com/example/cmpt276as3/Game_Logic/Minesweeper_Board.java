package com.example.cmpt276as3.Game_Logic;

import java.util.ArrayList;
import java.util.Random;
//Minecraft Board stores information on the current game iteration, providing the game logic to scan the board, check for mines and if the player has won.
public class Minesweeper_Board {
    private int board[][];
    private int rows;
    private int columns;
    private int total_mines;
    private int mines_found;
    private int scan_count;

    public Minesweeper_Board(Options options){
        rows = options.get_boardSize().getY();
        columns = options.get_boardSize().getX();
        total_mines = options.get_mineCount();
        mines_found = 0;
        scan_count = 0;

        board = new int[rows][columns];
        for(int x = 0; x < columns; x++){
            for(int y = 0; y < rows; y++){
                board[y][x] = 0;
            }
        }

        Random rand = new Random();
        int random_row;
        int random_column;
        int x = 0;

        while(x < total_mines){
            random_row = rand.nextInt(rows);
            random_column = rand.nextInt(columns);
            if(board[random_row][random_column] == 0) {
                board[random_row][random_column] = 1;
                x++;
            }
        }
    }

    public int scan(int rows, int columns){
        int count = 0;
        if(board[rows][columns] > 0)
            count++;
        for(int x = 0; x < this.rows; x++){
            if(board[x][columns] > 0 && x != rows)
                count++;
        }
        for(int x = 0; x < this.columns; x++){
            if(board[rows][x] > 0 && x != columns){
                count++;
            }
        }
        scan_count++;
        return count;
    }

    public boolean mine_found(int column, int row){
        if(board[row][column] == 1) {
            board[row][column] = 0;
            mines_found++;
            return true;
        }
        return false;
    }
    public boolean check_board(){
        if(mines_found == total_mines)
            return true;

        return false;
    }

    public int getScan_count() {
        return scan_count;
    }

    public int getMines_found(){
        return mines_found;
    }
}
