package com.example.android.uscitizenshipquiz;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class FlashCardsActivity extends AppCompatActivity {

    // initialize variables and load images
    Button b_prev, b_next;
    ImageSwitcher imageSwitcher;

    Integer[] images = {R.drawable.slide001, R.drawable.slide005,
            R.drawable.slide006, R.drawable.slide007, R.drawable.slide008, R.drawable.slide009, R.drawable.slide010,
            R.drawable.slide011, R.drawable.slide012, R.drawable.slide013, R.drawable.slide014, R.drawable.slide015,
            R.drawable.slide016, R.drawable.slide017, R.drawable.slide018, R.drawable.slide019, R.drawable.slide020,
            R.drawable.slide021, R.drawable.slide022, R.drawable.slide023, R.drawable.slide024};

    int i = 0;
    Animation in, out, in_prev, out_prev;

    // variables for screen height to get ideal flash card height
    DisplayMetrics metrics;
    int screenWidth = 0, screenHeight = 0;
    int totalScreenHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards);

        // get screen height to resize the flash card
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenHeight = metrics.heightPixels;
        screenWidth = metrics.widthPixels;
        // Calculate ActionBar height to subtract from screen height
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            int actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
            // get total available screen height by deducting action bar height from screen height
            totalScreenHeight = screenHeight - actionBarHeight - 250;
        }


        // set view to landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        // image switcher
        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory(){
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageView.setMaxHeight(totalScreenHeight);
                imageView.setAdjustViewBounds(true);
                imageView.setLayoutParams(
                        new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                return imageView;
            }
        });

        // animate next and previous flash cards
        in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.in);
        out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.out);
        in_prev = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.in_prev);
        out_prev = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.out_prev);

        b_prev = (Button) findViewById(R.id.b_prev);
        b_next = (Button) findViewById(R.id.b_next);

        // initialize
        if(i == 0){
            imageSwitcher.setImageResource(images[0]);
        }

        // when NEXT slide button is clicked, animate and count
        b_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i > 0){
                    imageSwitcher.setInAnimation(in_prev);
                    imageSwitcher.setInAnimation(out_prev);

                    i--;
                    imageSwitcher.setImageResource(images[i]);
                }
                else if(i == 0){
                    i = images.length - 1;
                    imageSwitcher.setImageResource(images[i]);
                }
            }
        });

        // when PREV slide button is clicked, animate and count
        b_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageSwitcher.setInAnimation(in);
                imageSwitcher.setInAnimation(out);

                if(i < images.length - 1){
                    i++;
                    imageSwitcher.setImageResource(images[i]);
                }
                else if(i == images.length -1){
                    i = 0;
                    imageSwitcher.setImageResource(images[i]);
                }
            }
        });

    }
}