package com.example.dartscount;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

    public void summaryGamePopup(String correctCount){
        dialogBuilder = new AlertDialog.Builder(builderActivity);
        View endGamePopupView = builderActivity.getLayoutInflater().inflate(R.layout.summary_game_popup,null);
        TextView correctAnswerView = endGamePopupView.findViewById(R.id.correctAnswerView);
        ImageButton endButton = endGamePopupView.findViewById(R.id.endButton);
        dialogBuilder.setView(endGamePopupView);
        dialog = dialogBuilder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        correctAnswerView.setText(correctCount);
        endButton.setOnClickListener(view -> {
            dialog.dismiss();
            Intent intent = new Intent(builderActivity, MainActivity.class);
            builderActivity.startActivity(intent);
            builderActivity.finish();
            });
    }
}
