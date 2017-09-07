package com.example.home.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    public void getTemp(View view) {
        display= (TextView) findViewById(R.id.text);
        try {
            String temp = (new UrlJsonParser().getTemp("Houston"));
            display.setText(temp + " C");
        }
        catch(Exception e)
        {
            display.setText("Does not work");
        }
    }
}
