package com.example.mutetest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mutetest.R;
import com.example.mutetest.otherfiles.SharedPreferencesUser;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    Intent gotonext;
    SharedPreferencesUser user;
    ImageView menuHeaderGender,playquiz,learning,highscore,feedback;
    TextView name,detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        user=new SharedPreferencesUser(this);
        playquiz=findViewById(R.id.playquiz);
        learning=findViewById(R.id.learning);
        highscore=findViewById(R.id.highscore);
        feedback=findViewById(R.id.feedback);

        playquiz.setOnClickListener(this);
        learning.setOnClickListener(this);
        highscore.setOnClickListener(this);
        feedback.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        menuHeaderGender=findViewById(R.id.menu_header_profile);
        name=findViewById(R.id.menu_name);
        detail=findViewById(R.id.textView);
        name.setText( user.getName());
        detail.setText(user.getMobile());
        if(user.getGender().equals("Male")){
            menuHeaderGender.setImageResource(R.drawable.man);
        }else{
            menuHeaderGender.setImageResource(R.drawable.woman);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_logout) {
            user.setName("");
            user.setMobile("");
            user.setGender("");
            user.setGender("");
            user.setAddress("");
            user.setCoaching("");
            gotonext=new Intent(getApplicationContext(),Login.class);
            startActivity(gotonext);
            finish();
        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.playquiz:
                Toast.makeText(getApplicationContext(),"start playing",Toast.LENGTH_SHORT).show();
                break;
            case R.id.learning:
                Toast.makeText(getApplicationContext(),"start learing",Toast.LENGTH_SHORT).show();
                break;
            case R.id.highscore:
                Toast.makeText(getApplicationContext(),"hithscore",Toast.LENGTH_SHORT).show();
                break;
            case R.id.feedback:
                Toast.makeText(getApplicationContext(),"feedback",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
