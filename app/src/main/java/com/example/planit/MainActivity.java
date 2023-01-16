package com.example.planit;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner=findViewById(R.id.spinner);
        String[] action = {"Edit Profile","Dashboard","Report Error","Logout"};
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, action);
        spinner.setAdapter(adapter);


    }

}