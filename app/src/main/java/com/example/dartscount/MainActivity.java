package com.example.dartscount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    public Button oneDartMainMenuButton;
    public Button twoDartMainMenuButton;
    public Button threeDartsMainMenuButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_layout);
        getSupportActionBar().hide();

        oneDartMainMenuButton = findViewById(R.id.oneDartModebutton);
        twoDartMainMenuButton = findViewById(R.id.twoDartModeButton);
        threeDartsMainMenuButton = findViewById(R.id.threeDartsModeButton);



        oneDartMainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OneDartModeActivity.class);
                startActivity(intent);
            }
        });

        twoDartMainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TwoDartModeActivity.class);
                startActivity(intent);
            }
        });

        threeDartsMainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThreeDartModeActivity.class);
                startActivity(intent);
            }
        });



    }
}