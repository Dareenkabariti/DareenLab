package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
//Button save ;
//EditText name ;
FirebaseFirestore db = FirebaseFirestore.getInstance();
  /*  Button btn_save;
    EditText et_name;*/


    EditText et_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name = findViewById(R.id.et_user_name);
    }

    public  void saveToFirebase (View v){

        String username = et_name.getText().toString();
        Map<String, Object> user = new HashMap<>();
        if(!username.isEmpty()) {
            user.put("name",username);

            db.collection("users")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.e("dareen", "Data added successfully to database: ");
                            openSecondSecreen();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("dareen", "Failed to add database", e);
                        }
                    });

        }else {
            Toast.makeText(this,"please fill feilds" ,Toast.LENGTH_SHORT).show();
        }
    }
    public  void  openSecondSecreen(){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

}