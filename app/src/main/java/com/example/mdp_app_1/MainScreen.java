package com.example.mdp_app_1;

import static android.Manifest.permission.CALL_PHONE;
import static android.content.Intent.ACTION_CALL;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//import android.widget.Toolbar;

public class MainScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //Variables here
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton call;
    TextView showEmail;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        //navigation item menu

        //hide or show items (could use this if needed)

        //Menu menu = navigationView.getMenu();
        //menu.findItem(R.id.nav_logout).setVisible(false);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        //this is to make the drawer components show highlighted when open
        navigationView.setCheckedItem(R.id.nav_home);

        //display username

        //onclick listener
        call = findViewById(R.id.call_2);
        call.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                /*Intent i = new Intent(MainScreen.this,ContactScreen.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up);
                finish();*/

                Intent callIntent = new Intent(ACTION_CALL);
                callIntent.setData(Uri.parse("tel:123457890"));
                if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(callIntent);
                } else {
                    requestPermissions(new String[]{CALL_PHONE}, 1);

                }
            }
        })


    ;}

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
                break;

            case R.id.nav_gallery:
                Intent intent = new Intent(MainScreen.this, GalleryScreen.class);
                startActivity(intent);
                finish();

                overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up);
                break;

            case R.id.nav_contact:
                Intent intent1 = new Intent(MainScreen.this, ContactScreen.class);
                startActivity(intent1);
                finish();

                overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up);
                break;

            case R.id.nav_login:
                Intent intent2 = new Intent(MainScreen.this, LoginScreen.class);
                startActivity(intent2);
                finish();

                overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up);
                break;

            case R.id.nav_logout:
                FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(MainScreen.this, MainScreen.class);
                startActivity(intent3);
                finish();

                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
                break;

        }


        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

}