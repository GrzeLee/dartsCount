package com.example.dartscount;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class OneDartModeActivity extends MainActivity {


    public TextView gameTimer;
    public Button nextButton;
    public TextView numberToCount;
    public EditText inputAnswer;


    private ExpectedResultAndDisplayedNumber expectedResultAndDisplayedNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onedart_mode_game_layout);

        nextButton = findViewById(R.id.validateAndNextQuestionButton);
        numberToCount = findViewById(R.id.numberToDisplayForUser);
        inputAnswer = findViewById(R.id.inputNumberToValid);
        gameTimer = findViewById(R.id.gameTimer);

        generateNewExample();

        new CountDownTimer(checkTimeLength(), 1000) {

            public void onTick(long millisUntilFinished) {

                NumberFormat f = new DecimalFormat("00");
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;

                gameTimer.setText(f.format(min) + ":" + f.format(sec));
            }
            public void onFinish() {
                gameTimer.setText("00:00");
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
                    inputAnswer.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "Bad Value !!!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void generateNewExample(){
        expectedResultAndDisplayedNumber = CountMethods.getRandomNumberWithOperatorAndResult();
        numberToCount.setText(expectedResultAndDisplayedNumber.getDisplayedNumber());
    }

    public long checkTimeLength(){
        Intent receivedIntend = getIntent();
        String receivedValue = receivedIntend.getStringExtra("TIME_SENDER");
        if(receivedValue.equals("3 min")){
            return 180000;
        }
        if(receivedValue.equals("5 min")){
            return 300000;
        }
        else {
            return 600000;
        }

    }
}
