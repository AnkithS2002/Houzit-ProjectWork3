package com.example.houzit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HouseRecyclerView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_recycler_view);
        Intent intent = getIntent();
        String message = intent.getStringExtra(HouseFragment.msg);
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
    }
}