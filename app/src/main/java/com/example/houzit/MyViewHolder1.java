package com.example.houzit;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder1 extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView userString;
    TextView rentVal;
    TextView depositVal;
    View v;
    public MyViewHolder1(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageview);
        userString = itemView.findViewById(R.id.userString);
        rentVal = itemView.findViewById(R.id.rentVal);
        depositVal = itemView.findViewById(R.id.depositVal);
        v = itemView;
    }
}
