package com.example.planit;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;

public class signup_reg extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseFirestore dbroot;
    DatabaseReference db;

    Button buttonSub, buttonImg;
    EditText editText1, editText2, editText3, editText4, editText5;
    ImageView imageView;
    Bitmap bmp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_reg);

        editText1=findViewById(R.id.editText1);
        editText2=findViewById(R.id.editText2);
        editText3=findViewById(R.id.editText3);
        editText4=findViewById(R.id.editText4);
        editText5=findViewById(R.id.editText5);

        imageView=findViewById(R.id.imageView);

        buttonSub=findViewById(R.id.buttonReg);
        buttonImg=findViewById(R.id.buttonImgReg);
        mAuth = FirebaseAuth.getInstance();

        buttonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(signup_reg.this, "Choose an image", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,200);
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(signup_reg.this, "You have sucessfully registered!!", Toast.LENGTH_SHORT).show();
                RegisterUser();


            }
        });
    }

    private void RegisterUser() {

        String name=editText1.getText().toString();
        Integer age= Integer.valueOf(editText2.getText().toString());
        String email=editText4.getText().toString();
        String loc=editText3.getText().toString();

        String pswd=editText5.getText().toString();

        mAuth.createUserWithEmailAndPassword(email,pswd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User user=new User(name,age,email,loc);
                    //save user to firebase
                   FirebaseDatabase.getInstance().getReference("Users")
                           .child(mAuth.getCurrentUser().getUid())
                           .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(signup_reg.this, "You have sucessfully registered!!", Toast.LENGTH_SHORT).show();
//                                Intent intent=new Intent(signup_reg.this,MainActivity.class);
//                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(signup_reg.this, "Registration failed!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

//                    dbroot= FirebaseFirestore.getInstance();

//                    dbroot.collection("users").document(email).set(user);
                    Toast.makeText(signup_reg.this, "User Created", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(signup_reg.this, "Error!!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        Intent intent=new Intent(signup_reg.this, login_reg.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==200)
        {
            assert data != null;
            imageView.setImageURI(data.getData());
            Uri uri=data.getData();
            try {
                bmp= MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                bmp=Bitmap.createScaledBitmap(bmp,100,100,true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}