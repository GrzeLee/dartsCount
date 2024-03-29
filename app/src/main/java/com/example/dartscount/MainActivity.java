package com.example.dartscount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public Button firstGameButton;
    public Button secondGameButton;
    public Button bestScoreButton;
    private int backTapCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_layout);
        getSupportActionBar().hide();

        firstGameButton = findViewById(R.id.firstGameButton);
        secondGameButton = findViewById(R.id.secondGameButton);
        bestScoreButton = findViewById(R.id.best_score_button);


        firstGameButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FirstGameModeSettingActivity.class);
            startActivity(intent);
            finish();
        });

        secondGameButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SecondGameModeSettingActivity.class);
            startActivity(intent);
            finish();
        });

        bestScoreButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, BestScoreActivity.class);
            startActivity(intent);
            finish();
        });
    }


    @Override
    public void onBackPressed() {
        backTapCount++;
        Toast.makeText(getApplicationContext(), "Click again to close",
                Toast.LENGTH_LONG).show();
        if (backTapCount == 2) {
            MainActivity.this.finish();
            System.exit(0);
        }
    }


}