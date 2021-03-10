package com.example.locksafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.locksafe.model.User;
import com.example.locksafe.sql.DatabaseHelper;
import com.google.android.material.textfield.TextInputEditText;

public class Register extends AppCompatActivity {

    private NestedScrollView nestscroll;
    private TextInputEditText txt_name;
    private TextInputEditText txt_email;
    private TextInputEditText txt_password;
    private TextInputEditText txt_cnfpassword;
    private AppCompatButton registerbtn;
    private AppCompatTextView login_link;

    private DatabaseHelper dbhelper;
    private User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nestscroll = (NestedScrollView) findViewById(R.id.nestscroll);
        txt_name = (TextInputEditText) findViewById(R.id.txt_name);
        txt_email = (TextInputEditText) findViewById(R.id.txt_email);
        txt_password = (TextInputEditText) findViewById(R.id.txt_password);
        txt_cnfpassword = (TextInputEditText) findViewById(R.id.txt_cnfpassword);
        registerbtn = (AppCompatButton) findViewById(R.id.registerbtn);
        login_link = (AppCompatTextView) findViewById(R.id.login_link);
        dbhelper = new DatabaseHelper(Register.this);



        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!dbhelper.userCheck(txt_email.getText().toString().trim())){
                    u.setName(txt_name.getText().toString().trim());
                    u.setEmail(txt_email.getText().toString().trim());
                    u.setPassword(txt_password.getText().toString().trim());
                    dbhelper.addUser(u);
                    Toast.makeText(getApplicationContext(), "Sign Up Successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), " Wrong Email or Password ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        login_link.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent login_intent = new Intent(getApplicationContext(), Login.class);
                startActivity(login_intent);
            }
        });
    }
}