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

public class Quiz7Activity extends AppCompatActivity {
    // keep track of scores
    int totalScore;
    int possibleTopScore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz7);

        addListenerOnButton();

        // Get total score from previous activity
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

                // get EditText field contents
                EditText submitAnswer = findViewById(R.id.submitAnswer);
                String stringAnswer = submitAnswer.getText().toString();

                // get all possible correct answers from string.xml
                String answer01 = getResources().getString(R.string.quiz_7_answer_1);
                String answer02 = getResources().getString(R.string.quiz_7_answer_2);
                String answer03 = getResources().getString(R.string.quiz_7_answer_3);
                String answer04 = getResources().getString(R.string.quiz_7_answer_4);
                String answer05 = getResources().getString(R.string.quiz_7_answer_5);
                String answer06 = getResources().getString(R.string.quiz_7_answer_6);
                String answer07 = getResources().getString(R.string.quiz_7_answer_7);
                String answer08 = getResources().getString(R.string.quiz_7_answer_8);
                String answer09 = getResources().getString(R.string.quiz_7_answer_9);
                String answer10 = getResources().getString(R.string.quiz_7_answer_10);
                String answer11 = getResources().getString(R.string.quiz_7_answer_11);
                String answer12 = getResources().getString(R.string.quiz_7_answer_12);
                String answer13 = getResources().getString(R.string.quiz_7_answer_13);
                String answer14 = getResources().getString(R.string.quiz_7_answer_14);
                String answer15 = getResources().getString(R.string.quiz_7_answer_15);
                String answer16 = getResources().getString(R.string.quiz_7_answer_16);
                String answer17 = getResources().getString(R.string.quiz_7_answer_17);
                String answer18 = getResources().getString(R.string.quiz_7_answer_18);
                String answer19 = getResources().getString(R.string.quiz_7_answer_19);
                String answer20 = getResources().getString(R.string.quiz_7_answer_20);
                String answer21 = getResources().getString(R.string.quiz_7_answer_21);
                String answer22 = getResources().getString(R.string.quiz_7_answer_22);
                String answer23 = getResources().getString(R.string.quiz_7_answer_23);
                String answer24 = getResources().getString(R.string.quiz_7_answer_24);

                // check to see if correct answer was entered
                if ((stringAnswer.equals(answer01)) ||
                        (stringAnswer.equals(answer02)) ||
                        (stringAnswer.equals(answer03)) ||
                        (stringAnswer.equals(answer04)) ||
                        (stringAnswer.equals(answer05)) ||
                        (stringAnswer.equals(answer06)) ||
                        (stringAnswer.equals(answer07)) ||
                        (stringAnswer.equals(answer08)) ||
                        (stringAnswer.equals(answer09)) ||
                        (stringAnswer.equals(answer10)) ||
                        (stringAnswer.equals(answer11)) ||
                        (stringAnswer.equals(answer12)) ||
                        (stringAnswer.equals(answer13)) ||
                        (stringAnswer.equals(answer14)) ||
                        (stringAnswer.equals(answer15)) ||
                        (stringAnswer.equals(answer16)) ||
                        (stringAnswer.equals(answer17)) ||
                        (stringAnswer.equals(answer18)) ||
                        (stringAnswer.equals(answer19)) ||
                        (stringAnswer.equals(answer20)) ||
                        (stringAnswer.equals(answer21)) ||
                        (stringAnswer.equals(answer22)) ||
                        (stringAnswer.equals(answer23)) ||
                        (stringAnswer.equals(answer24))) {

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
        Intent myIntent = new Intent(this, Quiz8Activity.class);
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