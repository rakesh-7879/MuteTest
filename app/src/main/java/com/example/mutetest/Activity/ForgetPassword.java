package com.example.mutetest.Activity;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mutetest.ConnectionClass;
import com.example.mutetest.R;
import com.example.mutetest.otherfiles.SmsBackgrond;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class ForgetPassword extends AppCompatActivity implements View.OnClickListener {

    LinearLayout screen1,screen2;
    ConstraintLayout result;
    EditText rnumber,newpassword,confirmpassword;
    TextView title,name,address,gender;
    Button findme,nextResult,getOTP,submit;
    ResultSet resultSet;
    ConnectionClass connectionClass=new ConnectionClass();
    String mobile="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forget_password);
        Intent thisIntent=getIntent();
        result=(ConstraintLayout)findViewById(R.id.fresult);
        screen1=(LinearLayout)findViewById(R.id.screen1);
        screen2=(LinearLayout)findViewById(R.id.screen2);
        if(thisIntent.hasExtra("mobile")){
            mobile=thisIntent.getStringExtra("mobile");
            screen1.setVisibility(View.GONE);
            screen2.setVisibility(View.VISIBLE);
        }else{
            screen1.setVisibility(View.VISIBLE);
            screen2.setVisibility(View.GONE);
        }
        rnumber=findViewById(R.id.rnumber);
        newpassword=findViewById(R.id.newpassword);
        confirmpassword=findViewById(R.id.confirmpassword);
        title=findViewById(R.id.title);
        name=findViewById(R.id.fname);
        gender=(TextView)findViewById(R.id.fgender);
        address=findViewById(R.id.faddress);
        nextResult=findViewById(R.id.nextresult);
        getOTP=findViewById(R.id.getotp);
        findme=findViewById(R.id.findme);
        submit=findViewById(R.id.submit);

        findme.setOnClickListener(this);
        nextResult.setOnClickListener(this);
        getOTP.setOnClickListener(this);
        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.findme:
                mobile=rnumber.getText().toString();
                try{
                    Connection connection=connectionClass.CONN();
                    Statement statement=connection.createStatement();
                    resultSet=statement.executeQuery("select * from users where user_mobile like '%"+ mobile+ "%'");
                    if(resultSet.next()){
                        result.setVisibility(View.VISIBLE);
                        title.setText("Result");
                        name.setText(resultSet.getString(2));
                        gender.setText(resultSet.getString(5));
                        address.setText(resultSet.getString(4));
                    }else{
                        Toast.makeText(this,"no result found",Toast.LENGTH_SHORT).show();
                    }
                }catch (SQLException ex){
                    Toast.makeText(this,ex.toString(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.nextresult:
                try {
                    if (resultSet.next()) {
                        name.setText(resultSet.getString(2));
                        gender.setText(resultSet.getString(5));
                        address.setText(resultSet.getString(4));
                    }else{
                        resultSet.first();
                    }
                }catch (SQLException ex){
                    Toast.makeText(this,"no result found",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.getotp:
                try {
                    Random random = new Random();
                    String otp = String.format("%04d", random.nextInt(10000));
                    SmsBackgrond backgroudWorker = new SmsBackgrond(this);
                    backgroudWorker.execute(resultSet.getString(3), otp);
                    Intent i = new Intent(ForgetPassword.this, VerifyOTP.class);
                    i.putExtra("name", name.getText().toString());
                    i.putExtra("gender", gender.getText().toString());
                    i.putExtra("mobile", resultSet.getString(3));
                    i.putExtra("password", resultSet.getString(8));
                    i.putExtra("otp", otp);
                    i.putExtra("activity","ForgetPassword");
                    startActivity(i);
                    finish();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.submit:
                if(newpassword.getText().toString().equals(confirmpassword.getText().toString())){
                    try {
                        Connection connection = connectionClass.CONN();
                        Statement statement = connection.createStatement();
                        statement.execute("update users set password='" + newpassword.getText().toString() + "' where user_mobile='" + mobile + "'");
                        Intent next = new Intent(ForgetPassword.this, Home.class);
                        startActivity(next);
                        finish();
                    }catch (SQLException ex){
                        Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}
