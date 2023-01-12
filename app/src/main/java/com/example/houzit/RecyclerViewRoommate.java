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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class RecyclerViewRoommate extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseRecyclerOptions<House> options;
    FirebaseRecyclerAdapter<House, MyViewHolder1> adapter;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_roommate);

        databaseReference = FirebaseDatabase.getInstance().getReference("Roommate");
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        String college = getIntent().getStringExtra("college");
        LoadData(college);
    }

    private void LoadData(String college) {
        Query query = databaseReference.orderByChild("College").startAt(college).endAt(college+"\uf8ff");

        options = new FirebaseRecyclerOptions.Builder<House>().setQuery(query, House.class).build();
        adapter = new FirebaseRecyclerAdapter<House, MyViewHolder1>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder1 holder, int position, @NonNull House model) {

                holder.userString.setText(model.getHouseType()+" for sharing in "+model.getArea());
                holder.rentVal.setText(model.getRentAmt());
                holder.depositVal.setText(model.getDepositAmt());
                Picasso.get().load(model.getImageUrl()).into(holder.imageView);
                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(RecyclerViewRoommate.this, RoommateDetails.class);
                        intent.putExtra("HouseId", getRef(position).getKey());
                        startActivity(intent);
                    }
                });


            }

            @NonNull
            @Override
            public MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_item, parent, false);
                return new MyViewHolder1(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}