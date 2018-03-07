package com.example.kirri.tp4;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Sensor> listeSensor;

    public static String NOM_ACTIVITE = "nom" ;
    private ListView listeV;
    private SensorManager sensorManager;
    private SensorAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listeV  =  findViewById(R.id.listCapteurs);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        listeSensor = sensorManager.getSensorList(Sensor.TYPE_ALL);



        adapter = new SensorAdapter( MainActivity.this, listeSensor);

        listeV.setAdapter(adapter);

        listeV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?>adap, View v, int position, long id){

                Sensor sensor = (Sensor) adap.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this,SensorActivity.class);
                intent.putExtra(NOM_ACTIVITE, sensor.getName());

                startActivity(intent);
            }
        });



    }

    public ListView getListeV(){
        return listeV;
    }
}
