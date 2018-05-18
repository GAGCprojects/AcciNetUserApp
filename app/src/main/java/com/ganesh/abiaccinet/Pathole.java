package com.ganesh.abiaccinet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by User on 31-03-2018.
 */

public class Pathole extends AppCompatActivity {

    EditText landamrk,description;
    RadioGroup radioGroup;
    ImageView imageView;
    StorageReference storageReference;
    Button send;
    Uri file;
    String URL,condit;
    DatabaseReference db;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pathole);
        progressDialog= new ProgressDialog(Pathole.this);
        progressDialog.setMessage("Uploading image ....");
        progressDialog.setCanceledOnTouchOutside(false);
        db = FirebaseDatabase.getInstance().getReference().child("pothole");
        imageView = (ImageView)findViewById(R.id.img);
        storageReference = FirebaseStorage.getInstance().getReference();
        landamrk = (EditText) findViewById(R.id.landmark);
        description = (EditText)findViewById(R.id.desciption);
        radioGroup=(RadioGroup)findViewById(R.id.condition1);
        send = (Button)findViewById(R.id.regcom);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                file = Uri.fromFile(getOutputMediaFile());
                intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
                 progressDialog.show();
                startActivityForResult(intent, 100);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==1){
                    condit = "small";
                }else if(i==2){
                    condit = "medium";
                }else {
                    condit = "large";
                }
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,String> hashMap = new HashMap<String, String>();
                hashMap.put("landmark",landamrk.getText().toString());
                hashMap.put("desc",description.getText().toString());
                hashMap.put("url",URL);
                hashMap.put("condition",condit);

                db.push().setValue(hashMap);

            }
        });
    }

    private File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpg");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {

            if (resultCode == RESULT_OK) {
                imageView.setImageURI(file);

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), file);
                    final StorageReference path = storageReference.child("child").child(file.getLastPathSegment());
                    path.putFile(file).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getApplicationContext(),"image added successfullt",Toast.LENGTH_LONG).show();
                            URL = taskSnapshot.getMetadata().getDownloadUrl().toString();
                        }
                    });
                    progressDialog.dismiss();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }






}
