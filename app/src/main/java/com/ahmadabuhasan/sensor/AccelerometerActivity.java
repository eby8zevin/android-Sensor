package com.ahmadabuhasan.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.hardware.SensorEvent;
import android.hardware.Sensor;

import java.util.EventListener;
import java.util.List;

/**
 * Created by Ahmad Abu Hasan on 4/10/2020.
 */

public class AccelerometerActivity extends AppCompatActivity {

    TextView textView1 = null;
    SensorManager sm = null;
    List list;

    SensorEventListener sel = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] values = sensorEvent.values;
            if (sensorEvent.values[1] < 7) {
                textView1.setText("x: " + values[0] + " m/s²\ny: " + values[1] + " m/s²\nz: " + values[2] + " m/s²");
                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
            } else {
                textView1.setText("x: " + values[0] + " m/s²\ny: " + values[1] + " m/s²\nz: " + values[2] + " m/s²");
                getWindow().getDecorView().setBackgroundColor(Color.RED);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        setTitle("TYPE_ACCELEROMETER");

        textView1 = findViewById(R.id.textView1);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        list = sm.getSensorList(Sensor.TYPE_ACCELEROMETER); //Type Sensor
        if (list.size() > 0) {
            sm.registerListener(sel, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(getBaseContext(), "Sorry, sensor not available for this device.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStop() {
        if (list.size() > 0) {
            sm.unregisterListener(sel);
        }
        super.onStop();
    }
}