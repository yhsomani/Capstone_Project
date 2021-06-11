package com.example.vchat;
/*
 ****************************
 * Develop by Yash Somani
 * **************************
 */
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class DashboardActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        bottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(HomeActivity.newInstance("", ""));

    }
    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.home:
                            openFragment(HomeActivity.newInstance("", ""));
                            return true;
                        case R.id.history:
                            openFragment(History.newInstance("", ""));
                            return true;
                        case R.id.setting:
                            openFragment(About.newInstance("", ""));
                            return true;
                        case R.id.About:
                            openFragment(Logout.newInstance("", ""));
                            return true;
                    }
                    return false;
                }
            };
}