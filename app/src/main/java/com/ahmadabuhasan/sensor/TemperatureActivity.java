package com.ahmadabuhasan.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Ahmad Abu Hasan on 4/10/2020.
 */

public class TemperatureActivity extends AppCompatActivity implements SensorEventListener {

    private TextView textView2;
    private SensorManager sm;
    private Sensor mTemperature;
    private Boolean SA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        setTitle("TYPE_AMBIENT_TEMPERATURE");

        textView2 = findViewById(R.id.textView2);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sm.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            mTemperature = sm.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE); //Type Sensor
            SA = true;
        } else {
            textView2.setText("Sorry, sensor not available for this device.");
            SA = false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.values[0] < 38) {
            textView2.setText(sensorEvent.values[0] + " °C");
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        } else {
            textView2.setText(sensorEvent.values[0] + " °C");
            getWindow().getDecorView().setBackgroundColor(Color.RED);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SA) {
            sm.registerListener(this, mTemperature, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (SA) {
            sm.unregisterListener(this);
        }
    }
}