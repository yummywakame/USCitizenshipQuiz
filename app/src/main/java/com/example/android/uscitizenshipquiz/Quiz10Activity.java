package com.example.android.uscitizenshipquiz;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CheckBox;

public class Quiz10Activity extends AppCompatActivity {
    // keep track of scores
    int totalScore;
    int possibleTopScore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz10);

        addListenerOnButton();

        // get total score from previous activity
        totalScore = getIntent().getIntExtra("TOTAL_SCORE", 0);
        possibleTopScore = getIntent().getIntExtra("POSSIBLE_SCORE", 0);
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
                Button btn = (Button) findViewById(R.id.btnSubmit);
                btn.setAlpha(.6f);
                btn.setEnabled(false);

                // add one to possibleTopScore for secondary progress bar display
                possibleTopScore = possibleTopScore + 1;

                // initialize the CheckBoxes
                CheckBox checkbox1 = findViewById(R.id.checkbox1);
                CheckBox checkbox2 = findViewById(R.id.checkbox2);
                CheckBox checkbox3 = findViewById(R.id.checkbox3);
                CheckBox checkbox4 = findViewById(R.id.checkbox4);
                CheckBox checkbox5 = findViewById(R.id.checkbox5);

                // highlight correct answer(s)
                checkbox2.setTextColor(getResources().getColor(R.color.mediumRed));
                checkbox3.setTextColor(getResources().getColor(R.color.mediumRed));
                checkbox4.setTextColor(getResources().getColor(R.color.mediumRed));

                // check to see if correct checkboxes were selected
                if (checkbox2.isChecked() && checkbox3.isChecked() && checkbox4.isChecked() && !checkbox1.isChecked() && !checkbox5.isChecked()) {
                    // if correct answer, add 1 to totalScore and display "Correct!" in a toast.
                    totalScore = totalScore + 1;
                    showCustomToastMessage(true);
                    saveAndProceedToNextScreen();
                } else {
                    // if incorrect answer, display "INCORRECT!" in a toast.
                    showCustomToastMessage(false);

                    // show correct answer as a tip
                    TextView tip = (TextView) findViewById(R.id.tip);
                    tip.setVisibility(View.VISIBLE);

                    // delay next function by 5 seconds
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            saveAndProceedToNextScreen();
                        }
                    }, 5000);   //5 seconds
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
        if (toastType == true) {
            layout = inflater.inflate(R.layout.toast_correct,
                    (ViewGroup) findViewById(R.id.toast_layout_root));
            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText(getString(R.string.toast_correct));
            toast.setDuration(Toast.LENGTH_SHORT);
        } else {
            layout = inflater.inflate(R.layout.toast_incorrect,
                    (ViewGroup) findViewById(R.id.toast_layout_root));
            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText(getString(R.string.toast_incorrect));
            toast.setDuration(Toast.LENGTH_LONG);
        }
        toast.setView(layout);
        toast.show();
    }

    // pass value of Total Score to next Activity and open it
    private void saveAndProceedToNextScreen() {
        Intent myIntent;
        // if score is 6 or greater, proceed to PASSED screen else FAIL screen
        if (totalScore >= 6) {
            myIntent = new Intent(this, QuizPassedActivity.class);
        } else {
            myIntent = new Intent(this, QuizFailedActivity.class);
        }
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