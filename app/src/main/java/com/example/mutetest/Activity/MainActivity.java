package com.example.mutetest.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.mutetest.R;
import com.example.mutetest.Test;
import com.example.mutetest.otherfiles.SharedPreferencesUser;

public class MainActivity extends AppCompatActivity {

    SharedPreferencesUser user;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        user=new SharedPreferencesUser(this);
        String mobile=user.getMobile();
        String otp=user.getOtp();
        String name=user.getName();
        String gender=user.getGender();
        String address=user.getAddress();
        String coaching=user.getCoaching();
        i= new Intent(MainActivity.this, Login.class);
        if(mobile.equals("")){
            i= new Intent(MainActivity.this, Login.class);
        }else if(otp.equals("")){
            Toast.makeText(getApplicationContext(),"OTP is not set",Toast.LENGTH_SHORT).show();
        }else if(name.equals("")){
            Toast.makeText(getApplicationContext(),"Name is not set",Toast.LENGTH_SHORT).show();
        }else if(gender.equals("")){
            Toast.makeText(getApplicationContext(),"Gender is not set",Toast.LENGTH_SHORT).show();
        }else{
            i=new Intent(MainActivity.this, Home.class);
        }
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            public void run() {

                startActivity(i);
                finish();
            }
        }, 8*1000);
    }
}
