package com.ganesh.abiaccinet;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 30-03-2018.
 */

public class AdapterClass extends ArrayAdapter<DataClass> {

Context context;
ArrayList<DataClass> list;

    public AdapterClass(@NonNull Context context, ArrayList<DataClass> list) {
        super(context,R.layout.structure,list);
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView = View.inflate(context,R.layout.structure,null);
        }
        TextView nh,ld,dt;
        nh = (TextView)convertView.findViewById(R.id.ldValue);
        ld = (TextView)convertView.findViewById(R.id.textView8);
        dt = (TextView)convertView.findViewById(R.id.dateValue);

        DataClass dataClass = list.get(position);
        nh.setText(dataClass.landmark);
        ld.setText(dataClass.nhway);
        dt.setText(dataClass.date+" "+dataClass.time);

        return convertView;
    }




}
