package com.example.planit;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaCodec;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login_reg extends AppCompatActivity {

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

    EditText editText1,editText2;
    Button button4,button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_reg);

        editText1=findViewById(R.id.editText1);
        editText2=findViewById(R.id.editText2);
        String email=editText1.getText().toString();
        String pswd= editText1.getText().toString();

        button4=findViewById(R.id.button4);
        button5=findViewById(R.id.button5);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login_reg.this, MainActivity.class);
                startActivity(intent);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login_reg.this, signup_reg.class);
                startActivity(intent);
            }
        });

        if(emailvalid(email))
        {

        }
        else {
//            Toast.makeText(this, "wrong email address", Toast.LENGTH_SHORT).show();
        }
        if(pswdvalid(pswd))
        {

        }
        else {
//            Toast.makeText(this, "Password must contain 6 character & only letters & digits", Toast.LENGTH_SHORT).show();
        }
    }


}