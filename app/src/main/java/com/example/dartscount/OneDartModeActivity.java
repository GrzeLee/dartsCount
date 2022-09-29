package com.example.dartscount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class OneDartModeActivity extends MainActivity {

    public ImageView goHomeButton;
    public Button startButton;
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
        startButton = findViewById(R.id.startGameButton);
        goHomeButton = findViewById(R.id.backToHomeButton);

        nextButton.setEnabled(false);

        startButton.setOnClickListener(view -> {
            generateNewExample();
            nextButton.setEnabled(true);
            startButton.setEnabled(false);
        });

        goHomeButton.setOnClickListener(view -> {
            Intent intent = new Intent(OneDartModeActivity.this,MainActivity.class);
            startActivity(intent);
        });


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
}
