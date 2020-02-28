package com.example.cmpt276as3.Game_Logic;
//Stores settings selected by the player. Has set dimensions to pick from and is singleton to support multiple activities accessing one instance.
public class Options {
    private Coordinate[] board_size;
    private int[] total_mines;

    private int selected_board = 0;
    private int selected_mines = 0;

    private static Options single_instance = null;
    private Options(){
        board_size = new Coordinate[3];

        board_size[0] = new Coordinate(6,4);
        board_size[1] = new Coordinate(10,5);
        board_size[2] = new Coordinate(15,6);

        total_mines = new int[4];
        total_mines[0] = 6; total_mines[1] = 10;
        total_mines[2] = 15;total_mines[3] = 20;

        selected_board = 0;
        selected_mines = 0;
    }
    public static Options getInstance(){
        if(single_instance == null)
            single_instance = new Options();

        return single_instance;
    }

    public void set_board(int board_size, int total_mines){
        selected_board = board_size;
        selected_mines = total_mines;
    }

    public Coordinate get_boardSize(){
        return board_size[selected_board];
    }

    public int get_mineCount(){
        return total_mines[selected_mines];
    }
}
