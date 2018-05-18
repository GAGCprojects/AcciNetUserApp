package com.ganesh.abiaccinet;

import android.*;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import com.google.android.gms.location.LocationServices;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OfflineSMSActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText offlandmark,offdescription;
    private TextView offlocation,dispprobcat;
    private String categ;
    private String[] AllNHNames={"NH47","NH44"};
    private String[] AllNh={"13.179679 80.194249,13.370136 80.138498,13.905222 79.907214,14.162796 79.862528,14.395339 79.945107,15.010605 80.001723,15.577371 80.058703","13.061097 80.156927,13.050739 80.088873"};
    private String[] NHNumbers={"+919962623557","+919962623557"};
    private TextView offnh,t10;
    private int phIndex=0;
    private ImageView imv;
    private RatingBar offrisk;
    private Button reg;
    private String Date,Status,Time;
    private static final int REQUEST_LOCATION = 1;
    private static final int SEND_SMS=1;
    LocationManager locationManager;
    private static final int PERMISSION_REQUEST_CODE = 1;
    String lattitude="50",longitude="50";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_sms);
        categ=getIntent().getExtras().getString("Category");
        offlandmark=(EditText)findViewById(R.id.landmark);
        offlocation=(TextView) findViewById(R.id.loc);
        dispprobcat=(TextView) findViewById(R.id.dispprobcat);
        offdescription=(EditText)findViewById(R.id.desciption);
        offnh=(TextView)findViewById(R.id.nh);
        imv=(ImageView) findViewById(R.id.loca);
        t10=(TextView) findViewById(R.id.textView10);
        reg=(Button)findViewById(R.id.regcom);
        offrisk=(RatingBar)findViewById(R.id.ratingBar);
        dispprobcat.setText(categ);
        Calendar calendar = Calendar.getInstance();
        Time = calendar.getTime().toString();
        String time[]= Time.split(" ");
        Time = time[3];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date  = simpleDateFormat.format(calendar.getTime());
       ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SEND_SMS}, 1);
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        t10.setOnClickListener(this);
        imv.setOnClickListener(this);
        reg.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if(view==imv){
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                buildAlertMessageNoGps();

            } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                getLocation();
            }
            getNhNo();
            offnh.setText(AllNHNames[phIndex]);
        }
        if(view==t10){
            String s=offlocation.getText().toString().trim();
            String[] sep=s.split(" ");
            lattitude = sep[0];
            longitude= sep[1];
              getNhNo();
               offnh.setText(AllNHNames[phIndex]);
        }
        if(view==reg){
            String Landmark,Description,Risklevel,Nationalhighways=AllNHNames[phIndex],Location,Email="ganeshsuriya24@gmail.com";
            String[] re={"Very Low","Low","Medium","High","Very High"};
            Landmark=offlandmark.getText().toString().trim();
            Description=offdescription.getText().toString().trim();
            double d=offrisk.getRating();
            int red=(int)d;
            Risklevel= re[red-1];
            String phNo=NHNumbers[phIndex];
            Location=lattitude+" "+longitude;
            String message="ABI,"+categ+","+Landmark+","+Description+","+Risklevel+","+Nationalhighways+","+Location+","+Email+","+Status+","+Date+","+Time;
            SmsManager smsManager=SmsManager.getDefault();
            PendingIntent sentPI;
            String SENT = "SMS_SENT";
            sentPI = PendingIntent.getBroadcast(this, 0,new Intent(SENT), 0);
            smsManager.sendTextMessage(phNo,null,message,sentPI,null);
            Toast.makeText(getApplicationContext(),"SMS Sent",Toast.LENGTH_LONG).show();
        }

    }
    public void getNhNo(){
        double lat = Double.parseDouble(lattitude);
        double lon = Double.parseDouble(longitude);
        String[] spli = AllNh[0].split(",");
        String[] firmin = spli[0].split(" ");
        double minlat = Double.parseDouble(firmin[0]);
        double minlon = Double.parseDouble(firmin[1]);
        int minindex=0;
        for(int i=0;i<2;i++){
            String[] rd=AllNh[i].split(",");
            for(int j=0;j<rd.length;j++){
                String[] tf=rd[j].split(" ");
                double crrlat = Double.parseDouble(tf[0]);
                double crrlon = Double.parseDouble((tf[1]));
                if(Math.abs(lat-minlat)>Math.abs(lat-crrlat)||Math.abs(lon-minlon)>Math.abs(lon-crrlon)){
                        minlat = crrlat;
                        minlon = crrlon;
                        minindex=i;
                }
            }
        }
        phIndex=minindex;
        lattitude=String.valueOf(minlat);
        longitude=String.valueOf(minlon);

    }
    private void getLocation() {
        if ((ActivityCompat.checkSelfPermission(OfflineSMSActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) && (ActivityCompat.checkSelfPermission
                (OfflineSMSActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(OfflineSMSActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location location2 = locationManager.getLastKnownLocation(LocationManager. PASSIVE_PROVIDER);
            if (location != null) {
                double latti = location.getLatitude();
                double longi = location.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);
                offlocation.setText(lattitude +" "+longitude,TextView.BufferType.EDITABLE);
            }
            else  if (location1 != null) {
                double latti = location1.getLatitude();
                double longi = location1.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);
                offlocation.setText(lattitude +" "+longitude,TextView.BufferType.EDITABLE);
            }
            else  if (location2 != null) {
                double latti = location2.getLatitude();
                double longi = location2.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);
                offlocation.setText(lattitude +" "+longitude,TextView.BufferType.EDITABLE);
            }
            else{
                Toast.makeText(this,"Unable to Trace your location", Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected void buildAlertMessageNoGps() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
}
