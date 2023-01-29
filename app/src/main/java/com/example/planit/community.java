package com.example.planit;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class community extends AppCompatActivity implements AdapterView.OnItemSelectedListener,rcViewClick {

    Spinner spinner;
    ImageView imageViewreg;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    StorageReference storageReference;
    DocumentReference documentReference;
    CollectionReference usersc;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<User> userList;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

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


        usersc = fStore.collection("users");
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userList = new ArrayList<>();
        recyclerViewAdapter = new RecyclerViewAdapter(this, userList, this);
        recyclerView.setAdapter(recyclerViewAdapter);

        fStore.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User user = document.toObject(User.class);
//                                user.streak=Integer.parseInt(document.get("streak").toString());
                                userList.add(user);

//                                Log.d(TAG, document.getId() + " => " + document.getData());
                                //toast
//                                Toast.makeText(community.this, "testing1", Toast.LENGTH_SHORT).show();
                            }
//                            for (int i = 0; i < userList.size(); i++) {
////                                Toast.makeText(community.this, userList.get(i).name, Toast.LENGTH_SHORT).show();
////                                Log.d("TAG", "onComplete: " + userList.get(i).name);
//                            }
                            recyclerViewAdapter.notifyDataSetChanged();
                        } else {
//                            Toast.makeText(community.this, "error1", Toast.LENGTH_SHORT).show();

                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


        spinner=findViewById(R.id.spinner);
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
            if(func.equals("Report Error")){}
//                startActivity(new Intent(this, profile_x.class));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override
    public void itemOnClick(int position) {
        startActivity(new Intent(this, profile_view.class));
    }
}