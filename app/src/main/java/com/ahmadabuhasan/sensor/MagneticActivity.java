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
import android.widget.Toast;

/**
 * Created by Ahmad Abu Hasan on 4/10/2020.
 */

public class MagneticActivity extends AppCompatActivity implements SensorEventListener {

    TextView textView7;
    private static SensorManager sm;
    private Sensor mMagn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnetic);
        setTitle("TYPE_MAGNETIC_FIELD");

        textView7 = findViewById(R.id.textView7);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mMagn = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD); //Type Sensor
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mMagn != null) {
            sm.registerListener(this, mMagn, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(this, "Sorry, sensor not available for this device.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float azimuth = Math.round(sensorEvent.values[0]);
        float pitch = Math.round(sensorEvent.values[1]);
        float roll = Math.round(sensorEvent.values[2]);

        double tesla = Math.sqrt((azimuth * azimuth) + (pitch * pitch) + (roll * roll));
        String text = String.format("%.0f", tesla);
        int num = Integer.parseInt(text);

        if (num < 36) {
            textView7.setText(text + " μT");
            getWindow().getDecorView().setBackgroundColor(Color.RED);
        } else {
            textView7.setText(text + " μT");
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}