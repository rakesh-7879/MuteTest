package com.example.mutetest.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mutetest.ConnectionClass;
import com.example.mutetest.R;
import com.example.mutetest.otherfiles.SharedPreferencesUser;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;

public class VerifyOTP extends AppCompatActivity {

    EditText otp;
    private String oldotp,activiy;
    Button verify;
    Intent gotoNext,thisIntent;
    ConnectionClass connectionClass=new ConnectionClass();
    String name="", gender="", mobile="", password="",query="";
    SharedPreferencesUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_verify_otp);
        otp=(EditText)findViewById(R.id.otp);
        user=new SharedPreferencesUser(getApplicationContext());
        thisIntent=getIntent();
        if(thisIntent.hasExtra("otp")){
            oldotp=thisIntent.getStringExtra("otp");
        }
        if(thisIntent.hasExtra("activity")){
            activiy=thisIntent.getStringExtra("activity");
        }
        if (thisIntent.hasExtra("name") && thisIntent.hasExtra("gender") && thisIntent.hasExtra("mobile") && thisIntent.hasExtra("password")) {
            name = thisIntent.getStringExtra("name");
            gender = thisIntent.getStringExtra("gender");
            mobile = thisIntent.getStringExtra("mobile");
            password = thisIntent.getStringExtra("password");
        }
        verify=(Button)findViewById(R.id.verify);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    try {
                        Connection connection = connectionClass.CONN();
                        Statement statement = connection.createStatement();
                            if (otp.getText().toString().equals(oldotp)) {


                                if (activiy.equals("Home")) {
                                    gotoNext = new Intent(VerifyOTP.this, Home.class);
                                    user.setName(name);
                                    user.setMobile(mobile);
                                    user.setGender(gender);
                                    user.setOtp(oldotp);

                                    query = "insert into users values(null,'" + name + "','" + mobile + "','','" + gender + "','" + oldotp + "','','" + password + "','1')";
                                } else {
                                    user.setName(name);
                                    user.setMobile(mobile);
                                    user.setGender(gender);
                                    user.setOtp(oldotp);
                                    gotoNext = new Intent(VerifyOTP.this, ForgetPassword.class);
                                    gotoNext.putExtra("otp", oldotp);
                                    gotoNext.putExtra("mobile",mobile);
                                    query = "update users set otp='" + oldotp + "' where user_mobile='" + mobile + "'";
                                }
                                statement.execute(query);
                                startActivity(gotoNext);
                                finish();

                            } else {
                                Toast.makeText(getApplicationContext(), "your otp is worng please try again.", Toast.LENGTH_SHORT).show();
                            }
                    }catch (SQLException ex){
                        Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }
}
