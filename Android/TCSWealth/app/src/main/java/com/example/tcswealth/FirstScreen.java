package com.example.tcswealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FirstScreen extends AppCompatActivity {
EditText userId,userPassword;
Button submitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        userId=findViewById(R.id.userid);
        userPassword=findViewById(R.id.userpass);
submitBtn=findViewById(R.id.submitBtn);
        Intent i =new Intent(FirstScreen.this,Welness.class);
submitBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        String id=userId.getText().toString();
        String pass=userPassword.getText().toString();
        Bundle data = new Bundle();


        if((id.length()==0) && (pass.length()==0)){

            Toast.makeText(FirstScreen.this, "Please fill the username and passwords", Toast.LENGTH_SHORT).show();


        }else{

            if(pass.equals("a")==true){
                if(id!=null){

                    Intent intent = new Intent(getApplicationContext(), Welness.class);
                    intent.putExtra("id", id.toString());
//
//                    Bundle data1 = new Bundle();
//                    data.putString("id",id);
//                    i.putExtras(data1);

                    startActivity(intent);
//                    Toast.makeText(FirstScreen.this, id, Toast.LENGTH_SHORT).show();

                    Toast.makeText(FirstScreen.this, "Success", Toast.LENGTH_SHORT).show();

                }



            }else {
                Toast.makeText(FirstScreen.this, "Invalid Password", Toast.LENGTH_SHORT).show();
            }
        }

    }
});
    }
}