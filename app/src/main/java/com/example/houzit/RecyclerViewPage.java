package com.example.houzit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.load.model.ModelLoader;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class RecyclerViewPage extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseRecyclerOptions<House> options;
    FirebaseRecyclerAdapter<House, MyViewHolder1>adapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_page);

        databaseReference = FirebaseDatabase.getInstance().getReference("House");
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        LoadData();
    }

    private void LoadData() {
        options = new FirebaseRecyclerOptions.Builder<House>().setQuery(databaseReference, House.class).build();
        adapter = new FirebaseRecyclerAdapter<House, MyViewHolder1>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder1 holder, int position, @NonNull House model) {
                holder.userString.setText(model.getHouseType()+" for rent in "+model.getArea());
                holder.rentVal.setText(model.getRentAmt());
                holder.depositVal.setText(model.getDepositAmt());
                Picasso.get().load(model.getImageUrl()).into(holder.imageView);
                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(RecyclerViewPage.this, HouseDetails.class);
                        intent.putExtra("HouseId", getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_item, parent, false);
                return new MyViewHolder1((v));
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}