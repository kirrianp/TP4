package com.example.kirri.tp4;

import android.content.Context;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kirri on 23/02/2018.
 */

public class SensorAdapter extends BaseAdapter {

    private List<Sensor> listSensor;
    private LayoutInflater inflater;



    public SensorAdapter(Context context,List<Sensor> l) {

        inflater = LayoutInflater.from(context);
        listSensor = l;
    }

    @Override
    public int getCount() {
        return listSensor.size();
    }

    @Override
    public Sensor getItem(int position) {
        return listSensor.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {

        TextView nomView;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {

        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.sensor_layout, null);

            holder.nomView = convertView.findViewById(R.id.textSensor);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();

        }

        holder.nomView.setText(listSensor.get(position).getName());

        return convertView;


    }
}
