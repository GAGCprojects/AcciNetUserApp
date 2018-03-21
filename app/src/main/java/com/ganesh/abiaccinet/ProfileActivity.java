package com.ganesh.abiaccinet;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{
private FirebaseAuth firebaseAuth;
//private TextView registered;
private Button buttonLogout;
private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
     //   registered=(TextView) findViewById(R.id.registered);
        FirebaseUser user=firebaseAuth.getCurrentUser();
       // textViewUserEmail=(TextView)findViewById(R.id.sample);
        //textViewUserEmail.setText("Welcome "+user.getEmail());
        floatingActionButton = (FloatingActionButton) findViewById(R.id.add);
        buttonLogout=(Button)findViewById(R.id.logout);
        floatingActionButton.setOnClickListener(this);
        buttonLogout.setOnClickListener(this);
       // registered.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
         if(view==buttonLogout){
             firebaseAuth.signOut();
             finish();
             startActivity(new Intent(this,LoginActivity.class));
         }
         /*if(view==registered){
             startActivity(new Intent(this,RegisteredComplaints.class));
         }*/
         if(view ==floatingActionButton){
             startActivity(new Intent(this,AccidentComplain.class));
         }
    }
}
