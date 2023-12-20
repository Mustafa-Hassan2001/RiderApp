package com.developer.riderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HomePage extends AppCompatActivity {
    Button btn_next1;
    String id, name, parcels, amount;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText edt_id, edt_name, edt_totalParcels, edt_tottalAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        btn_next1 = findViewById(R.id.btn_next);
        edt_id = findViewById(R.id.edt_id);
        edt_name = findViewById(R.id.edt_name);
        edt_totalParcels = findViewById(R.id.edt_totalParcels);
        edt_tottalAmount = findViewById(R.id.edt_totalAmount);
        btn_next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = edt_id.getText().toString();
                name = edt_name.getText().toString();
                parcels = edt_totalParcels.getText().toString();
                amount = edt_tottalAmount.getText().toString();
                Map<String, Object> userData = new HashMap<>();
                userData.put("id", id);
                userData.put("name", name);
                userData.put("parcels", parcels);
                userData.put("amount", amount);
                db.collection("userData").document().set(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(HomePage.this, "Data Saved", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(HomePage.this, "Data Not Uploaded", Toast.LENGTH_SHORT).show();
                    }
                });
                Intent intent0 = new Intent(HomePage.this, Parcel.class);
                startActivity(intent0);
            }
        });


    }
}