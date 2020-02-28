package com.example.cmpt276as3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;

import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.cmpt276as3.Game_Logic.Options;
//Animation plays then auto enters main menu or by button
public class WelcomeScreen extends AppCompatActivity {
    private boolean start;
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable(){
        @Override
        public void run(){
            if(start) {
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intent);
                finish();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        start = true;
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("MyPref", 0);
        Options options = Options.getInstance();
        options.set_board(sharedPref.getInt("board_size",0), sharedPref.getInt("mine_count", 0));

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                start = false;
                timerHandler.removeCallbacks(timerRunnable);
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intent);
                finish();
            }
        });

        ((TextView)findViewById(R.id.Title)).setText(R.string.app_name);
        ((TextView)findViewById(R.id.Author)).setText(R.string.application_author);
        timerHandler.postDelayed(timerRunnable, 9200);
        ImageView image = findViewById(R.id.imageView);
        ImageView mine1 = findViewById(R.id.imageView2);
        ImageView mine2 = findViewById(R.id.imageView3);
        Animation fade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        Animation spin = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.spin);
        image.startAnimation(fade);
        mine1.startAnimation(spin);
        mine2.startAnimation(spin);
    }
}
