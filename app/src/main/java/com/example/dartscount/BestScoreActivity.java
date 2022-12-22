package com.example.dartscount;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dartscount.roomdatabase.ScoreDataBase;

public class BestScoreActivity extends AppCompatActivity {


    public TextView easy_top_score_Time1, medium_top_score_Time1, hard_top_score_Time1, easy_top_score_Time2, medium_top_score_Time2, hard_top_score_Time2, easy_top_score_Time3, medium_top_score_Time3, hard_top_score_Time3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.best_score);
        getSupportActionBar().hide();

        easy_top_score_Time1 = findViewById(R.id.easy_top_score_Time1);
        medium_top_score_Time1 = findViewById(R.id.medium_top_score_Time1);
        hard_top_score_Time1 = findViewById(R.id.hard_top_score_Time1);
        easy_top_score_Time2 = findViewById(R.id.easy_top_score_Time2);
        medium_top_score_Time2 = findViewById(R.id.medium_top_score_Time2);
        hard_top_score_Time2 = findViewById(R.id.hard_top_score_Time2);
        easy_top_score_Time3 = findViewById(R.id.easy_top_score_Time3);
        medium_top_score_Time3 = findViewById(R.id.medium_top_score_Time3);
        hard_top_score_Time3 = findViewById(R.id.hard_top_score_Time3);

        easy_top_score_Time1.setText(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode1EasyTime1());
        easy_top_score_Time2.setText(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode1EasyTime2());
        easy_top_score_Time3.setText(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode1EasyTime3());
        medium_top_score_Time1.setText(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode1MediumTime1());
        medium_top_score_Time2.setText(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode1MediumTime2());
        medium_top_score_Time3.setText(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode1MediumTime3());
        hard_top_score_Time1.setText(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode1HardTime1());
        hard_top_score_Time2.setText(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode1HardTime2());
        hard_top_score_Time3.setText(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode1HardTime3());
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(BestScoreActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
