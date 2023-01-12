package com.example.houzit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class PGDetails extends AppCompatActivity {

    ImageView imageView;
    TextView userString, rentVal, depositVal;
    TextView gender, laundry, vegNonveg, parking, food, housekeeping, security, sharing, description,ownerName, ownerPhone, ownerEmail;
    DatabaseReference databaseReference;
    CardView ownerView;
    Button contactOwnerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pgdetails);

        imageView=findViewById(R.id.userimage);
        userString=findViewById(R.id.userstringval);
        rentVal=findViewById(R.id.userrentval);
        depositVal=findViewById(R.id.userdepositval);
        gender=findViewById(R.id.gender);
        laundry=findViewById(R.id.laundry);
        vegNonveg=findViewById(R.id.vegNonveg);
        parking=findViewById(R.id.parking);
        food=findViewById(R.id.food);
        housekeeping=findViewById(R.id.housekeeping);
        security=findViewById(R.id.security);
        sharing=findViewById(R.id.sharing);
        description=findViewById(R.id.description);
        ownerEmail=findViewById(R.id.ownerEmail);
        ownerPhone=findViewById(R.id.ownerPhone);
        ownerName=findViewById(R.id.ownerName);
        ownerView=findViewById(R.id.ownerView);
        contactOwnerBtn=findViewById(R.id.contactOwnerBtn);

        databaseReference = FirebaseDatabase.getInstance().getReference("PG");

        String HouseId = getIntent().getStringExtra("PGId");

        databaseReference.child(HouseId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String RentAmt = snapshot.child("RentAmt").getValue().toString();
                    String DepositAmt = snapshot.child("DepositAmt").getValue().toString();
                    String PGname = snapshot.child("PGName").getValue().toString();
                    String Area = snapshot.child("Area").getValue().toString();
                    String ImageUrl = snapshot.child("ImageUrl").getValue().toString();


                    Picasso.get().load(ImageUrl).into(imageView);
                    rentVal.setText(RentAmt);
                    depositVal.setText(DepositAmt);
                    userString.setText(PGname+ " for rent in "+Area);
                    gender.setText(snapshot.child("Gender").getValue().toString()+" only");
                    laundry.setText(snapshot.child("Laundry").getValue().toString()+" present");
                    vegNonveg.setText(snapshot.child("VegNonveg").getValue().toString()+" allowed");
                    parking.setText(snapshot.child("Parking").getValue().toString()+" parking");
                    food.setText(snapshot.child("Food").getValue().toString()+" available");
                    housekeeping.setText(snapshot.child("HouseKeeping").getValue().toString());
                    security.setText(snapshot.child("Security").getValue().toString()+" gated security");
                    sharing.setText("Available "+snapshot.child("Sharing").getValue().toString());
                    description.setText(snapshot.child("Description").getValue().toString());
                    ownerName.setText(snapshot.child("OwnerName").getValue().toString());
                    ownerEmail.setText(snapshot.child("OwnerEmail").getValue().toString());
                    ownerPhone.setText(snapshot.child("OwnerContact").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        contactOwnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ownerView.setVisibility(View.VISIBLE);
            }
        });
    }
}