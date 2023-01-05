package com.example.houzit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class HouseDetails extends AppCompatActivity {

    ImageView img;
    TextView txt1, txt2, txt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_details);

        img=findViewById(R.id.userimage);
        txt1=findViewById(R.id.userstringval);
        txt2=findViewById(R.id.userrentval);
        txt3=findViewById(R.id.userdepositval);

        Intent intent = getIntent();
        img.setImageResource(intent.getIntExtra("image", 0));
        txt1.setText(intent.getStringExtra("userstring"));
        txt2.setText(intent.getStringExtra("rentval"));
        txt3.setText(intent.getStringExtra("depositval"));
    }
}