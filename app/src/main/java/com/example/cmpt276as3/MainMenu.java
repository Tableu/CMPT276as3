package com.example.cmpt276as3;

import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
//for navigation to other screens
public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_menu);
        Button option_button = findViewById(R.id.optionsButton);
        option_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), OptionsScreen.class);
                startActivity(intent);
            }
        });

        Button help_button = findViewById(R.id.helpButton);
        help_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), HelpScreen.class);
                startActivity(intent);
            }
        });

        Button game_button = findViewById(R.id.gameButton);
        game_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), GameScreen.class);
                startActivity(intent);
            }
        });
    }
}
