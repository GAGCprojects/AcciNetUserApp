package com.ganesh.abiaccinet;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.Manifest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.MapView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class AccidentComplain extends AppCompatActivity implements View.OnClickListener {
    private TextView nh,probcat;
   // private ImageView im;
    private Button regcomp;
    private EditText landmark1,description1;
    private RatingBar riskLevel1;
    private static final int REQUEST_LOCATION = 1;
    private DatabaseReference db;
    private FirebaseAuth firebaseAuth;
    LocationManager locationManager;
    private String categ;
    private String Status="Pending";
    private String Date="";
    private String Time="";
    FirebaseUser user;
    String lattitude="50",longitude="50";
    TextView loc;
    String lat[] = {
            "21.186992",
            "21.186711",
            "21.186741",
            "21.188261",
            "21.188180",
            "21.188339",
            "21.186392",
            "21.186211",
            "21.186141",
            "21.188761",
            "21.188280",
            "21.180339",
            "21.086992",
            "21.186711",
            "21.286741",
            "21.388261",
            "21.488180",
            "21.588339",
            "21.686392",
            "21.786211",
            "21.886141",
            "21.988761",
            "21.088280",
            "21.110339"

    };
    String lon[] = {
            "79.025505","79.026063" ,"79.065758" ,"79.079019","79.085542","79.105712",
            "79.025305","79.026463" ,"79.065558" ,"79.079619","79.085742","79.105812",

            "79.015505","79.026063" ,"79.035758" ,"79.049019","79.055542","79.165712",
            "79.075305","79.086463" ,"79.095558" ,"79.179619","79.185742","79.305812"
    };
    RadioGroup area,accType,vehicle,roadType,construction;
    String areaT,accTypeT,vehicleT,roadTypeT,constructionT;
    String Landmark,Description,Risklevel,Nationalhighways,Location,Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accident_complain);
        categ=getIntent().getExtras().getString("Category");
        area = (RadioGroup)findViewById(R.id.area);
        accType = (RadioGroup)findViewById(R.id.accType);
        vehicle = (RadioGroup)findViewById(R.id.vehicle);
        roadType = (RadioGroup)findViewById(R.id.road);
        construction = (RadioGroup)findViewById(R.id.cnsType);
        landmark1=(EditText)findViewById(R.id.landmark);
        probcat=(TextView)findViewById(R.id.dispprobcat);
        description1=(EditText)findViewById(R.id.desciption);
        loc = (TextView)findViewById(R.id.loc);
        nh=(TextView)findViewById(R.id.nh);
      //  t = (TextView)findViewById(R.id.loc);
       // riskLevel1=(RatingBar)findViewById(R.id.ratingBar);
        firebaseAuth=FirebaseAuth.getInstance();
       // probcat.setText(categ);
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        Calendar calendar = Calendar.getInstance();
        Time = calendar.getTime().toString();
        String time[]=Time.split(" ");
        Time = time[3];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date  = simpleDateFormat.format(calendar.getTime());
        FirebaseUser user=firebaseAuth.getCurrentUser();
        db= FirebaseDatabase.getInstance().getReference("Complaints");
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        regcomp=(Button)findViewById(R.id.regcom);
     //   im = (ImageView) findViewById(R.id.loca);
       regcomp.setOnClickListener(this);
        landmark1.setOnClickListener(this);

        area.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
             if(i==1){
                 areaT = "urban";
             }else {
                 areaT = "rural";
             }
            }
        });

        accType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
             if(i==1){
                 accTypeT  = "fatal";
             }else if(i==2){
                 accTypeT = "unhospitalized";
             }else {
                 accTypeT = "hospitalized";
             }
            }
        });

        vehicle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
               if(i==1){
                   vehicleT = "2 wheeler";
               }else {
                   vehicleT = "4 wheeler";
               }
            }
        });

        roadType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
     if(i==1){
         roadTypeT = "NH";
     }else {
         roadTypeT = "SH";
     }
            }
        });

        construction.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
          if(i==1){
             constructionT = "Yes";
          }else {
              constructionT = "No";
          }
            }
        });
       // t.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view==landmark1) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                buildAlertMessageNoGps();

            } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                getLocation();
                String data = getLocationinfo(lattitude,longitude);
                loc.setText(data);
            }
        }

        if(view==regcomp){

            String[] re={"Very Low","Low","Medium","High","Very High"};
            Landmark=landmark1.getText().toString().trim();
            Description=description1.getText().toString().trim();
            //double d=riskLevel1.getRating();
           // int red=(int)d;
           // Risklevel= re[red-1];
            Nationalhighways="NH4";
            Location=lattitude+" "+longitude;
             user=firebaseAuth.getCurrentUser();
            Email=user.getEmail();
            String id=db.push().getKey();
            Complaintregister eri=new Complaintregister(id,categ,Landmark,Description,Nationalhighways,Location,Email,Status,Date,Time,areaT,accTypeT,vehicleT,roadTypeT,constructionT);
            db.child(id).setValue(eri);
           // Toast.makeText(this,"Complaint registered Successfully",Toast.LENGTH_LONG).show();

            Pdf pdf =new Pdf(AccidentComplain.this,id,categ,Landmark,Description,Nationalhighways,Location,Email,Status,Date,Time,areaT,accTypeT,vehicleT,roadTypeT,constructionT);
            pdf.createPdf();
            //duplicate();
            alertMe(lattitude,longitude);

        }
    }

    private void alertMe(String lat,String lng) {

        Geocoder geocoder = new Geocoder(AccidentComplain.this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(Double.parseDouble(lat), Double.parseDouble(lng), 1);
            Address obj = addresses.get(0);
            String add = obj.getAddressLine(0);
            showMessage(add);
           Log.e("DATA",add);
            Toast.makeText(getApplicationContext(),add,Toast.LENGTH_LONG).show();
        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void showMessage(String add) {
        AlertDialog.Builder builder =new  AlertDialog.Builder(AccidentComplain.this);
        View view = getLayoutInflater().inflate(R.layout.message,null);
        TextView content = view.findViewById(R.id.content);
        Button button = view.findViewById(R.id.ok);
        content.setText("Complaint registered at"+add+"\n"+Date+" "+Time);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCanceledOnTouchOutside(false);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                Intent k=new Intent(AccidentComplain.this, map.class);
                k.putExtra("Location",lattitude);
                k.putExtra("Location2",longitude);
                startActivity(k);
            }
        });
    }

    public String getLocationinfo(String lat,String lng){
        String add="";
        Geocoder geocoder = new Geocoder(AccidentComplain.this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(Double.parseDouble(lat), Double.parseDouble(lng), 1);
            Address obj = addresses.get(0);
            add = obj.getAddressLine(0);
        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return add;
    }

    private void duplicate() {

        for(int i=0;i<100;i++){
            String id=db.push().getKey();
            Email="abcdef@gmail.com";
            categ="Accident";
            Landmark = "near nagpur";
            Description = "4 people injured";
            Nationalhighways = "NH4";
            Random random = new Random();
            int r = Math.abs(random.nextInt())%20;
            Location = lat[r]+" "+lon[r];
            Status = "pending";
            areaT = "urban";
            accTypeT = "hospitalized";
            vehicleT = "4 wheeler";
            roadTypeT = "SH";
            constructionT = "NO";
            Complaintregister eri=new Complaintregister(id,categ,Landmark,Description,Nationalhighways,Location,Email,Status,Date,Time,areaT,accTypeT,vehicleT,roadTypeT,constructionT);
            db.child(id).setValue(eri);
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
               // t.setText("Lat="+lattitude +" Long="+longitude);

            }
            else  if (location1 != null) {
                double latti = location1.getLatitude();
                double longi = location1.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);
               // t.setText("Lat="+lattitude +" Long="+longitude);
            }
            else  if (location2 != null) {
                double latti = location2.getLatitude();
                double longi = location2.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);
               // t.setText( "Lat=" + lattitude
                    //    + " Long="+longitude);
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

