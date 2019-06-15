package com.example.mutetest.otherfiles;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUser {
    SharedPreferences user;
    String id,name,mobile,address,gender,otp,coaching,temp;
    Context ctx;
    public SharedPreferencesUser(Context ctx){
        this.ctx=ctx;
        user=ctx.getSharedPreferences("mutetest",Context.MODE_PRIVATE);
    }
    public String getId(){
        return user.getString("id","");
    }
    public void setId(String id){
        user.edit().putString("id",id).commit();
    }
    public String getName(){
        return user.getString("name","");
    }
    public void setName(String name){
        user.edit().putString("name",name).commit();
    }
    public String getMobile(){
        return user.getString("mobile","");
    }
    public void setMobile(String mobile){
        user.edit().putString("mobile",mobile).commit();
    }
    public String getAddress(){
        return user.getString("address","");
    }
    public void setAddress(String address){
        user.edit().putString("address",address).commit();
    }
    public String getGender(){
        return user.getString("gender","");
    }
    public void setGender(String gender){
        user.edit().putString("gender",gender).commit();
    }
    public String getOtp(){
        return user.getString("otp","");
    }
    public void setOtp(String otp){
        user.edit().putString("otp",otp).commit();
    }
    public String getCoaching(){
        return user.getString("coaching","");
    }
    public void setCoaching(String coaching){
        user.edit().putString("coaching",coaching).commit();
    }
    public String getTemp(){
        return user.getString("temp","");
    }
    public void setTemp(String temp){
        user.edit().putString("temp",temp).commit();
    }
}
