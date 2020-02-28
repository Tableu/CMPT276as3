package com.example.cmpt276as3;

import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class HelpScreen extends AppCompatActivity {
//Provides citation, author information and tips on how to play the game
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_help_screen);
    }
}
