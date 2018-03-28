package com.example.android.uscitizenshipquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class QuizPassedActivity extends AppCompatActivity {
    // keep track of scores
    int totalScore;
    int possibleTopScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_passed);

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

        // display final score
        TextView text = (TextView) findViewById(R.id.finalscore);
        text.setText(String.valueOf(totalScore));
    }

    /**
     * When the 'Retake Quiz' button is clicked:
     * Set the content of the activity to use the activity_flash_cards_intro.xml file
     */
    public void startQuiz(View view) {
        Intent intent = new Intent(this,Quiz1Activity.class);
        startActivity(intent);
    }

    /**
     * When the 'Flash Cards' button is clicked:
     * Set the content of the activity to use the activity_flash_cards_intro.xml file
     */
    public void openFlashCardsActivity(View view) {
        Intent gotoFlashCardsActivity = new Intent(this,FlashCardsIntroActivity.class);
        startActivity(gotoFlashCardsActivity);

    }

}
