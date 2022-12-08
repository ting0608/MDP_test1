package com.example.mdp_app_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Mood extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //Variables here
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton imageButton;

    //countdown timer
    private CountDownTimer countDownTimer;
    private long timeleftms = 10000;
    private boolean timerRunning;

    private boolean happy = false, sad = false, angry = false;
    int happyNum = 0, sadNum = 0, angryNum = 0;
    TextView AngValue, SadValue, HapValue;
    
    LottieAnimationView lottieSad, lottieAngry, lottieHappy;

    ArrayList barArraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        //navigation item menu
        //hide or show items (could use this if needed)
        /*
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_gallery).setVisible(false);*/

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        //this is to make the drawer components show highlighted when open
        navigationView.setCheckedItem(R.id.nav_gallery);

        //check notification sdk
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("my notification", "my notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        //here to find lottie button's id
        lottieHappy = findViewById(R.id.happy);
        lottieAngry = findViewById(R.id.angry);
        lottieSad = findViewById(R.id.sad);

        AngValue = (TextView) findViewById(R.id.angry_value);
        HapValue = (TextView) findViewById(R.id.happy_value);
        SadValue = (TextView) findViewById(R.id.sad_value);

        lottieHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(happy){

                    lottieHappy.setMinAndMaxProgress(0.5f, 1.0f);
                    lottieHappy.playAnimation();
                    happy = false;
                    HappyDec();


                }else{
                    lottieHappy.setMinAndMaxProgress(0.0f, 0.5f);
                    lottieHappy.playAnimation();
                    happy = true;
                    HappyInc();
                }
            }
        });

        lottieSad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sad){
                    lottieSad.setMinAndMaxProgress(0.5f, 1.0f);
                    lottieSad.playAnimation();
                    sad = false;
                    SadDec();

                }else{
                    lottieSad.setMinAndMaxProgress(0.0f, 0.5f);
                    lottieSad.playAnimation();
                    sad = true;
                    SadInc();
                }
            }
        });

        lottieAngry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(angry){

                    lottieAngry.setMinAndMaxProgress(0.5f, 1.0f);
                    lottieAngry.playAnimation();
                    angry = false;
                    AngryDec();


                }else{
                    lottieAngry.setMinAndMaxProgress(0.0f, 0.5f);
                    lottieAngry.playAnimation();
                    angry = true;
                    AngryInc();

                }
            }
        });


        //this part for barchart
//        getData();
//        BarChart barchart = findViewById(R.id.barchart);
//        BarDataSet barDataSet = new BarDataSet(barArraylist,"Mood track");
//        BarData barData = new BarData(barDataSet);
//        barchart.setData(barData);
//
//        //color
//        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//        barDataSet.setValueTextColor(Color.BLACK);
//        barDataSet.setValueTextSize(16f);
//        barchart.getDescription().setEnabled(true);
    }

//    private void getData(){
//        barArraylist = new ArrayList();
//        barArraylist.add(new BarEntry(2f,happyNum));
//        barArraylist.add(new BarEntry(3f,sadNum));
//        barArraylist.add(new BarEntry(4f,angryNum));
//    }

    private void AngryInc() {
        Toast.makeText(Mood.this, "You feel angry? Chill", Toast.LENGTH_SHORT).show();
        angryNum++;
        AngValue.setText("Angry = " + angryNum);
        startTimer();
    }

    private void AngryDec() {
        angryNum--;
        AngValue.setText("Angry = " + angryNum);
        stopTimer();
    }

    private void HappyInc() {
        Toast.makeText(Mood.this, "Good mood brings good day!", Toast.LENGTH_SHORT).show();
        happyNum++;
        HapValue.setText("Happy = " + happyNum);
        startTimer();
    }

    private void HappyDec() {
        happyNum--;
        HapValue.setText("Happy = " + happyNum);
        stopTimer();
    }

    private void SadInc() {
        Toast.makeText(Mood.this, "Why you feel sad?", Toast.LENGTH_SHORT).show();
        sadNum++;
        SadValue.setText("Sad = " + sadNum);
        startTimer();
    }

    private void SadDec() {
        sadNum--;
        SadValue.setText("Sad = " + sadNum);
        stopTimer();
    }

    private void CDNotify(){
        //notification codes here
        NotificationCompat.Builder builder = new NotificationCompat.Builder(Mood.this,"my notification");
        builder.setContentTitle("MentalWare Cooldown finish");
        builder.setContentText("Time to add your feelings today!");
        builder.setSmallIcon(R.drawable.home);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Mood.this);
        managerCompat.notify(1,builder.build());
    }

    private void startTimer(){
        countDownTimer = new CountDownTimer(timeleftms, 10000) {
            @Override
            public void onTick(long l) {
                timeleftms = 10000;
            }

            @Override
            public void onFinish() {
                lottieAngry.setMinAndMaxProgress(0.5f, 1.0f);
                lottieAngry.playAnimation();
                angry = false;

                lottieHappy.setMinAndMaxProgress(0.5f, 1.0f);
                lottieHappy.playAnimation();
                happy = false;

                lottieSad.setMinAndMaxProgress(0.5f, 1.0f);
                lottieSad.playAnimation();
                sad = false;

                CDNotify();
            }
        }.start();
    }

    private void stopTimer(){
        countDownTimer.cancel();
        timerRunning = false;

    }

    private void updatetimer(){
        int minutes = (int) timeleftms / 60000;
        int seconds = (int) timeleftms %60000 / 1000;

    }


    @Override
    public void onBackPressed(){

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        else{

            super.onBackPressed();

        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {

        //switch case to open different activity depends on R.id
        switch (menuitem.getItemId()){

            case R.id.nav_home:
                Intent intent = new Intent(Mood.this, MainScreen.class);
                startActivity(intent);
                finish();

                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
                break;

            case R.id.nav_gallery:
                break;

            case R.id.nav_contact:
                Intent intent1 = new Intent(Mood.this, ContactScreen.class);
                startActivity(intent1);
                finish();

                overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up);
                break;

            case R.id.nav_login:
                Intent intent2 = new Intent(Mood.this, LoginScreen.class);
                startActivity(intent2);
                finish();

                overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up);
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

}