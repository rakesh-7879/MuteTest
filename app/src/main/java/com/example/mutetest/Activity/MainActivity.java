package com.example.mutetest.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.mutetest.R;
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
        if(mobile.equals("")){
            i= new Intent(MainActivity.this, Login.class);
        }else if(otp.equals("")){

        }else if(name.equals("")){

        }else if(gender.equals("")){

        }else if(address.equals("")){

        }else if(coaching.equals("")){

        }else{

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
