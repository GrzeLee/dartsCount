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

public class ThreeDartModeActivity extends AppCompatActivity {


    public Button nextButton;
    public TextView numberToCount1;
    public TextView numberToCount2;
    public TextView numberToCount3;
    public EditText inputAnswer;
    public TextView gameTimer;

    private int backTapCount = 0;
    private int correctCount = 0;
    private CountDownTimer countDownTimer;


    private ExpectedResultAndDisplayedNumber expectedResultAndDisplayedNumber1;
    private ExpectedResultAndDisplayedNumber expectedResultAndDisplayedNumber2;
    private ExpectedResultAndDisplayedNumber expectedResultAndDisplayedNumber3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threedarts_mode_game_layout);
        getSupportActionBar().hide();

        nextButton = findViewById(R.id.validateAndNextQuestionButton);
        numberToCount1 = findViewById(R.id.firstDartToDisplayForUser);
        numberToCount2 = findViewById(R.id.secondDartToDisplayForUser);
        numberToCount3 = findViewById(R.id.thirdDartToDisplayForUser);
        inputAnswer = findViewById(R.id.inputNumberToValid);
        gameTimer = findViewById(R.id.gameTimer);

        generateNewExample1();
        generateNewExample2();
        generateNewExample3();


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
                PopupBuilder popupBuilder = new PopupBuilder(ThreeDartModeActivity.this);
                popupBuilder.summaryGamePopup(String.valueOf(correctCount));
            }
        }.start();


        nextButton.setOnClickListener(view -> {
            String stringAnswer = String.valueOf(inputAnswer.getText());
            if(stringAnswer.equals("")){
                Toast.makeText(getApplicationContext(), "Empty field",
                        Toast.LENGTH_LONG).show();
            }else {
                int userAnswer = Integer.parseInt(String.valueOf(inputAnswer.getText()));
                int expectedResult = Integer.parseInt(expectedResultAndDisplayedNumber1.getExpectedResult())+Integer.parseInt(expectedResultAndDisplayedNumber2.getExpectedResult())+Integer.parseInt(expectedResultAndDisplayedNumber3.getExpectedResult());
                if(userAnswer==expectedResult){
                    generateNewExample1();
                    generateNewExample2();
                    generateNewExample3();
                    correctCount ++;
                    inputAnswer.setText("");
                    Toast.makeText(getApplicationContext(), "Nice !!!",
                            Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Bad Value !!!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        backTapCount++;
        Toast.makeText(getApplicationContext(), "Click again to back home",
                Toast.LENGTH_LONG).show();
        if (backTapCount==2){
            countDownTimer.cancel();
            Intent intent =  new Intent(ThreeDartModeActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void setBaseScoreToDB(String score ,String receivedTimeValue) {
        if(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().selectAll().isEmpty()){
            BestScore newDataRow = new BestScore("0","0","0","0","0","0","0","0","0");
            ScoreDataBase.getDataBase(getApplicationContext()).daoScore().insertAll(newDataRow);
        }
        int scoreInt = Integer.parseInt(score);
        switch (receivedTimeValue) {
            case "180000":
                int actualScore3min = Integer.parseInt(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode1Hard3min());
                if(scoreInt > actualScore3min){ScoreDataBase.getDataBase(getApplicationContext()).daoScore().updateHardScore3min(score);}
                break;
            case "300000":
                int actualScore5min = Integer.parseInt(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode1Hard5min());
                if(scoreInt > actualScore5min){ScoreDataBase.getDataBase(getApplicationContext()).daoScore().updateHardScore5min(score);}
                break;
            case "600000":
                int actualScore10min = Integer.parseInt(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode1Hard10min());
                if(scoreInt > actualScore10min){ScoreDataBase.getDataBase(getApplicationContext()).daoScore().updateHardScore10min(score);}
                break;
        }
    }

    public void generateNewExample1(){
        expectedResultAndDisplayedNumber1 = CountMethods.getRandomNumberWithOperatorAndResult();
        numberToCount1.setText(expectedResultAndDisplayedNumber1.getDisplayedNumber());
    }

    public void generateNewExample2(){
        expectedResultAndDisplayedNumber2 = CountMethods.getRandomNumberWithOperatorAndResult();
        numberToCount2.setText(expectedResultAndDisplayedNumber2.getDisplayedNumber());
    }

    public void generateNewExample3(){
        expectedResultAndDisplayedNumber3 = CountMethods.getRandomNumberWithOperatorAndResult();
        numberToCount3.setText(expectedResultAndDisplayedNumber3.getDisplayedNumber());
    }
}
