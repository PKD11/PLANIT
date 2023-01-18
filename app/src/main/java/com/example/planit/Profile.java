package com.example.planit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class Profile extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    EditText editText1,editText2,editText3;
    ImageButton imageButton1,imageButton2,imageButton3,imageButton4,imageButton5;
    Bitmap bmp;
    Uri uri;

    FirebaseFirestore dbroot;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    StorageReference storageReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imageView=findViewById(R.id.imageView);
        textView=findViewById(R.id.textView);
        editText1=findViewById(R.id.editText1); editText1.setEnabled(false);
        editText2=findViewById(R.id.editText2); editText2.setEnabled(false);
        editText3=findViewById(R.id.editText3); editText3.setEnabled(false);
        imageButton1=findViewById(R.id.imageButton1); imageButton1.setEnabled(false);
        imageButton2=findViewById(R.id.imageButton2);
        imageButton3=findViewById(R.id.imageButton3);
        imageButton4=findViewById(R.id.imageButton4);
        imageButton5=findViewById(R.id.imageButton5);imageButton5.setEnabled(false);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();


        StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(imageView);
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton1.setVisibility(View.VISIBLE);
                imageButton1.setEnabled(true);
                imageButton5.setVisibility(View.VISIBLE);
                imageButton5.setEnabled(true);
                editText1.setEnabled(true);
                editText2.setEnabled(true);
                editText3.setEnabled(true);
            }
        });

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Profile.this, "Choose an Image", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,100);
            }
        });
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Profile.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                UpdateUser();
                imageButton1.setVisibility(View.INVISIBLE);
                imageButton1.setEnabled(false);
                imageButton5.setVisibility(View.INVISIBLE);
                imageButton5.setEnabled(false);
                editText1.setEnabled(false);
                editText2.setEnabled(false);
                editText3.setEnabled(false);
            }
        });
    }

    private void UpdateUser()
    {
        String name=editText1.getText().toString();
        String email=editText2.getText().toString();
        String loc=editText3.getText().toString();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100)
        {
            assert data != null;
            imageView.setImageURI(data.getData());
            uri=data.getData();
            try {
                bmp= MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                bmp= Bitmap.createScaledBitmap(bmp,100,100,true);
                uploadImageToFirebase(uri);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void uploadImageToFirebase(Uri imageUri) {
        // uplaod image to firebase storage
        final StorageReference fileRef = storageReference.child("users/" + fAuth.getCurrentUser().getUid() + "/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        // image uploaded to firebase storage toast
                        Picasso.get().load(uri).into(imageView);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getApplicationContext(), "Failed." + e, Toast.LENGTH_SHORT).show();

            }
        });
    }
}