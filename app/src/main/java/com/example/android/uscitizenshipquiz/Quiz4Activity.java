package com.example.android.uscitizenshipquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Quiz4Activity extends AppCompatActivity {
    // keep track of scores
    int totalScore;
    int possibleTopScore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz4);

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

                // hide annoying keyboard once useless
                hideKeyboard();

                // add one to possibleTopScore for secondary progress bar display
                possibleTopScore = possibleTopScore + 1;

                // get correct answer from string.xml
                String answer = getResources().getString(R.string.quiz_4_answer);

                // retrieve answer from EditText
                EditText inputAnswer = findViewById(R.id.submitAnswer);
                // convert EditText to String
                String inputAnswerString = inputAnswer.getText().toString();
                // highlight correct answer(s)
                inputAnswer.setTextColor(getResources().getColor(R.color.mediumRed));
                inputAnswer.setText(answer);
                // check if value = ""
                if (inputAnswerString.equals("")) {
                    inputAnswerString = "0";
                }
                // convert String to int
                int inputAnswerNumber = Integer.parseInt(inputAnswerString);

                // check to see if correct number was entered
                if (inputAnswerNumber == 9) {
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
        Intent myIntent = new Intent(this, Quiz5Activity.class);
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

    // override and hide the keyboard when useless
    private void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}