package com.example.dartscount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ThreeDartModeActivity extends MainActivity {


    public Button nextButton;
    public TextView numberToCount1;
    public TextView numberToCount2;
    public TextView numberToCount3;
    public EditText inputAnswer;

    private ExpectedResultAndDisplayedNumber expectedResultAndDisplayedNumber1;
    private ExpectedResultAndDisplayedNumber expectedResultAndDisplayedNumber2;
    private ExpectedResultAndDisplayedNumber expectedResultAndDisplayedNumber3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threedarts_mode_game_layout);

        nextButton = findViewById(R.id.validateAndNextQuestionButton);
        numberToCount1 = findViewById(R.id.firstDartToDisplayForUser);
        numberToCount2 = findViewById(R.id.secondDartToDisplayForUser);
        numberToCount3 = findViewById(R.id.thirdDartToDisplayForUser);
        inputAnswer = findViewById(R.id.inputNumberToValid);

        generateNewExample1();
        generateNewExample2();
        generateNewExample3();


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
                    generateNewExample3();
                    inputAnswer.setText("");
                }else {
                    Toast.makeText(getApplicationContext(), "Bad Value !!!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


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
