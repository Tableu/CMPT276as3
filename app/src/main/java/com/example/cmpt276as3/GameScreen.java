package com.example.cmpt276as3;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.cmpt276as3.Game_Logic.Coordinate;
import com.example.cmpt276as3.Game_Logic.Minesweeper_Board;
import com.example.cmpt276as3.Game_Logic.Options;
//Where the game is played
public class GameScreen extends AppCompatActivity {
    private Button[][] buttons;
    private Options options = Options.getInstance();
    private Minesweeper_Board board;
    private Coordinate board_dim = options.get_boardSize();
    private TextView scan_count;
    private TextView mines_found;
    private Resources res;
    private LinearLayout screen;

    private final int ROWS = board_dim.getY();
    private final int COLUMNS = board_dim.getX();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game_screen);

        res = getResources();
        board = new Minesweeper_Board(options);
        buttons = new Button[ROWS][COLUMNS];
        scan_count = findViewById(R.id.scanCount);
        scan_count.setText(res.getString(R.string.scan_count,0));
        mines_found = findViewById(R.id.minesFound);
        mines_found.setText(res.getString(R.string.mines_left,0, options.get_mineCount()));
        make_board();
    }

    private void make_board(){
        TableLayout table = findViewById((R.id.table));
        for(int row = 0; row < ROWS; row++){
            TableRow table_row = new TableRow(this);
            table_row.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT, 1.0f));
            table.addView(table_row);
            for(int column = 0; column < COLUMNS; column++){
                final int final_column = column;
                final int final_row = row;

                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.MATCH_PARENT, 1.0f));

                button.setPadding(0,0,0,0);
                button.setBackground(getDrawable(R.drawable.button));
                button.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        button_clicked(final_column, final_row, view);
                    }
                });
                table_row.addView(button);
                table_row.getId();
                buttons[row][column] = button;
            }
        }
    }

    private void button_clicked(int column, int row, View view) {
        Button button = buttons[row][column];

        if(board.mine_found(column, row)){
            lock_size();
            int width = button.getWidth();
            int height = button.getHeight();
            Bitmap original_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.creeperface);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(original_bitmap, width, height, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, scaledBitmap));
            //Update scan numbers
            mines_found.setText(res.getString(R.string.mines_left, board.getMines_found(),options.get_mineCount()));
            update_scans(column, row);
            if(board.check_board()){
                on_win();
            }
        }else{
            int[] location = new int[2];
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.radarfade);
            view.getLocationOnScreen(location);
            ImageView image = findViewById(R.id.fade);
            image.setX(location[0]-74);
            image.setY(location[1]-59);
            image.setVisibility(View.VISIBLE);
            image.startAnimation(animation);
            image.setVisibility(View.INVISIBLE);
            button.setText(Integer.toString(board.scan(row, column)));
            button.setTextColor(Color.parseColor("#E9E3E3"));
            scan_count.setText(res.getString(R.string.scan_count, board.getScan_count()));
        }
    }

    private void update_scans(int column, int row) {
        Button button;
        int scan;
        for(int x = 0; x < ROWS; x++){
            button = buttons[x][column];
            if(button.getText() != ""){
                scan = Integer.parseInt(button.getText().toString());
                scan--;
                button.setText(Integer.toString(scan));
            }
        }
        for(int x = 0; x < COLUMNS; x++){
            button = buttons[row][x];
            if(button.getText() != ""){
                scan = Integer.parseInt(button.getText().toString());
                scan--;
                button.setText(Integer.toString(scan));
            }
        }
    }

    private void lock_size(){
        for (int row = 0; row < ROWS; row++){
            for(int column = 0; column < COLUMNS; column++){
                Button button = buttons[row][column];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }

    private void on_win(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.creeperface);
        builder.setMessage(R.string.dialog_text);
        builder.setTitle(R.string.dialog_title);
        builder.setPositiveButton(R.string.dialog_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
