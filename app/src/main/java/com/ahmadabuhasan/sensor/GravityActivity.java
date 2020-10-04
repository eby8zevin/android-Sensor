package com.ahmadabuhasan.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Ahmad Abu Hasan on 4/10/2020.
 */

public class GravityActivity extends AppCompatActivity {

    private TextView textView3;
    private SensorManager sm;
    private AudioManager aManager;
    List list;

    SensorEventListener sel = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {

            float[] values = sensorEvent.values;
            if (sensorEvent.values[2] < 9.5) {
                textView3.setText("x: " + values[0] + " m/s² \ny: " + values[1] + " m/s² \nz: " + values[2] + " m/s²");
                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                aManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            } else {
                textView3.setText("x: " + values[0] + " m/s² \ny: " + values[1] + " m/s² \nz: " + values[2] + " m/s²");
                getWindow().getDecorView().setBackgroundColor(Color.RED);
                aManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravity);
        setTitle("TYPE_GRAVITY");

        textView3 = findViewById(R.id.textView3);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        aManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        list = sm.getSensorList(Sensor.TYPE_GRAVITY); //Type Sensor
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