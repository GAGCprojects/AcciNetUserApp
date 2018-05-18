package com.ganesh.abiaccinet;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class user_profile_activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    private Button buttonLogout;
    private FirebaseAuth firebaseAuth;
    private ImageView acci,path,medcut,other;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference("complaints");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firebaseAuth.getCurrentUser()==null){
                    finish();
                    Intent i=new Intent(user_profile_activity.this,OffProfileActivity.class);
                    startActivity(i);
                }
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        acci=(ImageView)findViewById(R.id.accidentcomplaint);
        path=(ImageView)findViewById(R.id.pathholecomplaint);
        medcut=(ImageView)findViewById(R.id.unauthorizedmediancut);
        other=(ImageView)findViewById(R.id.others);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        FirebaseUser user=firebaseAuth.getCurrentUser();

        // floatingActionButton = (FloatingActionButton) findViewById(R.id.add);
        buttonLogout=(Button)findViewById(R.id.logout);
        acci.setOnClickListener(this);
        path.setOnClickListener(this);
        medcut.setOnClickListener(this);
        other.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_profile_activity, menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(item.getItemId()==R.id.logout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,LoginActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.profile){
           startActivity(new Intent(user_profile_activity.this,user_profile_detail.class));
        }

        if(id == R.id.complaints){
            startActivity(new Intent(user_profile_activity.this,Mycomplaints.class));
        }
        if(id == R.id.abouts){
            startActivity(new Intent(user_profile_activity.this,NavigatingRoute.class));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        if(view == buttonLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        if(view == acci){
            Intent i=new Intent(user_profile_activity.this,AccidentComplain.class);
            i.putExtra("Category","Accident");
            startActivity(i);
        }
        if(view == path){
            Intent i=new Intent(user_profile_activity.this,Pathole.class);
            i.putExtra("Category","Pathhole");
            startActivity(i);
        }
        if(view == medcut){
            Intent i=new Intent(user_profile_activity.this,AccComplaintOld.class);
            i.putExtra("Category","Unauthorizedmediancut");
            startActivity(i);
        }
        if(view == other){
            Intent i=new Intent(user_profile_activity.this,AccComplaintOld.class);
            i.putExtra("Category","Others");
            startActivity(i);
        }
    }
}
