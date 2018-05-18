package com.ganesh.abiaccinet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MessageIntent extends AppCompatActivity {

    TextView msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_intent);
        msg = (TextView)findViewById(R.id.not);
        String msg1 = getIntent().getExtras().getString("landmark");
        msg.setText(msg1);
    }
}
