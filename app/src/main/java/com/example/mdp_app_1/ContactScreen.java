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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    //Variables here
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    //database, use google firebase
    DatabaseReference cont_reff;
    EditText txtname, txtphone, txtemail;
    Button submitBtn;
    Member member;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_screen);

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
        navigationView.setCheckedItem(R.id.nav_contact);

        //field text and btn for database
        txtname = (EditText) findViewById(R.id.name);
        txtemail = (EditText) findViewById(R.id.email_2);
        txtphone = (EditText) findViewById(R.id.Phone_2);
        submitBtn = (Button) findViewById(R.id.contact_submit);

        member = new Member();
        cont_reff = FirebaseDatabase.getInstance().getReference().child("Member");

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long phn = Long.parseLong(txtphone.getText().toString().trim());
                member.setName(txtname.getText().toString().trim());
                member.setMail(txtemail.getText().toString().trim());
                member.setPh(phn);
                cont_reff.push().setValue(member);
                Toast.makeText(ContactScreen.this, "Data submitted successfully", Toast.LENGTH_LONG).show();


            }
        });
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
                Intent intent = new Intent(ContactScreen.this, MainScreen.class);
                startActivity(intent);
                finish();

                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
                break;

            case R.id.nav_gallery:
                Intent intent1 = new Intent(ContactScreen.this, GalleryScreen.class);
                startActivity(intent1);
                finish();

                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
                break;


            case R.id.nav_contact:
                break;
    }
        drawerLayout.closeDrawer(GravityCompat.START);



        return true;


}
}