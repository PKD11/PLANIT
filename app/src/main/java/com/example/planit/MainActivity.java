package com.example.planit;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.planit.ml.ModelUnquant;
import com.google.firebase.firestore.FirebaseFirestore;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    Button checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, button6, button7, button8, button9, button10, button2,buttonSub;
    ImageView imageView,imageView2, imageView3, imageView4, imageView5, imageView6;
    TextView textView2, textView3, textView4, textView5, textView6;
    Bitmap b1,b2,b3,b4,b5,b6;
    FirebaseFirestore dbroot;

    Boolean doneToday=false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    dbroot=FirebaseFirestore.getInstance();
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
        buttonSub=findViewById(R.id.buttonSubmit);

        textView2=findViewById(R.id.TextView2);
        textView3=findViewById(R.id.TextView3);
        textView4=findViewById(R.id.TextView4);
        textView5=findViewById(R.id.TextView5);
        textView6=findViewById(R.id.TextView6);

        String[] task={"Planting","Garbage","Cleaning","Watering"};


        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              doneToday=true;
              //check previous date and update accordingly;
                Toast.makeText(MainActivity.this, "Submitted", Toast.LENGTH_SHORT).show();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch camera if we have permission
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(MainActivity.this, "Opening Camera", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,100);
                }
                //Ask for permission if needed
                else
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},1);
                }
                button6.setEnabled(false);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch camera if we have permission
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(MainActivity.this, "Opening Camera", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,200);
                }
                //Ask for permission if needed
                else
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},2);
                }
                button7.setEnabled(false);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch camera if we have permission
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(MainActivity.this, "Opening Camera", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,300);
                }
                //Ask for permission if needed
                else
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},3);
                }
                button8.setEnabled(false);
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch camera if we have permission
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(MainActivity.this, "Opening Camera", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,400);
                }
                //Ask for permission if needed
                else
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},4);
                }
                button9.setEnabled(false);
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch camera if we have permission
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(MainActivity.this, "Opening Camera", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,500);
                }
                //Ask for permission if needed
                else
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},5);
                }
                button10.setEnabled(false);
            }
        });

        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2=Bitmap.createScaledBitmap(b2, 224,224,true);
                try {
                    ModelUnquant model = ModelUnquant.newInstance(getApplicationContext());

                    // Creates inputs for reference.
                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);

                    TensorImage tensorImage= new TensorImage(DataType.FLOAT32);
                    tensorImage.load(b2);
                    ByteBuffer byteBuffer= tensorImage.getBuffer();

                    inputFeature0.loadBuffer(byteBuffer);

                    // Runs model inference and gets result.
                    ModelUnquant.Outputs outputs = model.process(inputFeature0);
                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                    float[] arr=outputFeature0.getFloatArray();

                    // Releases model resources if no longer used.
                    model.close();

                    if(isvalid(arr)==(int)isvalid(arr)) {
                        textView2.setText(task[isvalid(arr)]);
                        Toast.makeText(MainActivity.this, R.string.verified, Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MainActivity.this, "Disapointment", Toast.LENGTH_SHORT).show();
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
                    ModelUnquant model = ModelUnquant.newInstance(getApplicationContext());

                    // Creates inputs for reference.
                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);

                    TensorImage tensorImage= new TensorImage(DataType.FLOAT32);
                    tensorImage.load(b3);
                    ByteBuffer byteBuffer= tensorImage.getBuffer();

                    inputFeature0.loadBuffer(byteBuffer);

                    // Runs model inference and gets result.
                    ModelUnquant.Outputs outputs = model.process(inputFeature0);
                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                    float[] arr=outputFeature0.getFloatArray();

                    // Releases model resources if no longer used.
                    model.close();

                    if(isvalid(arr)==(int)isvalid(arr)) {
                        textView3.setText(task[isvalid(arr)]);
                        Toast.makeText(MainActivity.this, R.string.verified, Toast.LENGTH_SHORT).show();
                    }
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
                    ModelUnquant model = ModelUnquant.newInstance(getApplicationContext());

                    // Creates inputs for reference.
                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);

                    TensorImage tensorImage= new TensorImage(DataType.FLOAT32);
                    tensorImage.load(b4);
                    ByteBuffer byteBuffer= tensorImage.getBuffer();

                    inputFeature0.loadBuffer(byteBuffer);

                    // Runs model inference and gets result.
                    ModelUnquant.Outputs outputs = model.process(inputFeature0);
                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                    float[] arr=outputFeature0.getFloatArray();

                    // Releases model resources if no longer used.
                    model.close();

                    if(isvalid(arr)==(int)isvalid(arr)) {
                        textView4.setText(task[isvalid(arr)]);
                        Toast.makeText(MainActivity.this, R.string.verified, Toast.LENGTH_SHORT).show();
                    }
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
                    ModelUnquant model = ModelUnquant.newInstance(getApplicationContext());

                    // Creates inputs for reference.
                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);

                    TensorImage tensorImage= new TensorImage(DataType.FLOAT32);
                    tensorImage.load(b5);
                    ByteBuffer byteBuffer= tensorImage.getBuffer();

                    inputFeature0.loadBuffer(byteBuffer);

                    // Runs model inference and gets result.
                    ModelUnquant.Outputs outputs = model.process(inputFeature0);
                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                    float[] arr=outputFeature0.getFloatArray();

                    // Releases model resources if no longer used.
                    model.close();

                    if(isvalid(arr)==(int)isvalid(arr)) {
                        textView5.setText(task[isvalid(arr)]);
                        Toast.makeText(MainActivity.this, R.string.verified, Toast.LENGTH_SHORT).show();
                    }
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
                    ModelUnquant model = ModelUnquant.newInstance(getApplicationContext());

                    // Creates inputs for reference.
                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);

                    TensorImage tensorImage= new TensorImage(DataType.FLOAT32);
                    tensorImage.load(b6);
                    ByteBuffer byteBuffer= tensorImage.getBuffer();

                    inputFeature0.loadBuffer(byteBuffer);

                    // Runs model inference and gets result.
                    ModelUnquant.Outputs outputs = model.process(inputFeature0);
                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                    float[] arr=outputFeature0.getFloatArray();

                    // Releases model resources if no longer used.
                    model.close();

                    if(isvalid(arr)==(int)isvalid(arr)) {
                        textView6.setText(task[isvalid(arr)]);
                        Toast.makeText(MainActivity.this, R.string.verified, Toast.LENGTH_SHORT).show();
                    }
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

        if(requestCode==100 && resultCode==RESULT_OK)
        {
            assert data != null;
            b2=(Bitmap) data.getExtras().get("data");
            int dimension = Math.min(b2.getHeight(),b2.getWidth());
            b2= ThumbnailUtils.extractThumbnail(b2,dimension,dimension);

            imageView2.setImageBitmap(b2);

        }
        if(requestCode==200 && resultCode==RESULT_OK)
        {
            assert data != null;
            b3=(Bitmap) data.getExtras().get("data");
            int dimension = Math.min(b3.getHeight(),b3.getWidth());
            b3= ThumbnailUtils.extractThumbnail(b3,dimension,dimension);

            imageView3.setImageBitmap(b3);

        }
        if(requestCode==300 && resultCode==RESULT_OK)
        {
            assert data != null;
            b4=(Bitmap) data.getExtras().get("data");
            int dimension = Math.min(b4.getHeight(),b4.getWidth());
            b4= ThumbnailUtils.extractThumbnail(b4,dimension,dimension);

            imageView4.setImageBitmap(b4);

        }
        if(requestCode==400 && resultCode==RESULT_OK)
        {
            assert data != null;
            b5=(Bitmap) data.getExtras().get("data");
            int dimension = Math.min(b5.getHeight(),b5.getWidth());
            b5= ThumbnailUtils.extractThumbnail(b5,dimension,dimension);

            imageView5.setImageBitmap(b5);

        }
        if(requestCode==500 && resultCode==RESULT_OK)
        {
            assert data != null;
            b6=(Bitmap) data.getExtras().get("data");
            int dimension = Math.min(b6.getHeight(),b6.getWidth());
            b6= ThumbnailUtils.extractThumbnail(b6,dimension,dimension);

            imageView6.setImageBitmap(b6);

        }
    }

    public int isvalid(float[] arr)
    {
        int max=0;float con=0;
        for(int i=0; i<arr.length;i++){
            if(arr[i]>con){
                con=arr[i];
                max=i;
            }
        }
        return max;
    }
}