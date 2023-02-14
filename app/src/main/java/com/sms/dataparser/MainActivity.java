package com.sms.dataparser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button xmlbtn,jsonbtn,dualparse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xmlbtn=findViewById(R.id.xmlbtn);
        jsonbtn=findViewById(R.id.jsonbtn);
        dualparse=findViewById(R.id.dualparse);

        xmlbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ViewActivity.class);
                intent.putExtra("mode",1);
                startActivity(intent);
            }
        });
        jsonbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ViewActivity.class);
                intent.putExtra("mode",2);
                startActivity(intent);
            }
        });
        dualparse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ViewActivity.class);
                intent.putExtra("mode",3);
                startActivity(intent);
            }
        });

    }
}