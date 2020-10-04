package com.ahmadabuhasan.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Ahmad Abu Hasan on 4/10/2020.
 */

public class RotationActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager motionSensorManager;
    private Sensor motionSensor;
    private TextView pitchTextView12;
    private TextView rollTextView12;

    private static final int SENSOR_DELAY = 500 * 1000;
    private static final int FROM_RADS_TO_DEGS = -57;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotation);
        setTitle("TYPE_ROTATION_VECTOR");

        pitchTextView12 = findViewById(R.id.pitchTextView12);
        rollTextView12 = findViewById(R.id.rollTextView12);

        try {
            motionSensorManager = (SensorManager) getSystemService(Activity.SENSOR_SERVICE);
            motionSensor = motionSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR); //Type Sensor
            motionSensorManager.registerListener(this, motionSensor, SENSOR_DELAY);

        } catch (Exception e) {
            pitchTextView12.setText(R.string.no_sensor);
            rollTextView12.setText(R.string.no_sensor);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor == motionSensor) {
            update(sensorEvent.values);
        }
    }

    private void update(float[] vectors) {

//Compute the rotation matrix//

        float[] rotationMatrix = new float[9];
        SensorManager.getRotationMatrixFromVector(rotationMatrix, vectors);
        int worldAxisX = SensorManager.AXIS_X;
        int worldAxisZ = SensorManager.AXIS_Z;

//Remap the matrix based on the Activity’s current orientation//

        float[] adjustedRotationMatrix = new float[9];
        SensorManager.remapCoordinateSystem(rotationMatrix, worldAxisX, worldAxisZ, adjustedRotationMatrix);

//Compute the device's orientation//

        float[] orientation = new float[3];

//Supply the array of float values to the getOrientation() method//

        SensorManager.getOrientation(adjustedRotationMatrix, orientation);
        float pitch = orientation[1] * FROM_RADS_TO_DEGS;
        float roll = orientation[2] * FROM_RADS_TO_DEGS;

//Update the TextViews with the pitch and roll values//

        pitchTextView12.setText(getResources().getString(
                R.string.pitch_sensor, pitch));
        rollTextView12.setText(getResources().getString(
                R.string.roll_sensor, roll));
    }
}