package com.ganesh.abiaccinet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity{
    EditText name,aadhar,email,phno,pass1,pass2;
    Button sub;
    String na,aa,em,ph,pa1,pa2;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db= FirebaseDatabase.getInstance().getReference("message");
        name=(EditText) findViewById(R.id.name);
        aadhar=(EditText) findViewById(R.id.aadhar);
        email=(EditText) findViewById(R.id.email);
        phno=(EditText) findViewById(R.id.phno);
        pass1=(EditText) findViewById(R.id.pass1);
        pass2=(EditText) findViewById(R.id.pass2);
        sub= findViewById(R.id.sub);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                na=name.getText().toString().trim();
                aa=aadhar.getText().toString().trim();
                em=email.getText().toString().trim();
                ph=phno.getText().toString().trim();
                pa1=pass1.getText().toString().trim();
                pa2=pass2.getText().toString().trim();
                additem();
            }
        });
       // Toast.makeText(this,"Account created successfully!",Toast.LENGTH_LONG).show();
    }
    private void additem()
    {
       // Register abi=new Register(na,aa,em,ph,pa1,pa2,uid);
        String uid=db.push().getKey();
        Register abi=new Register(na,aa,em,ph,pa1,pa2,uid);
        db.child(uid).setValue(abi);
        Toast.makeText(this,"Account created Successfuly",Toast.LENGTH_LONG).show();

    }
}

