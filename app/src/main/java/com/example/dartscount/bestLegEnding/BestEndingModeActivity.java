package com.example.dartscount.bestLegEnding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dartscount.MainActivity;
import com.example.dartscount.R;


public class BestEndingModeActivity extends AppCompatActivity {


    public Spinner spinnerFirstThrowAnswer,spinnerSecondThrowAnswer,spinnerThirdThrowAnswer;
    private int backTapCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.best_ending_layout);
        getSupportActionBar().hide();


        spinnerFirstThrowAnswer = findViewById(R.id.spinnerFirstThrowAnswer);
        spinnerSecondThrowAnswer = findViewById(R.id.spinnerSecondThrowAnswer);
        spinnerThirdThrowAnswer = findViewById(R.id.spinnerThirdThrowAnswer);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.allThrows, R.layout.spinner_item_black);
        adapter.setDropDownViewResource(R.layout.spinner_black_dropdown_item);
        spinnerFirstThrowAnswer.setAdapter(adapter);
        spinnerSecondThrowAnswer.setAdapter(adapter);
        spinnerThirdThrowAnswer.setAdapter(adapter);



    }


    @Override
    public void onBackPressed() {
        backTapCount++;
        Toast.makeText(getApplicationContext(), "Click again to back home",
                Toast.LENGTH_LONG).show();
        if (backTapCount==2){
            Intent intent =  new Intent(BestEndingModeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
