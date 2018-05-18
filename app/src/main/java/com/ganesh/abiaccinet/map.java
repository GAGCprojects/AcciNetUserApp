package com.ganesh.abiaccinet;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class map extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private double lat,lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        String la=getIntent().getExtras().getString("Location");
        String lo=getIntent().getExtras().getString("Location2");
         lat=Double.parseDouble(la);
         lon=Double.parseDouble(lo);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").snippet("Population: 4,627,300")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.acc)));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

