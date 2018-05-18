package com.ganesh.abiaccinet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class OffProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView acci,path,medcut,other;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_off_profile);
        acci=(ImageView)findViewById(R.id.accidentcomplaint);
        path=(ImageView)findViewById(R.id.pathholecomplaint);
        medcut=(ImageView)findViewById(R.id.unauthorizedmediancut);
        other=(ImageView)findViewById(R.id.others);
        acci.setOnClickListener(this);
        path.setOnClickListener(this);
        medcut.setOnClickListener(this);
        other.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == acci){
            Intent i=new Intent(OffProfileActivity.this,OfflineSMSActivity.class);
            i.putExtra("Category","Accident Complaint");
            startActivity(i);
        }
        if(view == path){
            Intent i=new Intent(OffProfileActivity.this,OfflineSMSActivity.class);
            i.putExtra("Category","Road Pathhole Complaint");
            startActivity(i);
        }
        if(view == medcut){
            Intent i=new Intent(OffProfileActivity.this,OfflineSMSActivity.class);
            i.putExtra("Category","Unauthorized mediancut Report");
            startActivity(i);
        }
        if(view == other){
            Intent i=new Intent(OffProfileActivity.this,OfflineSMSActivity.class);
            i.putExtra("Category","Other Complaints");
            startActivity(i);
        }
    }
}
