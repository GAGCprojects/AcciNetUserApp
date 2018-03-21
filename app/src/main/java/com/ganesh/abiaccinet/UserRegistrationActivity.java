package com.ganesh.abiaccinet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserRegistrationActivity extends AppCompatActivity implements View.OnClickListener{
  private Button buttonRegister;
  private TextView signin;
  private EditText email,password;
  private ProgressDialog prog;
  private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        firebaseAuth=FirebaseAuth.getInstance();
        prog=new ProgressDialog(this);
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }
        buttonRegister=(Button)findViewById(R.id.create);
        email=(EditText)findViewById(R.id.email);
        signin=(TextView)findViewById(R.id.signin);
        password=(EditText)findViewById(R.id.password);
        buttonRegister.setOnClickListener(this);
        signin.setOnClickListener(this);
    }
    private void registerUser(){
        String ema=email.getText().toString().trim();
        String pass=password.getText().toString().trim();
        if(TextUtils.isEmpty(ema)||TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please enter email/password",Toast.LENGTH_SHORT).show();
            return;
        }
        prog.setMessage("Registering User....");
        prog.show();
        firebaseAuth.createUserWithEmailAndPassword(ema,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            Toast.makeText(UserRegistrationActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                            Intent in=new Intent(UserRegistrationActivity.this,MainActivity.class);
                            startActivity(in);
                        }
                        else{
                            Toast.makeText(UserRegistrationActivity.this,"Failed!! Try Again",Toast.LENGTH_SHORT).show();
                        }
                        prog.dismiss();
                    }
                });
    }
    @Override
    public void onClick(View view) {
        if(view == buttonRegister){
            registerUser();
        }
        if(view==signin){
            startActivity(new Intent(this,LoginActivity.class));
        }
    }
}
