package com.ganesh.abiaccinet;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

/**
 * Created by User on 16-03-2018.
 */

public class Pop extends Activity {
    TextView te;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);
        String la=getIntent().getExtras().getString("Location");
        String lo=getIntent().getExtras().getString("Location2");
        te=(TextView)findViewById(R.id.lo);
        te.setText(lo+"\n"+la);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.6));
    }

}
