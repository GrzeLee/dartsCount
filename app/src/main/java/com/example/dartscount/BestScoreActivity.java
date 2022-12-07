package com.example.dartscount;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dartscount.roomdatabase.ScoreDataBase;

public class BestScoreActivity extends AppCompatActivity {


    public TextView easy_top_score_3min, medium_top_score_3min, hard_top_score_3min, easy_top_score_5min, medium_top_score_5min, hard_top_score_5min, easy_top_score_10min, medium_top_score_10min, hard_top_score_10min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.best_score);
        getSupportActionBar().hide();

        easy_top_score_3min = findViewById(R.id.easy_top_score_3min);
        medium_top_score_3min = findViewById(R.id.medium_top_score_3min);
        hard_top_score_3min = findViewById(R.id.hard_top_score_3min);
        easy_top_score_5min = findViewById(R.id.easy_top_score_5min);
        medium_top_score_5min = findViewById(R.id.medium_top_score_5min);
        hard_top_score_5min = findViewById(R.id.hard_top_score_5min);
        easy_top_score_10min = findViewById(R.id.easy_top_score_10min);
        medium_top_score_10min = findViewById(R.id.medium_top_score_10min);
        hard_top_score_10min = findViewById(R.id.hard_top_score_10min);

        easy_top_score_3min.setText(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode1Easy3min());
        easy_top_score_5min.setText(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode1Easy5min());
        easy_top_score_10min.setText(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode1Easy10min());
        medium_top_score_3min.setText(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode1Medium3min());
        medium_top_score_5min.setText(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode1Medium5min());
        medium_top_score_10min.setText(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode1Medium10min());
        hard_top_score_3min.setText(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode1Hard3min());
        hard_top_score_5min.setText(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode1Hard5min());
        hard_top_score_10min.setText(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode1Hard10min());
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(BestScoreActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
