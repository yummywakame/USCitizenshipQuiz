package com.example.android.uscitizenshipquiz;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

    }

    /**
     * When the 'U.S. Civics Quiz' button is clicked:
     * Set the content of the activity to use the activity_quiz_intro.xml file
     */
    public void openCivicsQuizActivity(View view) {
        Intent gotoCivicsQuizActivity = new Intent(this,QuizIntroActivity.class);
        startActivity(gotoCivicsQuizActivity);

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
