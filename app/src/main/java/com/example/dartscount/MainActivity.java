package com.example.dartscount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    public Button firstGameButton;
    public Button secondGameButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_layout);
        getSupportActionBar().hide();

        firstGameButton = findViewById(R.id.firstGameButton);
        secondGameButton = findViewById(R.id.secondGameButton);


        firstGameButton.setOnClickListener(view -> {
                Intent intent =  new Intent(MainActivity.this,GameSettingActivity.class);
                startActivity(intent);
            });




    }
}