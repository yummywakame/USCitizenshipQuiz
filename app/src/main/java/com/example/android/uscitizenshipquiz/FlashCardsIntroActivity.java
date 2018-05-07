package com.example.android.uscitizenshipquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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