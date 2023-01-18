package com.example.dartscount;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dartscount.firstGameModeActivities.OneDartModeActivity;
import com.example.dartscount.firstGameModeActivities.ThreeDartModeActivity;
import com.example.dartscount.firstGameModeActivities.TwoDartModeActivity;
import com.example.dartscount.secondGameModeActivities.OneThrowSecondModeActivity;
import com.example.dartscount.secondGameModeActivities.ThreeThrowsSecondModeActivity;
import com.example.dartscount.secondGameModeActivities.TwoThrowsSecondModeActivity;

public class SecondGameModeSettingActivity extends AppCompatActivity {

    public Spinner modeSpinner;
    public Button startGameButton;
    public RadioGroup radioGroup;
    public RadioButton radioButton;
    public TextView textGameInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_setting);
        getSupportActionBar().hide();

        modeSpinner = findViewById(R.id.modeSpinner);
        startGameButton = findViewById(R.id.startGameButton);
        radioGroup = findViewById(R.id.radioGroup);
        textGameInfo = findViewById(R.id.textGameInfo);

        textGameInfo.setText(R.string.info_mode_2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gameModes, R.layout.spinner_item);
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
            modeSpinner.setAdapter(adapter);


            startGameButton.setOnClickListener(view -> {

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);


                int selectedItemNumber = modeSpinner.getSelectedItemPosition();
                if(selectedItemNumber==0){
                    Intent intent = new Intent(SecondGameModeSettingActivity.this, OneThrowSecondModeActivity.class);
                    intent.putExtra("TIME_SENDER", convertTimeToMilliseconds());
                    startActivity(intent);
                }
                if(selectedItemNumber==1){
                    Intent intent = new Intent(SecondGameModeSettingActivity.this, TwoThrowsSecondModeActivity.class);
                    intent.putExtra("TIME_SENDER", convertTimeToMilliseconds());
                    startActivity(intent);
                }
                if(selectedItemNumber==2){
                    Intent intent = new Intent(SecondGameModeSettingActivity.this, ThreeThrowsSecondModeActivity.class);
                    intent.putExtra("TIME_SENDER", convertTimeToMilliseconds());
                    startActivity(intent);
                }
                finish();
        });
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(SecondGameModeSettingActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public long convertTimeToMilliseconds(){
        String radioButtonText = String.valueOf(radioButton.getText());
        String arr[] = radioButtonText.split(" ", 2);
        return Integer.parseInt(arr[0])* 60000L;
    }


}
