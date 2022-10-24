package com.example.dartscount;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TwoDartModeActivity extends AppCompatActivity {

    public Button nextButton;
    public TextView numberToCount1;
    public TextView numberToCount2;
    public EditText inputAnswer;
    public TextView gameTimer;

    private CountDownTimer countDownTimer;
    private int backTapCount = 0;
    private int missCount = 0;
    private int correctCount = 0;

    private ExpectedResultAndDisplayedNumber expectedResultAndDisplayedNumber1;
    private ExpectedResultAndDisplayedNumber expectedResultAndDisplayedNumber2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twodarts_mode_game_layout);

        nextButton = findViewById(R.id.validateAndNextQuestionButton);
        numberToCount1 = findViewById(R.id.firstDartToDisplayForUser);
        numberToCount2 = findViewById(R.id.secondDartToDisplayForUser);
        inputAnswer = findViewById(R.id.inputNumberToValid);
        gameTimer = findViewById(R.id.gameTimer);

        generateNewExample1();
        generateNewExample2();

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
                PopupBuilder popupBuilder = new PopupBuilder(TwoDartModeActivity.this);
                popupBuilder.summaryGamePopup(String.valueOf(correctCount), String.valueOf(missCount));
            }
        }.start();


        nextButton.setOnClickListener(view -> {
            String stringAnswer = String.valueOf(inputAnswer.getText());
            if(stringAnswer.equals("")){
                Toast.makeText(getApplicationContext(), "Empty field",
                        Toast.LENGTH_LONG).show();
            }else {
                int userAnswer = Integer.parseInt(String.valueOf(inputAnswer.getText()));
                int expectedResult = Integer.parseInt(expectedResultAndDisplayedNumber1.getExpectedResult())+Integer.parseInt(expectedResultAndDisplayedNumber2.getExpectedResult());
                if(userAnswer==expectedResult){
                    generateNewExample1();
                    generateNewExample2();
                    correctCount ++;
                    inputAnswer.setText("");
                    Toast.makeText(getApplicationContext(), "Nice !!!",
                            Toast.LENGTH_LONG).show();
                }else {
                    missCount ++;
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
            Intent intent =  new Intent(TwoDartModeActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
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

}
