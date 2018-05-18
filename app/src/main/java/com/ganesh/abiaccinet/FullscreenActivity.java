package com.ganesh.abiaccinet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


public class FullscreenActivity extends AppCompatActivity {
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                android.net.NetworkInfo wifi = cm
                        .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                android.net.NetworkInfo datac = cm
                        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if (!((wifi != null & datac != null)
                        && (wifi.isConnected() || datac.isConnected()))) {
                    //no connection
                    Intent intent = new Intent(FullscreenActivity.this, OffProfileActivity.class);
                    Toast.makeText(getApplicationContext(),"Offline Mode",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else {

                    Intent intent = new Intent(FullscreenActivity.this, LoginActivity.class);
                    startService(new Intent(FullscreenActivity.this,NotificationService.class).putExtra("complaint","Accident occured at nagpur"));
                    startActivity(intent);
                }
            }
        }, 1000);
    }




}
