package com.example.dartscount;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PopupBuilder extends AppCompatActivity {

    private AlertDialog dialog;
    private AlertDialog.Builder dialogBuilder;

    private Activity builderActivity;

    public PopupBuilder(Activity activity){
        builderActivity = activity;
    }

    public void summaryGamePopup(String correctCount, String incorrectCount){
        dialogBuilder = new AlertDialog.Builder(builderActivity);
        View endGamePopupView = builderActivity.getLayoutInflater().inflate(R.layout.summary_game_popup,null);
        TextView correctAnswerView = endGamePopupView.findViewById(R.id.correctAnswerView);
        TextView incorrectAnswerView = endGamePopupView.findViewById(R.id.incorrectAnswerView);
        ImageButton endButton = endGamePopupView.findViewById(R.id.endButton);
        dialogBuilder.setView(endGamePopupView);
        dialog = dialogBuilder.create();
        dialog.show();
        correctAnswerView.setText(correctCount);
        incorrectAnswerView.setText(incorrectCount);
        endButton.setOnClickListener(view -> {
            Intent intent = new Intent(builderActivity, MainActivity.class);
            builderActivity.startActivity(intent);
            finish();
            });
    }
}
