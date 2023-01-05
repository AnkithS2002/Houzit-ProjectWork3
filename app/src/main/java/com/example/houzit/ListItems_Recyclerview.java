package com.example.houzit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ListItems_Recyclerview extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items_recyclerview);

        recyclerView = findViewById(R.id.recyclerview);

        String arruserString[]={"1BHK for rent in Basavangudi",
                "1BHK for rent in Hanumanthanagar",
                "1BHK for rent in Srinagar",
                "1BHK for rent in AshokNagar",
                "1BHK for rent in Sreenivasanagar",
                "1BHK for rent in NR colony",
                "1BHK for rent in Gandhi Bazar"};

        String arrrentval[]={"7000","8000","6000","11000","12000","9500","10000"};
        String arrdeposittval[]={"70000","50000","90000","100000","120000","50000","40000"};

        int arrimage[]={R.drawable.h1,R.drawable.h2,R.drawable.h3,R.drawable.h4,R.drawable.h5,R.drawable.h6,R.drawable.h1};

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListAdapter(this, arruserString, arrrentval, arrdeposittval, arrimage));
    }
}