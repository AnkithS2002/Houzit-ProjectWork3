package com.example.houzit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class EditAccount extends AppCompatActivity {

    Button button;
    EditText email, phone, name;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    private String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        button=findViewById(R.id.button);
        email=findViewById(R.id.editTextEmail);
        phone=findViewById(R.id.editTextPhone);
        name=findViewById(R.id.editTextName);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        userID = firebaseAuth.getCurrentUser().getUid();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newmail = email.getText().toString();
                String newphone = phone.getText().toString();
                String newname = name.getText().toString();
                if (newmail != null && newname != null && newphone != null){
                    HashMap<String, Object> usrDetail = new HashMap<>();
                    usrDetail.put("name", newname);
                    usrDetail.put("number", newphone);

                    firebaseFirestore.collection("User")
                            .whereEqualTo("email", newmail)
                            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful() && !task.getResult().isEmpty()){
                                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                                        String documentID = documentSnapshot.getId();
                                        firebaseFirestore.collection("User")
                                                .document(documentID)
                                                .update(usrDetail)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Toast.makeText(EditAccount.this, "Updated account successfully", Toast.LENGTH_SHORT).show();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(EditAccount.this, "Failed to Update account", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }
                                    else {
                                        Toast.makeText(EditAccount.this, "Failed to Update account1", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}