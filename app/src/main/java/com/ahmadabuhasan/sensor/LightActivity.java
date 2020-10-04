package com.ahmadabuhasan.sensor;

import androidx.appcompat.app.AppCompatActivity;

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

public class LightActivity extends AppCompatActivity implements SensorEventListener {

    private TextView textView5;
    private SensorManager sm;
    private Sensor mLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        setTitle("TYPE_LIGHT");

        textView5 = findViewById(R.id.textView5);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        mLight = sm.getDefaultSensor(Sensor.TYPE_LIGHT); //Type Sensor
    }

    @Override
    public void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        sm.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            if (sensorEvent.values[0] < 5) {
                textView5.setText(sensorEvent.values[0] + " lux");
                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
            } else {
                textView5.setText(sensorEvent.values[0] + " lux");
                getWindow().getDecorView().setBackgroundColor(Color.RED);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accurancy) {

    }
}