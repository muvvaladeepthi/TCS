package com.example.tcswealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;

public class Welness extends AppCompatActivity {
FloatingActionButton flotBtn,refreshBtn;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
TextView bp,bodyTemparature,glucose,respiration,heatRate,oxygenSaturation,ElectroCardiagram;
    String str;
    List dataList;
    int listSize;
    HashMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welness);
        bp = findViewById(R.id.bloodpresdurevalue);
        bodyTemparature = findViewById(R.id.bodyteparetureValue);
        glucose = findViewById(R.id.glucosevalue);
        respiration = findViewById(R.id.respirationvalue);
        heatRate= findViewById(R.id.heartratevalue);
        oxygenSaturation = findViewById(R.id.oxygensaturationvalue);
        ElectroCardiagram = findViewById(R.id.elecrocardiogramvalue);
        refreshBtn=findViewById(R.id.refresh);
        Intent intent = getIntent();
         str = intent.getStringExtra("id");
        getData();


            refreshBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getData();

                }
            });
        flotBtn= (FloatingActionButton) findViewById(R.id.flotBtn);
//        Toast.makeText(Welness.this,str, Toast.LENGTH_SHORT).show();

        flotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), DataUpload.class);
                intent.putExtra("id", str);


                startActivity(intent);
            }
        });
    }

    void getData(){

        DocumentReference docRef=db.collection("users").document(str).collection("Parameters").document("Parameters");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
           if(task.isComplete()) {
               try{
               DocumentSnapshot data=task.getResult();
               System.out.println(data);
                   Toast.makeText(Welness.this,"Refresh", Toast.LENGTH_SHORT).show();

                   dataList= (List) data.getData().get("Data");
                   listSize=dataList.size()-1;
                   map= (HashMap) dataList.get(listSize);
                   SetData();
                   SystemClock.sleep(50);

               }catch (Exception e){
                   Toast.makeText(Welness.this, "No user Found With This ID", Toast.LENGTH_SHORT).show();
               }

           }
            }
        });

    }

    void SetData(){

        bp.setText(map.get("bp").toString());
    bodyTemparature.setText(map.get("bodyTemparature").toString());
    glucose.setText(map.get("glucose").toString());
    respiration.setText(map.get("respiration").toString());
    heatRate.setText(map.get("heartRate").toString());
    oxygenSaturation.setText(map.get("oxygenSaturation").toString());
    ElectroCardiagram.setText(map.get("electroCardiogram").toString());
    }
}