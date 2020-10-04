package com.ahmadabuhasan.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class OrientationActivity extends AppCompatActivity {

    private TextView textView8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation);
        setTitle("TYPE_ORIENTATION");
    }
}