package com.example.kirri.tp4;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.w3c.dom.Text;
import android.widget.MediaController;

import static android.content.Context.SENSOR_SERVICE;


public class FragmentAccelerometre extends android.app.Fragment implements SensorEventListener {

    private TextView x;
    private TextView y;
    private TextView z;
    private Activity activity ;


    public SensorManager sensorManager;
    public Sensor sensorAccelerometre;

    public MediaPlayer media = new MediaPlayer();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            media = MediaPlayer.create(getContext(),R.raw.lightsabre);
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accelerometre, container, false);

    }

    public void onActivityCreated(Bundle b){

        super.onActivityCreated(b);
        sensorManager =  (SensorManager)getActivity().getSystemService(SENSOR_SERVICE);
        sensorAccelerometre  = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        x = getActivity().findViewById(R.id.X);
        y = getActivity().findViewById(R.id.Y);
        z = getActivity().findViewById(R.id.Z);

        media.start();


        sensorManager.registerListener(this,sensorAccelerometre,sensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    public void onDetach(){
        super.onDetach();
        sensorManager.unregisterListener(this, sensorAccelerometre);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        float[] values = event.values;
        x.setText("ForceX : "+String.valueOf(values[0]));
        y.setText("ForceY : "+String.valueOf(values[1]));
        z.setText("ForceZ : "+String.valueOf(values[2]));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
