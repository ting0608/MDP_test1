package com.example.mdp_app_1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ViewPager SlideViewPager;
    LinearLayout DotLayout;
    Button back, next, skip;


    TextView[] dots;
    ViewPageAdapter viewPageAdapter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        back = findViewById(R.id.backbtn);
        next = findViewById(R.id.nextbtn);
        skip = findViewById(R.id.skipButton);



        //back listener
        //if user press this get the pos and make a compare
        //0 is the 1st page, if user press back, get item > 0, turn it back by -1
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (getItem(0) > 0){

                    SlideViewPager.setCurrentItem(getItem(-1), true);

                }

            }
        });

        //next listener
        //0123, 3 is the last page, if user press this in 3(4th page), go to new intent
        //if < 3, +1
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (getItem(0)<3)
                    SlideViewPager.setCurrentItem(getItem(1),true);

                else {
                        Intent i = new Intent(MainActivity.this,MainScreen.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();


                    }
                }


        });

        //skip listener
        //if user press this go to main screen by calling new intent and start act
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this,MainScreen.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();

            }
        });

        SlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        DotLayout = (LinearLayout) findViewById(R.id.indicator_layout);

        viewPageAdapter = new ViewPageAdapter(this);
        SlideViewPager.setAdapter(viewPageAdapter);

        setUpindicator(0);
        SlideViewPager.addOnPageChangeListener(viewListener);
    }


        //this part is slider dot indicator
        //4 dots
        @RequiresApi(api = Build.VERSION_CODES.M)
        public void setUpindicator(int position){
            dots = new TextView[4];
            DotLayout.removeAllViews();

            //for loop to loop the dots action(length of it was 4)
            for (int i = 0 ; i < dots.length ; i++){

                dots[i] = new TextView(this);
                dots[i].setText(Html.fromHtml("&#8226"));
                dots[i].setTextSize(35);
                dots[i].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));
                DotLayout.addView(dots[i]);

            }

            dots[position].setTextColor(getResources().getColor(R.color.active,getApplicationContext().getTheme()));
        }

        //on page listener to listen whether the page was selected or nah (using on page selected method)
        ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onPageSelected(int position) {

                setUpindicator(position);
                if (position > 0){
                    back.setVisibility(View.VISIBLE);
                }
                else{
                    back.setVisibility(View.INVISIBLE);

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };

    //this method is created to GET the position of slider,
    private int getItem(int i){
        return SlideViewPager.getCurrentItem() + i;
    }

    }
