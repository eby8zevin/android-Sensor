package com.ahmadabuhasan.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class TempActivity extends AppCompatActivity {

    private TextView textView13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        setTitle("TYPE_TEMPERATURE");
    }
}