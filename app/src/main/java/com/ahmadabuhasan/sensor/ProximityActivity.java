package com.ahmadabuhasan.sensor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.TextView;

/**
 * Created by Ahmad Abu Hasan on 4/10/2020.
 */

public class ProximityActivity extends AppCompatActivity implements SensorEventListener {

    private TextView textView10;
    private SensorManager sm;
    private Sensor mProx;
    private Boolean SA;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);
        setTitle("TYPE_PROXIMITY");

        textView10 = findViewById(R.id.textView10);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        if (sm.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null) {
            mProx = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY); //Type Sensor
            SA = true;
        } else {
            textView10.setText("Sorry, sensor not available for this device.");
            SA = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SA) {
            sm.registerListener(this, mProx, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (SA)
            sm.unregisterListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        textView10.setText(sensorEvent.values[0] + " cm");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (sensorEvent.values[0] > 5) {
                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                getWindow().getDecorView().setBackgroundColor(Color.RED);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}