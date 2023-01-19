package com.example.planit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class community extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    ImageView imageViewreg;
    ImageView pfp2,pfp3,pfp4,pfp5;
    TextView name2,name3,name4,name5;
    TextView streak2,streak3,streak4,streak5;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    StorageReference storageReference;
    DocumentReference documentReference;

    String user2="8YjvylJLtMggw3DntAJzlUQ0c2C3";
    String user3="9sMzX5dKAVNR8Frr9i9tg94OAD32";
    String user4="TD3fC84GTbaDk0wdWTXGwyNZpts2";
    String user5="aB0J9KKLRIhOHx1Z3tV3YqjllUz2";


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

        pfp2=findViewById(R.id.pfpimage2);
        pfp3=findViewById(R.id.pfpimage3);
        pfp4=findViewById(R.id.pfpimage4);
        pfp5=findViewById(R.id.pfpimage5);

        name2=findViewById(R.id.textname1);
        name3=findViewById(R.id.textname2);
        name4=findViewById(R.id.textname3);
        name5=findViewById(R.id.textname4);

        streak2=findViewById(R.id.streak2);
        streak3=findViewById(R.id.streak3);
        streak4=findViewById(R.id.streak4);
        streak5=findViewById(R.id.streak5);




        StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(imageViewreg);
            }
        });

        StorageReference profileRef2 = storageReference.child("users/"+user2+"/profile.jpg");
        profileRef2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(pfp2);
            }
        });

        StorageReference profileRef3 = storageReference.child("users/"+user3+"/profile.jpg");
        profileRef3.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(pfp3);
            }
        });

        StorageReference profileRef4 = storageReference.child("users/"+user4+"/profile.jpg");
        profileRef4.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(pfp4);
            }
        });
        StorageReference profileRef5 = storageReference.child("users/"+user5+"/profile.jpg");
        profileRef5.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(pfp5);
            }
        });


    documentReference = fStore.collection("users").document(user2);
    documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
        @Override
        public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
            if (documentSnapshot.exists()) {

                name2.setText(documentSnapshot.getString("name"));

                Object str = documentSnapshot.get("streak");
                streak2.setText(str.toString() + " \ud83d\udd25");

            } else {
                Log.d("tag", "onEvent: Document do not exists" + user2);
            }
        }
    });


        documentReference = fStore.collection("users").document(user3);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.exists()) {

                    name3.setText(documentSnapshot.getString("name"));

                    Object str = documentSnapshot.get("streak");
                    streak3.setText(str.toString() + " \ud83d\udd25");

                } else {
                    Log.d("tag", "onEvent: Document do not exists" + user3);
                }
            }
        });


        documentReference = fStore.collection("users").document(user4);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.exists()) {

                    name4.setText(documentSnapshot.getString("name"));

                    Object str = documentSnapshot.get("streak");
                    streak4.setText(str.toString() + " \ud83d\udd25");

                } else {
                    Log.d("tag", "onEvent: Document do not exists" + user4);
                }
            }
        });


        documentReference = fStore.collection("users").document(user5);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.exists()) {

                    name5.setText(documentSnapshot.getString("name"));

                    Object str = documentSnapshot.get("streak");
                    streak5.setText(str.toString() + " \ud83d\udd25");

                } else {
                    Log.d("tag", "onEvent: Document do not exists" + user2);
                }
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