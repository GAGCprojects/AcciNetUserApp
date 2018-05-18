package com.ganesh.abiaccinet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Mycomplaints extends AppCompatActivity {

    DatabaseReference db;
    user register;
    DataClass dataClass;
    ArrayList<DataClass> list;
    AdapterClass mycomplaints;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycomplaints);
        listView = (ListView)findViewById(R.id.listItem);
        list = new ArrayList<DataClass>();
        db = FirebaseDatabase.getInstance().getReference().child("Complaints");
        register = new user();
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    if(dataSnapshot1.child("email").getValue().toString().equals(register.email)){
                        dataClass = new DataClass(dataSnapshot1.child("landmark").getValue().toString(),dataSnapshot1.child("nationalhighways").getValue().toString(),dataSnapshot1.child("status").getValue().toString(),dataSnapshot1.child("date").getValue().toString(),dataSnapshot1.child("time").getValue().toString());
                        list.add(dataClass);
                    }
                }
               mycomplaints = new AdapterClass(Mycomplaints.this,list);
               listView.setAdapter(mycomplaints);
               mycomplaints.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    @Override
    public void onBackPressed() {
        //this is only needed if you have specific things
        //that you want to do when the user presses the back button.
        /* your specific things...*/
        super.onBackPressed();
    }
}
