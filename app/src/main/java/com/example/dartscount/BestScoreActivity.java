package com.example.dartscount;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class BestScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.best_score);
        getSupportActionBar().hide();
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(BestScoreActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
