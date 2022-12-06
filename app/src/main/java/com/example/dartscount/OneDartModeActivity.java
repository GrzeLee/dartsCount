package com.example.dartscount;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dartscount.roomdatabase.BestScore;
import com.example.dartscount.roomdatabase.ScoreDataBase;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class OneDartModeActivity extends AppCompatActivity {


    public TextView gameTimer;
    public Button nextButton;
    public TextView numberToCount;
    public EditText inputAnswer;

    private CountDownTimer countDownTimer;
    private int backTapCount = 0;
    private int correctCount = 0;

    private ExpectedResultAndDisplayedNumber expectedResultAndDisplayedNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onedart_mode_game_layout);
        getSupportActionBar().hide();

        nextButton = findViewById(R.id.validateAndNextQuestionButton);
        numberToCount = findViewById(R.id.numberToDisplayForUser);
        inputAnswer = findViewById(R.id.inputNumberToValid);
        gameTimer = findViewById(R.id.gameTimer);

        generateNewExample();

        Intent receivedIntend = getIntent();
        long receivedTimeValue = receivedIntend.getLongExtra("TIME_SENDER",0);
        countDownTimer = new CountDownTimer(receivedTimeValue, 1000) {

            public void onTick(long millisUntilFinished) {

                NumberFormat f = new DecimalFormat("00");
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;

                gameTimer.setText(String.format("%s:%s", f.format(min), f.format(sec)));
            }
            public void onFinish() {
                //SET BEST SCORE  TO DATABASES
                setBaseScoreToDB(String.valueOf(correctCount), String.valueOf(receivedTimeValue));

                //POPUP BUILDER
                PopupBuilder popupBuilder = new PopupBuilder(OneDartModeActivity.this);
                popupBuilder.summaryGamePopup(String.valueOf(correctCount));
            }
        }.start();


        nextButton.setOnClickListener(view -> {
            String userAnswer = String.valueOf(inputAnswer.getText());
            if (userAnswer.equals("")){
                Toast.makeText(getApplicationContext(), "Empty field",
                        Toast.LENGTH_LONG).show();
            }else {
                if (userAnswer.equals(expectedResultAndDisplayedNumber.getExpectedResult())) {
                    generateNewExample();
                    correctCount ++;
                    inputAnswer.setText("");
                    Toast.makeText(getApplicationContext(), "Nice !!!",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Bad Value !!!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void setBaseScoreToDB(String score ,String receivedTimeValue) {
        if(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().selectAll().isEmpty()){
            BestScore newDataRow = new BestScore("0","0","0","0","0","0","0","0","0");
            ScoreDataBase.getDataBase(getApplicationContext()).daoScore().insertAll(newDataRow);
        }
        int scoreInt = Integer.parseInt(score);
            switch (receivedTimeValue) {
                case "180000":
                    int actualScore3min = Integer.parseInt(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode1Easy3min());
                    if(scoreInt > actualScore3min){ScoreDataBase.getDataBase(getApplicationContext()).daoScore().updateEasyScore3min(score);}
                    break;
                case "300000":
                    int actualScore5min = Integer.parseInt(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode1Easy5min());
                    if(scoreInt > actualScore5min){ScoreDataBase.getDataBase(getApplicationContext()).daoScore().updateEasyScore5min(score);}
                    break;
                case "600000":
                    int actualScore10min = Integer.parseInt(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode1Easy10min());
                    if(scoreInt > actualScore10min){ScoreDataBase.getDataBase(getApplicationContext()).daoScore().updateEasyScore10min(score);}
                    break;
            }
    }

    @Override
    public void onBackPressed() {
        backTapCount++;
        Toast.makeText(getApplicationContext(), "Click again to back home",
                Toast.LENGTH_LONG).show();
        if (backTapCount==2){
            countDownTimer.cancel();
            Intent intent =  new Intent(OneDartModeActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    public void generateNewExample(){
        expectedResultAndDisplayedNumber = CountMethods.getRandomNumberWithOperatorAndResult();
        numberToCount.setText(expectedResultAndDisplayedNumber.getDisplayedNumber());
    }
}
