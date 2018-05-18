package com.ganesh.abiaccinet;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RegisteredComplaints extends AppCompatActivity {
    private ListView lv;
    String[] items={"Name","Aadhar","Phone Number"};
   private DatabaseReference db;
   private FirebaseAuth firebaseAuth;
   private FirebaseDatabase database;
   public ArrayList<String> list = new ArrayList<>();
   ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_complaints);
        lv=(ListView)findViewById(R.id.listview);

        db= FirebaseDatabase.getInstance().getReference("users");
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, list);
        lv.setAdapter(adapter);
        /*db.addChildEventListener(new ChildEventListener(){
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot,String s){

                    list.add(dataSnapshot.getValue(String.class));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }
}
