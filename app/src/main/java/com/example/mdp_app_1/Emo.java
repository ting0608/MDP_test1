package com.example.mdp_app_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class Emo extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //Variables here
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton imageButton;

    //timer variables
    SeekBar timer_sb;
    TextView timer_tv;
    Button start_btn;
    CountDownTimer countDownTimer;
    Boolean counterIsActive = false;

    //media player
    MediaPlayer mediaPlayer;
    boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emo);

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

        timer_sb = findViewById(R.id.timer_sb);
        timer_tv = findViewById(R.id.timer_tv);
        start_btn = findViewById(R.id.start_btn);


        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.irwtsayh);

        timer_sb.setMax(300);
        timer_sb.setProgress(30);
        timer_sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                update(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
                start_timer();

            }
        });
    }



    private void update (int progress){
        int minutes = progress/60;
        int seconds = progress%60;
        String secondsFinal = "";
        if (seconds <= 9){
            secondsFinal = "0" + seconds;

        }else{
            secondsFinal = "" + seconds;
        }
        timer_sb.setProgress(progress);
        timer_tv.setText("" + minutes + ":" + secondsFinal);


    }

    public void start_timer(){

        if(counterIsActive == false){
            counterIsActive = true;
            timer_sb.setEnabled(false);
            start_btn.setText("STOP");

            countDownTimer = new CountDownTimer(timer_sb.getProgress() * 1000, 1000) {
                @Override
                //msFin is millis_second until finish
                public void onTick(long msFin) {

                    update((int) msFin/1000);


                }

                @Override
                public void onFinish() {
                    mediaPlayer.pause();
                    reset();

                }
            }.start();
        }else{
            mediaPlayer.pause();
            reset();
        }

    }

    private void reset(){

        timer_tv.setText("0:30");
        timer_sb.setProgress(30);
        countDownTimer.cancel();
        start_btn.setText("START");
        timer_sb.setEnabled(true);
        counterIsActive = false;
    }



    @Override
    protected void onPause() {
        mediaPlayer.pause();
        super.onPause();
        if(counterIsActive){
            countDownTimer.cancel();

        }
    }

    @Override
    protected void onDestroy(){
        mediaPlayer.pause();
        super.onDestroy();
        if(counterIsActive){
            mediaPlayer.pause();
            countDownTimer.cancel();
        }
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
                Intent intent = new Intent(Emo.this, MainScreen.class);
                startActivity(intent);
                finish();

                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
                break;

            case R.id.nav_gallery:
                break;

            case R.id.nav_contact:
                Intent intent1 = new Intent(Emo.this, ContactScreen.class);
                startActivity(intent1);
                finish();

                overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up);
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}