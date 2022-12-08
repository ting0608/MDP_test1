package com.example.mdp_app_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;

public class GalleryScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //Variables here
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_screen);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        //navigation item menu
        //hide or show items (could use this if needed)

        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_login).setVisible(false);
        menu.findItem(R.id.nav_logout).setVisible(false);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        //this is to make the drawer components show highlighted when open
        navigationView.setCheckedItem(R.id.nav_gallery);

        //Set onclick listener for 1st imagebutton(emo)
        imageButton = (ImageButton) findViewById(R.id.Emo);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(GalleryScreen.this, Emo.class);
                startActivity(intentLoadNewActivity);
            }
        });

        //Set onclick listener for 1st imagebutton(anxiety)
        imageButton = (ImageButton) findViewById(R.id.Anxiety);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(GalleryScreen.this, Anxious.class);
                startActivity(intentLoadNewActivity);
            }
        });


        //Set onclick listener for 1st imagebutton(mood)
        imageButton = (ImageButton) findViewById(R.id.Mood);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(GalleryScreen.this, Mood.class);
                startActivity(intentLoadNewActivity);
            }
        });
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
                Intent intent = new Intent(GalleryScreen.this, MainScreen.class);
                startActivity(intent);
                finish();

                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
                break;

            case R.id.nav_gallery:
                break;

            case R.id.nav_contact:
                Intent intent1 = new Intent(GalleryScreen.this, ContactScreen.class);
                startActivity(intent1);
                finish();

                overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up);
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

}