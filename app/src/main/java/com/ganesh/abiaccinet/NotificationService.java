package com.ganesh.abiaccinet;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by User on 30-03-2018.
 */

public class NotificationService extends Service {

DatabaseReference db;
 static boolean  flag = false;

    @Override
    public void onCreate() {
        super.onCreate();

        db = FirebaseDatabase.getInstance().getReference().child("Complaints");
        db.limitToLast(1);
        update();

    }

    private void update() {


            db.limitToLast(1).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    if(flag == false){
                        flag = true;
                    }else{
                        Intent intent = new Intent(NotificationService.this,MessageIntent.class);
                        intent.putExtra("landmark","Accident happened at"+dataSnapshot.child("nationalhighways").getValue().toString());

                        PendingIntent pendingIntent  = PendingIntent.getActivity(NotificationService.this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                        @SuppressWarnings("deprecation") NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
                                .setContentTitle("Alert From Accinet")
                                .setContentText("Accident happened at"+dataSnapshot.child("nationalhighways").getValue().toString())
                                .setSmallIcon(R.drawable.notify)
                                .setContentIntent(pendingIntent);

                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(1,builder.build());

                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
