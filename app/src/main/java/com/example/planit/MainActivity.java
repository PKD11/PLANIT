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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planit.ml.MobilenetV110224Quant;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    Button checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, button6, button7, button8, button9, button10;
    ImageView imageView,imageView2, imageView3, imageView4, imageView5, imageView6;
    TextView textView2, textView3, textView4, textView5, textView6;
    Bitmap b1,b2,b3,b4,b5,b6;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner=findViewById(R.id.spinner);
        String[] action = {"Edit Profile","Dashboard","Report Error","Logout"};
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, action);
        spinner.setAdapter(adapter);

        checkBox2=findViewById(R.id.checkBox2);
        checkBox3=findViewById(R.id.checkBox3);
        checkBox4=findViewById(R.id.checkBox4);
        checkBox5=findViewById(R.id.checkBox5);
        checkBox6=findViewById(R.id.checkBox6);

        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);

        button6=findViewById(R.id.button6);
        button7=findViewById(R.id.button7);
        button8=findViewById(R.id.button8);
        button9=findViewById(R.id.button9);
        button10=findViewById(R.id.button10);

        textView2=findViewById(R.id.TextView2);
        textView3=findViewById(R.id.TextView3);
        textView4=findViewById(R.id.TextView4);
        textView5=findViewById(R.id.TextView5);
        textView6=findViewById(R.id.TextView6);

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 100);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 200);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 300);
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 400);
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 500);
            }
        });

        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2=Bitmap.createScaledBitmap(b2, 224,224,true);
                try {
                    MobilenetV110224Quant model = MobilenetV110224Quant.newInstance(getApplicationContext());

                    // Creates inputs for reference.
                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.UINT8);

                    TensorImage tensorImage= new TensorImage(DataType.UINT8);
                    tensorImage.load(b2);
                    ByteBuffer byteBuffer= tensorImage.getBuffer();

                    inputFeature0.loadBuffer(byteBuffer);

                    // Runs model inference and gets result.
                    MobilenetV110224Quant.Outputs outputs = model.process(inputFeature0);
                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                    float[] arr=outputFeature0.getFloatArray();

                    // Releases model resources if no longer used.
                    model.close();

                    if(isvalid(arr)) textView2.setText(R.string.verified);
                } catch (IOException e) {
                    // TODO Handle the exception
                    Toast.makeText(MainActivity.this, "This is not a registered task?!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b3=Bitmap.createScaledBitmap(b3, 224,224,true);
                try {
                    MobilenetV110224Quant model = MobilenetV110224Quant.newInstance(getApplicationContext());

                    // Creates inputs for reference.
                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.UINT8);

                    TensorImage tensorImage= new TensorImage(DataType.UINT8);
                    tensorImage.load(b3);
                    ByteBuffer byteBuffer= tensorImage.getBuffer();

                    inputFeature0.loadBuffer(byteBuffer);

                    // Runs model inference and gets result.
                    MobilenetV110224Quant.Outputs outputs = model.process(inputFeature0);
                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                    float[] arr=outputFeature0.getFloatArray();

                    // Releases model resources if no longer used.
                    model.close();

                    if(isvalid(arr)) textView3.setText(R.string.verified);
                } catch (IOException e) {
                    // TODO Handle the exception
                    Toast.makeText(MainActivity.this, "This is not a registered task?!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        checkBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b4=Bitmap.createScaledBitmap(b4, 224,224,true);
                try {
                    MobilenetV110224Quant model = MobilenetV110224Quant.newInstance(getApplicationContext());

                    // Creates inputs for reference.
                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.UINT8);

                    TensorImage tensorImage= new TensorImage(DataType.UINT8);
                    tensorImage.load(b4);
                    ByteBuffer byteBuffer= tensorImage.getBuffer();

                    inputFeature0.loadBuffer(byteBuffer);

                    // Runs model inference and gets result.
                    MobilenetV110224Quant.Outputs outputs = model.process(inputFeature0);
                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                    float[] arr=outputFeature0.getFloatArray();

                    // Releases model resources if no longer used.
                    model.close();

                    if(isvalid(arr)) textView4.setText(R.string.verified);
                } catch (IOException e) {
                    // TODO Handle the exception
                    Toast.makeText(MainActivity.this, "This is not a registered task?!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        checkBox5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b5=Bitmap.createScaledBitmap(b5, 224,224,true);
                try {
                    MobilenetV110224Quant model = MobilenetV110224Quant.newInstance(getApplicationContext());

                    // Creates inputs for reference.
                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.UINT8);

                    TensorImage tensorImage= new TensorImage(DataType.UINT8);
                    tensorImage.load(b5);
                    ByteBuffer byteBuffer= tensorImage.getBuffer();

                    inputFeature0.loadBuffer(byteBuffer);

                    // Runs model inference and gets result.
                    MobilenetV110224Quant.Outputs outputs = model.process(inputFeature0);
                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                    float[] arr=outputFeature0.getFloatArray();

                    // Releases model resources if no longer used.
                    model.close();

                    if(isvalid(arr)) textView5.setText(R.string.verified);
                } catch (IOException e) {
                    // TODO Handle the exception
                    Toast.makeText(MainActivity.this, "This is not a registered task?!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        checkBox6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b6=Bitmap.createScaledBitmap(b6, 224,224,true);
                try {
                    MobilenetV110224Quant model = MobilenetV110224Quant.newInstance(getApplicationContext());

                    // Creates inputs for reference.
                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.UINT8);

                    TensorImage tensorImage= new TensorImage(DataType.UINT8);
                    tensorImage.load(b6);
                    ByteBuffer byteBuffer= tensorImage.getBuffer();

                    inputFeature0.loadBuffer(byteBuffer);

                    // Runs model inference and gets result.
                    MobilenetV110224Quant.Outputs outputs = model.process(inputFeature0);
                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                    float[] arr=outputFeature0.getFloatArray();

                    // Releases model resources if no longer used.
                    model.close();

                    if(isvalid(arr)) textView6.setText(R.string.verified);
                } catch (IOException e) {
                    // TODO Handle the exception
                    Toast.makeText(MainActivity.this, "This is not a registered task?!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100)
        {
            assert data != null;
            imageView2.setImageURI(data.getData());

            Uri uri=data.getData();
            try {
                b2= MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(requestCode==200)
        {
            assert data != null;
            imageView3.setImageURI(data.getData());

            Uri uri=data.getData();
            try {
                b3= MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(requestCode==300)
        {
            assert data != null;
            imageView4.setImageURI(data.getData());

            Uri uri=data.getData();
            try {
                b4= MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(requestCode==400)
        {
            assert data != null;
            imageView5.setImageURI(data.getData());

            Uri uri=data.getData();
            try {
                b5= MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(requestCode==500)
        {
            assert data != null;
            imageView6.setImageURI(data.getData());

            Uri uri=data.getData();
            try {
                b6= MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isvalid(float[] arr)
    {
        int max=0;float con=0;
        for(int i=0; i<arr.length;i++){
            if(arr[i]>con){
                con=arr[i];
                max=i;
            }
        }
        return max > 0;
    }
}