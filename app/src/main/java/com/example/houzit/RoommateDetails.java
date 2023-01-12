package com.example.houzit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class RoommateDetails extends AppCompatActivity {

    ImageView imageView;
    TextView userString, rentVal, depositVal;
    TextView areasqft, furnishing, vegNonveg, parking, gender, facing, security, water, description;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roommate_details);

        imageView=findViewById(R.id.userimage);
        userString=findViewById(R.id.userstringval);
        rentVal=findViewById(R.id.userrentval);
        depositVal=findViewById(R.id.userdepositval);
        areasqft=findViewById(R.id.areasqft);
        furnishing=findViewById(R.id.furnishing);
        vegNonveg=findViewById(R.id.vegNonveg);
        parking=findViewById(R.id.parking);
        gender=findViewById(R.id.gender);
        facing=findViewById(R.id.facing);
        security=findViewById(R.id.security);
        water=findViewById(R.id.water);
        description=findViewById(R.id.description);


        databaseReference = FirebaseDatabase.getInstance().getReference("Roommate");

        String HouseId = getIntent().getStringExtra("HouseId");
        databaseReference.child(HouseId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String RentAmt = snapshot.child("RentAmt").getValue().toString();
                    String DepositAmt = snapshot.child("DepositAmt").getValue().toString();
                    String HouseType = snapshot.child("HouseType").getValue().toString();
                    String Area = snapshot.child("Area").getValue().toString();
                    String ImageUrl = snapshot.child("ImageUrl").getValue().toString();


                    Picasso.get().load(ImageUrl).into(imageView);
                    rentVal.setText(RentAmt);
                    depositVal.setText(DepositAmt);
                    userString.setText(HouseType+ " for sharing in "+Area);
                    areasqft.setText(snapshot.child("Areasqft").getValue().toString()+" sqft");
                    furnishing.setText(snapshot.child("Furnishing").getValue().toString()+" furnished");
                    vegNonveg.setText(snapshot.child("VegNonveg").getValue().toString()+" allowed");
                    parking.setText(snapshot.child("Parking").getValue().toString()+" parking");
                    facing.setText(snapshot.child("Facing").getValue().toString()+" facing");
                    water.setText(snapshot.child("Water").getValue().toString());
                    security.setText(snapshot.child("Security").getValue().toString()+" gated security");
                    gender.setText("For "+snapshot.child("Gender").getValue().toString()+" only");
                    description.setText(snapshot.child("Description").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}