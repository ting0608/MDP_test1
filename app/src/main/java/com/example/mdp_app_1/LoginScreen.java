package com.example.mdp_app_1;

import static android.Manifest.permission.CALL_PHONE;
import static android.content.Intent.ACTION_CALL;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

//import android.widget.Toolbar;

public class LoginScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //Variables here
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    //login and register
    EditText logEmail;
    EditText logPW;
    TextView registerHere;
    Button logBtn;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

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
        navigationView.setCheckedItem(R.id.nav_login);

        //database findview and check user existing or not
        logEmail = findViewById(R.id.email_login2);
        logPW = findViewById(R.id.PW_log2);
        logBtn = findViewById(R.id.login_btn);
        registerHere = findViewById(R.id.RegisterHere);

        mAuth = FirebaseAuth.getInstance();
        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        registerHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegScreen();
            }
        });


        }

    private void openRegScreen() {
        Intent intent = new Intent(this, RegisterScreen.class);
        startActivity(intent);
    }

    private void loginUser() {
        String email = logEmail.getText().toString();
        String password = logPW.getText().toString();

        if (TextUtils.isEmpty(email)){
            logEmail.setError("Email cannot be empty");
            logEmail.requestFocus();
        } else if (TextUtils.isEmpty((password))) {
            logPW.setError("Password cannot be empty");
            logPW.requestFocus();

        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        String email = mAuth.getCurrentUser().getEmail();

                        Toast.makeText(LoginScreen.this, "Welcome! "+email, Toast.LENGTH_LONG).show();
                        Intent intent0 = new Intent(LoginScreen.this, MainScreen.class);
                        startActivity(intent0);
                        finish();
                    }else{
                        Toast.makeText(LoginScreen.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
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
                Intent intent0 = new Intent(LoginScreen.this, MainScreen.class);
                startActivity(intent0);
                finish();

                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
                break;

            case R.id.nav_gallery:
                Intent intent = new Intent(LoginScreen.this, GalleryScreen.class);
                startActivity(intent);
                finish();

                overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up);
                break;

            case R.id.nav_contact:
                Intent intent1 = new Intent(LoginScreen.this, ContactScreen.class);
                startActivity(intent1);
                finish();

                overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up);
                break;

            case R.id.nav_logout:
                //Intent intent2 = new Intent(LoginScreen.this, LoginScreen.class);
                //startActivity(intent2);
                //finish();

                //overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up);
                //break;

        }


        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

}