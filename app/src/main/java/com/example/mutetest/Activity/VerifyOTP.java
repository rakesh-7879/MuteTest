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

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;

public class VerifyOTP extends AppCompatActivity {

    EditText otp;
    private String oldotp,activiy;
    Button verify;
    Intent gotoNext;
    ConnectionClass connectionClass=new ConnectionClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_verify_otp);
        otp=(EditText)findViewById(R.id.otp);
        Intent thisIntent=getIntent();
        if(thisIntent.hasExtra("otp")){
            oldotp=thisIntent.getStringExtra("otp");
        }
        if(thisIntent.hasExtra("activity")){
            activiy=thisIntent.getStringExtra("activity");
        }
        verify=(Button)findViewById(R.id.verify);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    try {
                        Connection connection = connectionClass.CONN();
                        Statement statement = connection.createStatement();
                        if(otp.getText().toString().equals(oldotp)) {
                            if (activiy.equals("Home")) {
                                gotoNext = new Intent(VerifyOTP.this, Home.class);

                            } else {
                                gotoNext.putExtra("otp", oldotp);
                                gotoNext = new Intent(VerifyOTP.this, ForgetPassword.class);
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "your otp is worng please try again. new otp=" + otp.getText(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (SQLException ex){
                        Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }
}
