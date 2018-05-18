package com.ganesh.abiaccinet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.ValueEventListener;

import java.util.List;



public class Custum extends ArrayAdapter<Register> {
    Context context;
    public List<String>reg;
    public Custum(Context context, List<Register> reg){
        super(context,R.layout.custom,reg);
        this.context=context;
      //  this.reg=reg;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom,parent,false);
        TextView na1=(TextView)view.findViewById(R.id.name1);
        TextView ph1=(TextView)view.findViewById(R.id.phno1);
        TextView aa1=(TextView)view.findViewById(R.id.aadh1);
        //na1.setText(reg.get(position).getName());
        //ph1.setText(reg.get(position).getPhno());
        //aa1.setText(reg.get(position).getAadhar());
        return view;
    }
}
