package com.example.planit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class signup_reg extends AppCompatActivity {

    Button button, button2;
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

        String name=editText1.getText().toString();
        String age=editText2.getText().toString();
        String loc=editText3.getText().toString();
        String email=editText4.getText().toString();
        String pswd=editText5.getText().toString();

        imageView=findViewById(R.id.imageView);

        button=findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(signup_reg.this, "Choose an image", Toast.LENGTH_SHORT).show();

                Intent intent= new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,200);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(signup_reg.this, "You have sucessfully registered!!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(signup_reg.this, login_reg.class);
                startActivity(intent);
            }
        });
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}