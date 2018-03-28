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

public class QuizIntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_intro);

        // hilight "6 out of 10 questions" in red
        int index = 3;
        String regularExpression = " ";
        String text = getString(R.string.activity_quiz_intro2);
        TextView textView = (TextView) findViewById(R.id.instructions);
        if (textView != null) {
            textView.setText(text);
            highlightTextPart(textView, index, regularExpression);
        }

    }

    /**
     * Highlight the most important instructions
     */
    private void highlightTextPart(TextView textView, int index, String regularExpression) {
        String fullText = textView.getText().toString();
        int startPos = 0;
        int endPos = fullText.length();
        String[] textParts = fullText.split(regularExpression);
        if (index < 0 || index > textParts.length - 1) {
            return;
        }
        if (textParts.length > 1) {
            startPos = fullText.indexOf(textParts[index]);
            endPos = fullText.indexOf(regularExpression, startPos);
            if (endPos == -1) {
                endPos = fullText.length();
            }
        }
        Spannable spannable = new SpannableString(fullText);
        ColorStateList redColor = new ColorStateList(new int[][] { new int[] {}}, new int[] { (getColor(R.color.mediumRed)) });
        TextAppearanceSpan textAppearanceSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, redColor, null);
        spannable.setSpan(textAppearanceSpan, startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannable);
    }

    /**
     * When the 'First Question' button is clicked:
     * Set the content of the activity to use the activity_flash_cards_intro.xml file
     */
    public void startQuiz(View view) {
        Intent intent = new Intent(this,Quiz1Activity.class);
        startActivity(intent);
    }

}
