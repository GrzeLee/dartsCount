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


public class OneDartModeActivity extends AppCompatActivity {


    public TextView gameTimer;
    public Button nextButton;
    public TextView numberToCount;
    public EditText inputAnswer;

    private CountDownTimer countDownTimer;
    private int backTapCount = 0;
    private int missCount = 0;
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
                PopupBuilder popupBuilder = new PopupBuilder(OneDartModeActivity.this);
                popupBuilder.summaryGamePopup(String.valueOf(correctCount), String.valueOf(missCount));
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
