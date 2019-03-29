package com.uottawa.vedant.cookhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

// Opening screen for the app.
public class HomeScreen extends AppCompatActivity {

    RelativeLayout main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        main = (RelativeLayout) findViewById(R.id.activity_home_screen);
        final Intent mainpage = new Intent(getApplicationContext(), MainActivity.class);
        main.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //Starting a new Intent
                startActivity(mainpage);
            }
        });
    }


}
