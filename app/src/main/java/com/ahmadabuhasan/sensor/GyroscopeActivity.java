package com.ahmadabuhasan.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Ahmad Abu Hasan on 4/10/2020.
 */

public class GyroscopeActivity extends AppCompatActivity {

    private TextView textView4;
    private SensorManager sm;
    private Sensor mGryo = null;
    List list;

    SensorEventListener sel = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] values = sensorEvent.values;
            if (sensorEvent.values[2] > 0.5f) {
                textView4.setText("x: " + values[0] + " rad/s\ny: " + values[1] + " rad/s\nz: " + values[2] + " rad/s");
                getWindow().getDecorView().setBackgroundColor(Color.RED);
            } else {
                textView4.setText("x: " + values[0] + " rad/s\ny: " + values[1] + " rad/s\nz: " + values[2] + " rad/s");
                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);
        setTitle("TYPE_GYROSCOPE");

        textView4 = findViewById(R.id.textView4);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        list = sm.getSensorList(Sensor.TYPE_GYROSCOPE); //Type Sensor
        if (list.size() > 0) {
            sm.registerListener(sel, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(getBaseContext(), "Sorry, sensor not available for this device.", Toast.LENGTH_LONG).show();
        }
    }

    public void onResume() {
        super.onResume();
        sm.registerListener(sel, mGryo, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        if (list.size() > 0) {
            sm.unregisterListener(sel);
        }
        super.onStop();
    }
}