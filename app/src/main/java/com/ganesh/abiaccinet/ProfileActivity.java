package com.ganesh.abiaccinet;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{
private FirebaseAuth firebaseAuth;
private Button buttonLogout;
private ImageView acci,path,medcut,other;
private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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
    public void onClick(View view) {
         if(view == buttonLogout){
             firebaseAuth.signOut();
             finish();
             startActivity(new Intent(this,LoginActivity.class));
         }
         if(view == acci){
             Intent i=new Intent(ProfileActivity.this,AccidentComplain.class);
             i.putExtra("Category","Accident");
             startActivity(i);
         }
         if(view == path){
             Intent i=new Intent(ProfileActivity.this,AccidentComplain.class);
             i.putExtra("Category","Pathhole");
             startActivity(i);
         }
         if(view == medcut){
             Intent i=new Intent(ProfileActivity.this,AccidentComplain.class);
             i.putExtra("Category","Unauthorizedmediancut");
             startActivity(i);
         }
         if(view == other){
             Intent i=new Intent(ProfileActivity.this,AccidentComplain.class);
             i.putExtra("Category","Others");
             startActivity(i);
         }
       /*  if(view==registered){
             startActivity(new Intent(this,RegisteredComplaints.class));
         }
         if(view ==floatingActionButton){
             startActivity(new Intent(this,AccidentComplain.class));
         }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.logout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,LoginActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
