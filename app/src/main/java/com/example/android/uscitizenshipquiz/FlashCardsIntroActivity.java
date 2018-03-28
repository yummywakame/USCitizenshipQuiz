package com.example.android.uscitizenshipquiz;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.TextView;

public class FlashCardsIntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards_intro);


    }

    /**
     * When the 'Proceed' button is clicked:
     * Set the content of the activity to use the activity_flash_cards_intro.xml file
     */
    public void startFlashCards(View view) {
        Intent intent = new Intent(this,FlashCardsActivity.class);
        startActivity(intent);
    }

}