package com.ganesh.abiaccinet;

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
import android.view.View;
import android.Manifest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.MapView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccidentComplain extends AppCompatActivity implements View.OnClickListener {
    private TextView t,nh;
    private ImageView im;
    private Button regcomp;
    private EditText landmark1,description1;
    private RatingBar riskLevel;
    private static final int REQUEST_LOCATION = 1;
    private DatabaseReference db;
    private FirebaseAuth firebaseAuth;
    LocationManager locationManager;
    String lattitude="",longitude="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accident_complain);
        landmark1=(EditText)findViewById(R.id.landmark);
        description1=(EditText)findViewById(R.id.desciption);
        nh=(TextView)findViewById(R.id.nh);
        t = (TextView)findViewById(R.id.loc);
        riskLevel=(RatingBar)findViewById(R.id.ratingBar);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        FirebaseUser user=firebaseAuth.getCurrentUser();
        db= FirebaseDatabase.getInstance().getReference("Complaints");
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        regcomp=(Button)findViewById(R.id.regcom);
        im = (ImageView) findViewById(R.id.loca);
        regcomp.setOnClickListener(this);
        im.setOnClickListener(this);
        t.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view==im) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                buildAlertMessageNoGps();

            } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                getLocation();
            }
        }
        if(view==t){
            Intent i= new Intent(AccidentComplain.this,Pop.class);
            i.putExtra("Location",lattitude);
            i.putExtra("Location2",longitude);
            startActivity(i);
        }
        if(view==regcomp){
            String Landmark,Description,Risklevel,Nationalhighways,Location,Email;
            String[] re={"Very Low","Low","Medium","High","Very High"};
            Landmark=landmark1.getText().toString().trim();
            Description=description1.getText().toString().trim();
            double d=riskLevel.getRating();
            int red=(int)d;
            Risklevel= re[red-1];
            Nationalhighways="NH4";
            Location=lattitude+" "+longitude;
            FirebaseUser user=firebaseAuth.getCurrentUser();
            Email=user.getEmail();
            String id=db.push().getKey();
            Complaintregister eri=new Complaintregister(id,Landmark,Description,Risklevel,Nationalhighways,Location,Email);
            db.child(id).setValue(eri);
            Toast.makeText(this,"Complaint registered Successfully",Toast.LENGTH_LONG).show();
            Intent k=new Intent(AccidentComplain.this, map.class);
            k.putExtra("Location",lattitude);
            k.putExtra("Location2",longitude);
            startActivity(k);
        }

    }

    private void getLocation() {
        if ((ActivityCompat.checkSelfPermission(AccidentComplain.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) && (ActivityCompat.checkSelfPermission
                (AccidentComplain.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(AccidentComplain.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
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
                t.setText("Lat="+lattitude +" Long="+longitude);
            }
            else  if (location1 != null) {
                double latti = location1.getLatitude();
                double longi = location1.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);
                t.setText("Lat="+lattitude +" Long="+longitude);
            }
            else  if (location2 != null) {
                double latti = location2.getLatitude();
                double longi = location2.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);
                t.setText( "Lat=" + lattitude
                        + " Long="+longitude);
            }
            else{
                Toast.makeText(this,"Unble to Trace your location", Toast.LENGTH_SHORT).show();
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

