package com.example.houzit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
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

public class HouseDetails extends AppCompatActivity {

//    ImageView img;
//    TextView txt1, txt2, txt3;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_house_details);
//
//        img=findViewById(R.id.userimage);
//        txt1=findViewById(R.id.userstringval);
//        txt2=findViewById(R.id.userrentval);
//        txt3=findViewById(R.id.userdepositval);
//
//        Intent intent = getIntent();
//        img.setImageResource(intent.getIntExtra("image", 0));
//        txt1.setText(intent.getStringExtra("userstring"));
//        txt2.setText(intent.getStringExtra("rentval"));
//        txt3.setText(intent.getStringExtra("depositval"));
//    }

    ImageView imageView;
    TextView userString, rentVal, depositVal;
    TextView areasqft, furnishing, vegNonveg, parking, dateAvl, facing, security, water, description, ownerName, ownerPhone, ownerEmail;
    DatabaseReference databaseReference;
    CardView ownerView;
    Button contactOwnerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_details);

        imageView=findViewById(R.id.userimage);
        userString=findViewById(R.id.userstringval);
        rentVal=findViewById(R.id.userrentval);
        depositVal=findViewById(R.id.userdepositval);
        areasqft=findViewById(R.id.areasqft);
        furnishing=findViewById(R.id.furnishing);
        vegNonveg=findViewById(R.id.vegNonveg);
        parking=findViewById(R.id.parking);
        dateAvl=findViewById(R.id.dateAvl);
        facing=findViewById(R.id.facing);
        security=findViewById(R.id.security);
        water=findViewById(R.id.water);
        description=findViewById(R.id.description);
        ownerEmail=findViewById(R.id.ownerEmail);
        ownerPhone=findViewById(R.id.ownerPhone);
        ownerName=findViewById(R.id.ownerName);
        ownerView=findViewById(R.id.ownerView);
        contactOwnerBtn=findViewById(R.id.contactOwnerBtn);

        databaseReference = FirebaseDatabase.getInstance().getReference("House");

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
                    userString.setText(HouseType+ " for rent in "+Area);
                    areasqft.setText(snapshot.child("Areasqft").getValue().toString()+" sqft");
                    furnishing.setText(snapshot.child("Furnishing").getValue().toString()+" furnished");
                    vegNonveg.setText(snapshot.child("VegNonveg").getValue().toString()+" allowed");
                    parking.setText(snapshot.child("Parking").getValue().toString()+" parking");
                    facing.setText(snapshot.child("Facing").getValue().toString()+" facing");
                    water.setText(snapshot.child("Water").getValue().toString());
                    security.setText(snapshot.child("Security").getValue().toString()+" gated security");
                    dateAvl.setText("Available from "+snapshot.child("DateAvl").getValue().toString());
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