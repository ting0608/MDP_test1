package com.example.mdp_app_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Arrays;
import java.util.Random;

public class Anxious extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //Variables here
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton imageButton;

    //random the wheel
    private static final String [] sectors = {"Go for some creative ideas!","Ask someone for help, or chitchat with them was also fine!","Pause yourself for 5 mins!","Try to stand up and walking around","Drink some water, Cool yourself down","Deep breath..breath..breath"};
    private static final int[] sectorDegrees = new int[sectors.length];
    private static final Random random = new Random();

    private int degree = 0;
    private boolean isSpinning = false;
    private ImageView wheel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anxious);

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

        /*
        //Set onclick listener for 1st imagebutton(guilty)
        imageButton = (ImageButton) findViewById(R.id.Guilty);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(GalleryScreen.this, Guilty.class);
                startActivity(intentLoadNewActivity);
            }
        });

         */

        final Button spin = findViewById(R.id.spinbutton);
        wheel = findViewById(R.id.wheel);

        getDegreeForSectors();

        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isSpinning){
                    spin();
                    isSpinning = true;
                }

            }
        });


    }

    private void spin(){
        degree = random.nextInt(sectors.length-1);
        RotateAnimation rotateAnimation = new RotateAnimation(0, (360* sectors.length)+sectorDegrees[degree],
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF,0.5f);

        //spinning duration
        rotateAnimation.setDuration(3600);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //toast message, making string array to string and only calling for the results sectors string
                Toast.makeText(Anxious.this, ""+ Arrays.toString(new String[]{sectors[sectors.length - (degree + 1)]}), Toast.LENGTH_SHORT).show();
                isSpinning = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        wheel.startAnimation(rotateAnimation);


    }

    private void getDegreeForSectors(){
        int sectorDegree = 360/ sectors.length;

        for (int i=0; i < sectors.length; i++){
            sectorDegrees[i] = (i+1)*sectorDegree;
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
                Intent intent = new Intent(Anxious.this, MainScreen.class);
                startActivity(intent);
                finish();

                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
                break;

            case R.id.nav_gallery:
                break;

            case R.id.nav_contact:
                Intent intent1 = new Intent(Anxious.this, ContactScreen.class);
                startActivity(intent1);
                finish();

                overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up);
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

}