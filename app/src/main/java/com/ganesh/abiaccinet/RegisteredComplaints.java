package com.ganesh.abiaccinet;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RegisteredComplaints extends AppCompatActivity {
   /*private ListView l;
   ArrayList<RegisteredComplaints> list;
   ArrayAdapter<RegisteredComplaints> adapter;
   private DatabaseReference db;
   private FirebaseAuth firebaseAuth;
   private FirebaseDatabase database;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_complaints);

        //l=(ListView) findViewById(R.id.listcomplaints);
      /*  firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        database=FirebaseDatabase.getInstance();
        db=database.getReference("Complaints");
        list=new ArrayList<>();
        adapter=new ArrayAdapter<RegisteredComplaints>(this,R.layout.activity_registered_complaints,R.id.listcomplaints);
       db.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               for(DataSnapshot ds:dataSnapshot.getChildren()){

               }
               l.setAdapter(adapter);
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       })
         db = FirebaseDatabase.getInstance().getReference("Complaints");
        FirebaseUser user=firebaseAuth.getCurrentUser();
        String email=user.getEmail();*/

    }
}
