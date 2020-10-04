package com.ahmadabuhasan.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Ahmad Abu Hasan on 4/10/2020.
 */

public class PressureActivity extends AppCompatActivity implements SensorEventListener {

    private TextView textView9;
    private SensorManager sm;
    private Sensor mPress;
    private Boolean SA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure);
        setTitle("TYPE_PRESSURE");

        textView9 = findViewById(R.id.textView9);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sm.getDefaultSensor(Sensor.TYPE_PRESSURE) != null) {
            mPress = sm.getDefaultSensor(Sensor.TYPE_PRESSURE); //Type Sensor
            SA = true;
        } else {
            textView9.setText("Sorry, sensor not available for this device.");
            SA = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SA) {
            sm.registerListener(this, mPress, SensorManager.SENSOR_DELAY_NORMAL);
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
        if (sensorEvent.values[0] < 550.0) {
            textView9.setText(sensorEvent.values[0] + " hPa");
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        } else {
            textView9.setText(sensorEvent.values[0] + " hPa");
            getWindow().getDecorView().setBackgroundColor(Color.RED);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}