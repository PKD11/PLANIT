package com.example.planit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaCodec;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login_reg extends AppCompatActivity {
    FirebaseAuth fAuth;
    EditText editText1,editText2;
    Button loginButton,button5;
    public static boolean emailvalid(String email)
    {
        String regex="^(.+)@(.+)$";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean pswdvalid(String pswd)
    {
        if(pswd.length()==6)
        {
            int numCount = 0, charCount = 0;
            for (int i = 0; i < pswd.length(); i++)
            {
                char ch = pswd.charAt(i);
                if (is_Numeric(ch))
                    numCount++;
                else if (is_Letter(ch))
                    charCount++;
                else
                    return false;
            }
            return true;
        }
        else
            return false;
    }
    public static boolean is_Letter(char ch) {
        ch = Character.toUpperCase(ch);
        return (ch >= 'A' && ch <= 'Z');
    }
    public static boolean is_Numeric(char ch) {
        return (ch >= '0' && ch <= '9');
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_reg);

        editText1=findViewById(R.id.editText1);
        editText2=findViewById(R.id.editText2);

        loginButton=findViewById(R.id.login1);
        button5=findViewById(R.id.login2);
        fAuth = FirebaseAuth.getInstance();


//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(login_reg.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=editText1.getText().toString();
                String password= editText2.getText().toString();

                if(TextUtils.isEmpty(email)){
                    editText1.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    editText2.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6){
                    editText2.setError("Password Must be >= 6 Characters");
                    return;
                }


                // authenticate the user

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(login_reg.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(login_reg.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });

            }
        });


        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login_reg.this, signup_reg.class);
                startActivity(intent);
            }
        });

//        if(emailvalid(email))
//        {
//
//        }
//        else {
////            Toast.makeText(this, "wrong email address", Toast.LENGTH_SHORT).show();
//        }
//        if(pswdvalid(password))
//        {
//
//        }
//        else {
////            Toast.makeText(this, "Password must contain 6 character & only letters & digits", Toast.LENGTH_SHORT).show();
//        }
    }


}