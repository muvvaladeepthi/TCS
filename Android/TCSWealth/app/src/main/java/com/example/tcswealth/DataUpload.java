package com.example.tcswealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class DataUpload extends AppCompatActivity {
    TextView name, email, dob, phone;
    String userId;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Button submit;
    Button upload;
    String fetchedId;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> user = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_upload);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        dob = findViewById(R.id.dateofbirth);
        phone = findViewById(R.id.phone);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        submit = findViewById(R.id.button);
        upload = findViewById(R.id.button2);
        Intent intent = getIntent();
        fetchedId = intent.getStringExtra("id");
        upload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),Filechooser.class);
                startActivity(i);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.put("Name", name.getText().toString());
                user.put("email", email.getText().toString());
                user.put("dob", dob.getText().toString());
                user.put("phone", phone.getText().toString());
                SubmitToFire();
                if (name.getText().toString().length() != 0 && email.getText().toString().length() != 0 && dob.getText().toString().length() != 0 && phone.getText().toString().length() != 0) {

                } else {
                    Toast.makeText(DataUpload.this, "Please Give Details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    void SubmitToFire() {


        DocumentReference doc = db.collection("users").document(fetchedId).collection("Parameters").document(fetchedId);
        doc.update("Name", name.getText().toString());
                doc.update("email", email.getText().toString());
                doc.update("dob", dob.getText().toString());
                doc.update("phone", phone.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(DataUpload.this, "Sucess", Toast.LENGTH_SHORT).show();

                    }
                });super.onBackPressed();
    }
}





    //            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                @Override
//                public void onSuccess(DocumentReference documentReference) {
//                    Log.i( "documentReference", documentReference.toString());
//                    Toast.makeText(DataUpload.this, "Successfull", Toast.LENGTH_LONG).show();
//                    name.setText("");
//                    email.setText("");
//                    dob.setText("");
//                    phone.setText("");
//
//                }
//            })
//            .addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Log.i( "Error adding document", e.toString());
//                    Toast.makeText(DataUpload.this, "Failed", Toast.LENGTH_LONG).show();
//                }
//            });
//}

//}
