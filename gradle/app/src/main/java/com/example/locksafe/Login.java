package com.example.locksafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.locksafe.sqlite.DatabaseHelper;
import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {
    private NestedScrollView nestscroll;
    private TextInputEditText txt_email;
    private  TextInputEditText txt_password;

    private AppCompatButton loginbtn;
    private AppCompatTextView register_link;
    private DatabaseHelper dbhelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nestscroll = (NestedScrollView) findViewById(R.id.nestscroll);
        txt_email = (TextInputEditText) findViewById(R.id.txt_email);
        txt_password = (TextInputEditText) findViewById(R.id.txt_password);
        loginbtn = (AppCompatButton) findViewById(R.id.loginbtn);
        register_link = (AppCompatTextView) findViewById(R.id.register_link);
        dbhelper = new DatabaseHelper(Login.this);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbhelper.userCheck(txt_email.getText().toString().trim(), txt_password.getText().toString().trim())){
                    Intent intent = new Intent(Login.this, Encrypt.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), " Email Already Exists ", Toast.LENGTH_SHORT).show();

                }

            }
        });

        register_link.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent reg_intent = new Intent(getApplicationContext(), Register.class);
                startActivity(reg_intent);

            }
        });




    }
}