package com.ganesh.abiaccinet;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class user_profile_detail extends AppCompatActivity {

    DatabaseReference  db;
    TextView username,aadharnum,phnum,email;
    user register;
    ProgressDialog progressDialog;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_detail);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait.....");
        username = (TextView)findViewById(R.id.pfname);
        aadharnum = (TextView)findViewById(R.id.aadharnum);
        phnum = (TextView)findViewById(R.id.phnum);
        email = (TextView)findViewById(R.id.email);
        db = FirebaseDatabase.getInstance().getReference().child("users");
        register = new user();
        progressDialog.show();


        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren()){

                        if(data.child("email").getValue().toString().equals(register.email)){
                           username.setText(data.child("name").getValue().toString());
                            aadharnum.setText(data.child("aadhar").getValue().toString());
                            phnum.setText(data.child("phno").getValue().toString());
                            email.setText(data.child("email").getValue().toString());
                            progressDialog.dismiss();
                            break;
                        }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
