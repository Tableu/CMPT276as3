package com.example.cmpt276as3;

import android.view.Window;
import com.example.cmpt276as3.Game_Logic.Options;
import android.content.SharedPreferences;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
//User can change game settings here
public class OptionsScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_options_screen);

        Options options = Options.getInstance();

        switch(options.get_boardSize().getX()){
            case 6:
                ((RadioGroup)findViewById(R.id.board_size)).check(R.id.fourbysix);
                break;
            case 10:
                ((RadioGroup)findViewById(R.id.board_size)).check(R.id.fivebyten);
                break;
            case 15:
                ((RadioGroup)findViewById(R.id.board_size)).check(R.id.sixbyfifteen);
                break;
        }

        switch(options.get_mineCount()){
            case 6:
                ((RadioGroup)findViewById(R.id.MineRadio)).check(R.id.sixMines);
                break;
            case 10:
                ((RadioGroup)findViewById(R.id.MineRadio)).check(R.id.tenMines);
                break;
            case 15:
                ((RadioGroup)findViewById(R.id.MineRadio)).check(R.id.fifteenMines);
                break;
            case 20:
                ((RadioGroup)findViewById(R.id.MineRadio)).check(R.id.twentyMines);
                break;
        }

    }
    @Override
    protected void onStop(){
        super.onStop();
        Options options = Options.getInstance();
        int board_size = 0;
        int mine_count = 0;
        switch(((RadioGroup)findViewById(R.id.board_size)).getCheckedRadioButtonId()){
            case R.id.fourbysix:
                board_size = 0;
                break;
            case R.id.fivebyten:
                board_size = 1;
                break;
            case R.id.sixbyfifteen:
                board_size = 2;
                break;
        }
        switch(((RadioGroup)findViewById(R.id.MineRadio)).getCheckedRadioButtonId()){
            case R.id.sixMines:
                mine_count = 0;
                break;
            case R.id.tenMines:
                mine_count = 1;
                break;
            case R.id.fifteenMines:
                mine_count = 2;
                break;
            case R.id.twentyMines:
                mine_count = 3;
                break;
        }
        options.set_board(board_size, mine_count);
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("board_size", board_size);
        editor.putInt("mine_count", mine_count);

        editor.commit();
    }
}
