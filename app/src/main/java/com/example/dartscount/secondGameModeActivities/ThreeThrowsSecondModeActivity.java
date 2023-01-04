package com.example.dartscount.secondGameModeActivities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dartscount.CountMethods;
import com.example.dartscount.ExpectedResultAndDisplayedNumber;
import com.example.dartscount.MainActivity;
import com.example.dartscount.PopupBuilder;
import com.example.dartscount.R;
import com.example.dartscount.roomdatabase.BestScore;
import com.example.dartscount.roomdatabase.ScoreDataBase;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ThreeThrowsSecondModeActivity extends AppCompatActivity {

    public Button nextButton;
    public TextView numberToCount1,numberToCount2, numberToCount3 ,infoView, score;
    public EditText inputAnswer;
    public TextView gameTimer;


    private CountDownTimer countDownTimer;
    private int backTapCount = 0;
    private int correctCount = 0;

    private ExpectedResultAndDisplayedNumber expectedResultAndDisplayedNumber1;
    private ExpectedResultAndDisplayedNumber expectedResultAndDisplayedNumber2;
    private ExpectedResultAndDisplayedNumber expectedResultAndDisplayedNumber3;
    private String finalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threethrows_second_mode_layout);
        getSupportActionBar().hide();

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

        nextButton = findViewById(R.id.validateAndNextQuestionButton);
        numberToCount1 = findViewById(R.id.numberToDisplayForUser);
        numberToCount2 = findViewById(R.id.secondDartToDisplayForUser);
        numberToCount3 = findViewById(R.id.thirdDartToDisplayForUser);
        inputAnswer = findViewById(R.id.inputNumberToValid);
        gameTimer = findViewById(R.id.gameTimer);
        infoView = findViewById(R.id.info);
        score = findViewById(R.id.score);

        setNewExample1();
        setNewExample2();
        setNewExample3();
        setFinalScore();
        inputAnswer.setFocusableInTouchMode(true);
        inputAnswer.requestFocus();

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
                Boolean newBestScore =checkNewBestScore(String.valueOf(correctCount), String.valueOf(receivedTimeValue));
                //SET BEST SCORE  TO DATABASES
                setBaseScoreToDB(String.valueOf(correctCount), String.valueOf(receivedTimeValue));
                inputAnswer.clearFocus();
                //POPUP BUILDER
                PopupBuilder popupBuilder = new PopupBuilder(ThreeThrowsSecondModeActivity.this);
                popupBuilder.summaryGamePopup(String.valueOf(correctCount),newBestScore);
            }
        }.start();


        nextButton.setOnClickListener(view -> {
            String userAnswer = String.valueOf(inputAnswer.getText());
            if(userAnswer.equals("")){
                Toast.makeText(getApplicationContext(), "Empty field",
                        Toast.LENGTH_LONG).show();
            }else {
                if(userAnswer.equals(correctAnswer())){
                    setNewExample1();
                    setNewExample2();
                    setNewExample3();
                    setFinalScore();
                    correctCount ++;
                    inputAnswer.setText("");
                    textAnimation(infoView,"NICE !!!", R.color.correct_answer);
                }else {
                    textAnimation(infoView,"BAD !!!", R.color.wrong_answer);
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
            Intent intent =  new Intent(ThreeThrowsSecondModeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void textAnimation(TextView textView, String text, int color){
        AlphaAnimation alphaAnim = new AlphaAnimation(1.0f,0.0f);
        alphaAnim.setStartOffset(2000);
        alphaAnim.setDuration(400);
        alphaAnim.start();
        alphaAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                textView.setText(text);
                textView.setTextColor(getResources().getColor(color,null));
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                textView.setText("");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        textView.startAnimation(alphaAnim);
    }

    private void setBaseScoreToDB(String score ,String receivedTimeValue) {
        if(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().selectAll().isEmpty()){
            BestScore newDataRow = new BestScore("0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0");
            ScoreDataBase.getDataBase(getApplicationContext()).daoScore().insertAll(newDataRow);
        }
        int scoreInt = Integer.parseInt(score);
        switch (receivedTimeValue) {
            case "60000":
                int actualScoreTime1 = Integer.parseInt(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode2MediumTime1());
                if(scoreInt > actualScoreTime1){ScoreDataBase.getDataBase(getApplicationContext()).daoScore().updateMode2MediumScoreTime1(score);}
                break;
            case "120000":
                int actualScoreTime2 = Integer.parseInt(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode2MediumTime2());
                if(scoreInt > actualScoreTime2){ScoreDataBase.getDataBase(getApplicationContext()).daoScore().updateMode2MediumScoreTime2(score);}
                break;
            case "180000":
                int actualScoreTime3 = Integer.parseInt(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode2MediumTime3());
                if(scoreInt > actualScoreTime3){ScoreDataBase.getDataBase(getApplicationContext()).daoScore().updateMode2MediumScoreTime3(score);}
                break;
        }
    }

    private boolean checkNewBestScore(String score ,String receivedTimeValue){
        int scoreInt = Integer.parseInt(score);
        try {
            switch (receivedTimeValue) {
                case "60000":
                    int actualScoreTime1 = Integer.parseInt(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode2MediumTime1());
                    if (scoreInt > actualScoreTime1) {
                        return true;
                    }
                    break;
                case "120000":
                    int actualScoreTime2 = Integer.parseInt(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode2MediumTime2());
                    if (scoreInt > actualScoreTime2) {
                        return true;
                    }
                    break;
                case "180000":
                    int actualScoreTime3 = Integer.parseInt(ScoreDataBase.getDataBase(getApplicationContext()).daoScore().getMode2MediumTime3());
                    if (scoreInt > actualScoreTime3) {
                        return true;
                    }
                    break;
            }

        }catch (NumberFormatException ex){
            return true;
        }
        return false;
    }

    public void setNewExample1(){
        expectedResultAndDisplayedNumber1 = CountMethods.getRandomNumberWithOperatorAndResult();
        numberToCount1.setText(expectedResultAndDisplayedNumber1.getDisplayedNumber());
    }

    public void setNewExample2(){
        expectedResultAndDisplayedNumber2 = CountMethods.getRandomNumberWithOperatorAndResult();
        numberToCount2.setText(expectedResultAndDisplayedNumber2.getDisplayedNumber());
    }

    public void setNewExample3(){
        expectedResultAndDisplayedNumber3 = CountMethods.getRandomNumberWithOperatorAndResult();
        numberToCount3.setText(expectedResultAndDisplayedNumber3.getDisplayedNumber());
    }

    public void setFinalScore(){
        int expectedResultsSum = Integer.parseInt(expectedResultAndDisplayedNumber1.getExpectedResult()) + Integer.parseInt(expectedResultAndDisplayedNumber2.getExpectedResult()) + Integer.parseInt(expectedResultAndDisplayedNumber3.getExpectedResult());
        finalScore = CountMethods.getFinalScore(String.valueOf(expectedResultsSum));
        score.setText(finalScore);
    }

    public String correctAnswer(){
        int throwsSum = Integer.parseInt(expectedResultAndDisplayedNumber1.getExpectedResult()) + Integer.parseInt(expectedResultAndDisplayedNumber2.getExpectedResult()) + Integer.parseInt(expectedResultAndDisplayedNumber3.getExpectedResult());
        int answer = Integer.parseInt(finalScore) - throwsSum;
        return String.valueOf(answer);
    }
}
