package com.example.mutetest.otherfiles;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mutetest.Fragment.LoginFra;
import com.example.mutetest.Fragment.RegisterFra;

public class PagerAdapterClass extends FragmentStatePagerAdapter {

    int numberOfTabs;

    public PagerAdapterClass(FragmentManager fm, int numberOfTabs){
        super(fm);
        this.numberOfTabs=numberOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                RegisterFra register=new RegisterFra();
                return register;
            case 1:
                LoginFra login=new LoginFra();
                return login;

                default:
                    return null;
        }

    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
