package com.example.kirri.tp4;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.DataSetObserver;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SensorActivity extends AppCompatActivity   {

    private TextView nom;
    private SensorManager manager ;
    private List<Sensor> sensorList;
    private ListView capteurV;
    private ArrayList<String> listeAttributs;
    public FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        fm = getFragmentManager();

        nom = findViewById(R.id.nom);
        capteurV = findViewById(R.id.attributs);

        String n = getIntent().getStringExtra("nom");
        nom.setText(n);


        manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensorList = manager.getSensorList(Sensor.TYPE_ALL);

        Sensor capteur = getByName(n);

        listeAttributs = new ArrayList<String>();
        listeAttributs.add("Vendor : "+capteur.getVendor());
        listeAttributs.add("Power : "+String.valueOf(capteur.getPower()));
        listeAttributs.add("Version : "+String.valueOf(capteur.getVersion()));
        listeAttributs.add("Resolution : "+String.valueOf(capteur.getResolution()));
        //  listeAttributs.add(String.valueOf(capteur.isWakeUpSensor()));      -> pb d'API


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listeAttributs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        capteurV.setAdapter(adapter);



        if(capteur.getType() == Sensor.TYPE_ACCELEROMETER) {

            Log.d("accele", " accéléromètre trouvé");
            //Fragment fragmentAccelerometre = (Fragment) fm.findFragmentById(R.id.layoutDetails)      ;

            FragmentTransaction ft = fm.beginTransaction();
            FragmentAccelerometre fAcc = new FragmentAccelerometre();

            ft.add(R.id.container, fAcc);


            ft.commit();
        }




    }



    public Sensor getByName(String n) {

        for(Sensor  s : sensorList) {
            if(s.getName().equals(n)){
                return s;
            }
        }
        return null;
    }
}
