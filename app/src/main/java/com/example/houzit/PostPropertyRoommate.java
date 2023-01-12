package com.example.houzit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class PostPropertyRoommate extends AppCompatActivity {

    private static final int REQUEST_CODE_IMAGE = 101;
    private ImageView imageViewAdd;
    private EditText ownerName;
    private EditText ownerContact;
    private EditText ownerEmail;
    private EditText rentAmount;
    private EditText depositAmount;
    private EditText areasqft;
    private EditText facing;
    private EditText furnishing;
    private EditText water;
    private EditText parking;
    private EditText gender;
    private EditText security;
    private EditText vegNonveg;
    private EditText description;
    private ProgressBar progressBar;
    private TextView textViewProgress;
    private Button postBtn;

    Uri imageUri;
    boolean isImageAdded=false;

    DatabaseReference databaseReference;
    StorageReference storageReference;

    private static final String[] colleges = new String[]{
            "BMSCE", "RVCE", "MSRIT", "PESU main campus", "PESU ECity", "SJCE", "NIE", "DSCE", "CMRIT", "AIT", "UVCE", "BMSIT","New Horizon" , "RVITM", "SJBIT", "RNSIT", "SIT", "CHRIST"
    };
    private static final String[] houseType = new String[]{
            "1RK", "1BHK", "2BHK", "3BHK", "4BHK"
    };
    private static final String[] areaNames = new String[]{
            "Basavanagudi", "Hanumanthanagar", "Gandhi Bazar", "Thyagarajanagar", "NR Colony", "Srinivasanagar", "Srinagar", "Ashok nagar", "Gavipuram",
            "Jnanabharathi BDA", "RR nagar", "Mysore road","Kumbalgodu" , "Pattanagere", "Kengeri", "Nagarabhavi", "Banashankari stage 1", "Banashankari stage 2",
            "Banashankari stage 3", "Chamarajapete", "Vijayanagar", "RPC Layout", "Deepanjali nagar","Attiguppe", "Rajaji nagar", "Ullal", "Nandini layout", "Laggere",
            "Basaveshwara nagar", "Peenya indtl area", "Mathikere", "Koramangala", "Adugodi", "KR market", "Jayanagar", "JP nagar", "Wilson garden","Lalbhag", "Mavalli",
            "FrazerTown", "Kalyan nagar", "Jeevanbhima nagar", "Marathahalli", "Whitefield", "Tin factory", "KR Puram", "Mahadevpura", "Sarjapura", "Varthur", "Bellandur",
            "Begur", "Electronic city", "BTM layout", "HSR layout", "Sadashivanagar", "Malleshwaram", "Shantala nagar", "Shanti nagar", "Katriguppe", "Kumarswamy layout",
            "Bannerughatta road", "Hosur road", "Agara", "Bommansandra","Bommmanahalli", "Hebbagodi", "Aramane nagar", "Sanjay nagar", "Hebbal", "Rammurthynagar", "Indira nagar",
            "RT Nagar", "Vasanth nagar", "Hosakerehalli", "Yelahanka", "Yeswantpura"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_property_roommate);

        AutoCompleteTextView autoCompleteTextView1 = findViewById(R.id.autocompCollege);
        AutoCompleteTextView autoCompleteTextView2 = findViewById(R.id.autocompHouseType);
        AutoCompleteTextView autoCompleteTextView3 = findViewById(R.id.autocompAreaName);

        imageViewAdd = findViewById(R.id.imageViewAdd);
        ownerName = findViewById(R.id.ownerName);
        ownerContact = findViewById(R.id.ownerContact);
        ownerEmail = findViewById(R.id.ownerEmail);
        rentAmount = findViewById(R.id.rentAmount);
        depositAmount = findViewById(R.id.depositAmount);
        areasqft = findViewById(R.id.areasqft);
        facing = findViewById(R.id.facing);
        furnishing = findViewById(R.id.furnishing);
        gender = findViewById(R.id.gender);
        water = findViewById(R.id.water);
        parking = findViewById(R.id.parking);
        vegNonveg = findViewById(R.id.vegNonveg);
        security = findViewById(R.id.security);
        description = findViewById(R.id.description);
        progressBar = findViewById(R.id.progressBar);
        postBtn = findViewById(R.id.postBtn);
        textViewProgress = findViewById(R.id.textViewProgress);

        progressBar.setVisibility(View.INVISIBLE);
        textViewProgress.setVisibility(View.INVISIBLE);

        databaseReference = FirebaseDatabase.getInstance().getReference("Roommate");
        storageReference= FirebaseStorage.getInstance().getReference().child("RoommateDetails");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PostPropertyRoommate.this, android.R.layout.simple_list_item_1, colleges);
        autoCompleteTextView1.setAdapter(adapter);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(PostPropertyRoommate.this, android.R.layout.simple_list_item_1, houseType);
        autoCompleteTextView2.setAdapter(adapter2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(PostPropertyRoommate.this, android.R.layout.simple_list_item_1, areaNames);
        autoCompleteTextView3.setAdapter(adapter3);

        imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String PROPERTYTYPE = "Roommate";
                final String OWNERNAME = ownerName.getText().toString();
                final String OWNERCONTACT = ownerContact.getText().toString();
                final String OWNEREMAIL = ownerEmail.getText().toString();
                final String RENTAMT = rentAmount.getText().toString();
                final String DEPOSITAMT = depositAmount.getText().toString();
                final String AREASQFT = areasqft.getText().toString();
                final String FACING = facing.getText().toString();
                final String FURNISHING = furnishing.getText().toString();
                final String WATER = water.getText().toString();
                final String PARKING = parking.getText().toString();
                final String GENDER = gender.getText().toString();
                final String SECURITY = security.getText().toString();
                final String VEGNONVEG = vegNonveg.getText().toString();
                final String DESCRIPTION = description.getText().toString();
                final String HOUSETYPE = autoCompleteTextView2.getText().toString();
                final String COLLEGE = autoCompleteTextView1.getText().toString();
                final String AREA = autoCompleteTextView3.getText().toString();

                if(OWNERNAME != null && OWNERCONTACT !=null && OWNEREMAIL != null && RENTAMT != null && DEPOSITAMT != null
                        && AREASQFT != null && FACING != null && FURNISHING != null && WATER != null && PARKING != null && GENDER != null
                        && SECURITY != null && VEGNONVEG != null && DESCRIPTION != null && isImageAdded!=false && HOUSETYPE != null && COLLEGE != null && AREA != null){
                    progressBar.setVisibility(View.VISIBLE);
                    textViewProgress.setVisibility(View.VISIBLE);

                    final String key = databaseReference.push().getKey();
                    storageReference.child(key+".jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            storageReference.child(key+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    HashMap<String, String> hashMap = new HashMap<>();
                                    hashMap.put("PropertyType", PROPERTYTYPE);
                                    hashMap.put("OwnerName", OWNERNAME);
                                    hashMap.put("OwnerContact", OWNERCONTACT);
                                    hashMap.put("OwnerEmail", OWNEREMAIL);
                                    hashMap.put("RentAmt", RENTAMT);
                                    hashMap.put("DepositAmt", DEPOSITAMT);
                                    hashMap.put("Areasqft", AREASQFT);
                                    hashMap.put("Facing", FACING);
                                    hashMap.put("Furnishing", FURNISHING);
                                    hashMap.put("Parking", PARKING);
                                    hashMap.put("Water", WATER);
                                    hashMap.put("Gender", GENDER);
                                    hashMap.put("Security", SECURITY);
                                    hashMap.put("VegNonveg", VEGNONVEG);
                                    hashMap.put("Description", DESCRIPTION);
                                    hashMap.put("College", COLLEGE);
                                    hashMap.put("HouseType", HOUSETYPE);
                                    hashMap.put("Area", AREA);
                                    hashMap.put("ImageUrl", uri.toString());

                                    databaseReference.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(PostPropertyRoommate.this, "AD successfully posted",Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(PostPropertyRoommate.this, "AD not posted properly",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progress = (snapshot.getBytesTransferred()*100)/snapshot.getTotalByteCount();
                            progressBar.setProgress((int) progress);
                            textViewProgress.setText(progress + " %");
                        }
                    });
                }

            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_IMAGE && data!=null){
            imageUri=data.getData();
            isImageAdded=true;
            imageViewAdd.setImageURI(imageUri);
        }
    }
}