package com.ahmadabuhasan.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

import java.util.Collection;

/**
 * Created by Ahmad Abu Hasan on 4/10/2020.
 */

public class RelativeActivity extends AppCompatActivity implements SensorEventListener {

    private TextView textView11;
    private SensorManager sm;
    private Sensor mRela;
    private Boolean SA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative);
        setTitle("TYPE_RELATIVE_HUMIDITY");

        textView11 = findViewById(R.id.textView11);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sm.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) != null) {
            mRela = sm.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY); //Type Sensor
            SA = true;
        } else {
            textView11.setText("Sorry, sensor not available for this device.");
            SA = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SA) {
            sm.registerListener(this, mRela, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (SA) {
            sm.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.values[0] < 50.0) {
            textView11.setText(sensorEvent.values[0] + " %");
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        } else {
            textView11.setText(sensorEvent.values[0] + " %");
            getWindow().getDecorView().setBackgroundColor(Color.RED);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}