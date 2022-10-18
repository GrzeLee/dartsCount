package com.example.dartscount;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.filament.View;

import org.w3c.dom.Text;

public class GameSettingActivity extends MainActivity{

    public Spinner modeSpinner;
    public Button startGameButton;
    public RadioGroup radioGroup;
    public RadioButton radioButton;
    public TextView selectedGameTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_setting);

        modeSpinner = findViewById(R.id.modeSpinner);
        startGameButton = findViewById(R.id.startGameButton);
        radioGroup = findViewById(R.id.radioGroup);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gameModes, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            modeSpinner.setAdapter(adapter);


            startGameButton.setOnClickListener(view -> {

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);


                int selectedItemNumber = modeSpinner.getSelectedItemPosition();
                if(selectedItemNumber==0){
                    Intent intent = new Intent(GameSettingActivity.this,OneDartModeActivity.class);
                    intent.putExtra("TIME_SENDER", radioButton.getText());
                    startActivity(intent);
                }
                if(selectedItemNumber==1){
                    Intent intent = new Intent(GameSettingActivity.this,TwoDartModeActivity.class);
                    intent.putExtra("TIME_SENDER", radioButton.getText());
                    startActivity(intent);
                }
                if(selectedItemNumber==2){
                    Intent intent = new Intent(GameSettingActivity.this,ThreeDartModeActivity.class);
                    intent.putExtra("TIME_SENDER", radioButton.getText());
                    startActivity(intent);
                }

        });


    }


}