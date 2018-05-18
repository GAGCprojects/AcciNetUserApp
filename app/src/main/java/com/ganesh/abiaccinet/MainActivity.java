package com.ganesh.abiaccinet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText name,aadhar,phno;
    Button sub;
    String na,aa,ph;
    FirebaseAuth firebaseAuth;
    private DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        FirebaseUser user=firebaseAuth.getCurrentUser();
        db= FirebaseDatabase.getInstance().getReference("users");
        name=(EditText) findViewById(R.id.name);
        aadhar=(EditText) findViewById(R.id.aadhar);
        phno=(EditText) findViewById(R.id.phno);
        sub= findViewById(R.id.sub);
        sub.setOnClickListener(this);

    }
    private void addUserInfo()
    {
        na=name.getText().toString().trim();
        aa=aadhar.getText().toString().trim();
        ph=phno.getText().toString().trim();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        String email=user.getEmail();
        Register abi=new Register(na,aa,ph,email);
        db.child(user.getUid()).setValue(abi);
        startActivity(new Intent(this,ProfileActivity.class));
    }
    @Override
    public void onClick(View view) {
       if(view==sub){
           addUserInfo();
       }
    }
}

