package com.example.planit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class community extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    ImageView imageViewreg;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    StorageReference storageReference;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        spinner=findViewById(R.id.spinner);
        imageViewreg=findViewById(R.id.imageViewreg);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();


        StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(imageViewreg);
            }
        });

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.community,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String func= adapterView.getItemAtPosition(i).toString();
        Toast.makeText(this, func, Toast.LENGTH_SHORT).show();
            if(func.equals("Dashboard"))
                startActivity(new Intent(this, MainActivity.class));
            if(func.equals("Profile"))
                startActivity(new Intent(this, Profile.class));
            if(func.equals("Logout"))
                startActivity(new Intent(this, login_reg.class));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}