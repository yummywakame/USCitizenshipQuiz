package com.example.android.uscitizenshipquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class Quiz1Activity extends AppCompatActivity {
    // initialize total scores on first question
    int totalScore;
    int possibleTopScore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);

        addListenerOnButton();

        Log.i(this.getClass().getSimpleName(), "totalScore BEGINNING: " + totalScore + " | possibleTopScore BEGINNING: " + possibleTopScore);

        // display progress bar values
        ProgressBar bar = findViewById(R.id.progressBar);
        // show how far we have progressed through the quiz / possible correct answers
        int barPossibleProgress = possibleTopScore * 10;
        bar.setSecondaryProgress(barPossibleProgress);
        // show actual correct answers so far
        int barTotalProgress = totalScore * 10;
        bar.setProgress(barTotalProgress);
        bar.setScaleY(5f);
    }

    public void addListenerOnButton() {
        Button btnDisplay;
        btnDisplay = findViewById(R.id.btnSubmit);
        btnDisplay.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // disable button
                Button btn = findViewById(R.id.btnSubmit);
                btn.setAlpha(.6f);
                btn.setEnabled(false);

                // add one to possibleTopScore for secondary progress bar display
                possibleTopScore = possibleTopScore + 1;

                // check to see if correct radio button selected
                RadioButton rb;
                rb = findViewById(R.id.radio2);

                // highlight correct answer(s)
                rb.setTextColor(getResources().getColor(R.color.mediumRed));

                if (rb.isChecked()) {
                    // if correct answer, add 1 to totalScore and display "Correct!" in a toast.
                    totalScore = totalScore + 1;
                    showCustomToastMessage(true);
                    saveAndProceedToNextScreen();

                } else {
                    // if incorrect answer, display "INCORRECT!" in a toast.
                    showCustomToastMessage(false);

                    // delay next function by 3 seconds
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            saveAndProceedToNextScreen();
                        }
                    }, 3000);   //3 seconds
                }

            }
        });
    }

    // show custom toast message
    private void showCustomToastMessage(Boolean toastType) {
        LayoutInflater inflater = getLayoutInflater();
        View layout;
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 180);
        if (toastType) {
            layout = inflater.inflate(R.layout.toast_correct,
                    (ViewGroup) findViewById(R.id.toast_layout_root));
            TextView text = layout.findViewById(R.id.text);
            text.setText(getString(R.string.toast_correct));
            toast.setDuration(Toast.LENGTH_SHORT);
        } else {
            layout = inflater.inflate(R.layout.toast_incorrect,
                    (ViewGroup) findViewById(R.id.toast_layout_root));
            TextView text = layout.findViewById(R.id.text);
            text.setText(getString(R.string.toast_incorrect));
            toast.setDuration(Toast.LENGTH_LONG);
        }
        toast.setView(layout);
        toast.show();
    }

    // pass value of Total Score to next Activity and open it
    private void saveAndProceedToNextScreen() {
        Intent myIntent = new Intent(this, Quiz2Activity.class);
        myIntent.putExtra("TOTAL_SCORE", totalScore);
        myIntent.putExtra("POSSIBLE_SCORE", possibleTopScore);
        Log.i(this.getClass().getSimpleName(), "totalScore END: " + totalScore + "       | possibleTopScore END: " + possibleTopScore);
        startActivity(myIntent);
    }

    // override up/back button to not allow resubmitting questions.
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Tsk! Tsk! No cheating ;)", Toast.LENGTH_SHORT).show();
    }

}