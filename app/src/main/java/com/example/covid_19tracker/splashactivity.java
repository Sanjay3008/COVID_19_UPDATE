package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class splashactivity extends AppCompatActivity {

    private long splashtime=1000,ms=0;
    boolean splashctive=true,paused=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashactivity);

        Thread thread = new Thread() {
            public void run() {
                try {
                    while (splashctive && ms < splashtime) {
                        if (!paused) {
                            ms += 150;
                        }
                        sleep(100);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if(!isonline()){
                        ConstraintLayout cl = findViewById(R.id.cl);
                        Snackbar snackbar = Snackbar
                                .make(cl,"Check Your Internet connection",Snackbar.LENGTH_INDEFINITE)
                                .setAction("Retry", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        recreate();
                                    }
                                });
                        snackbar.show();
                    }
                    else{
                        gomain();
                    }
                }
            }
        };
        thread.start();
    }
    private boolean isonline() {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo()!=null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
    public void gomain(){
        Intent intent = new Intent(splashactivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}


