package com.example.locksafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

//fixed this error
//import com.example.locksafe.sql.DatabaseHelper;
import com.example.locksafe.sqlite.DatabaseHelper;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {
    private NestedScrollView nestscroll;
    private TextInputLayout txtIL_email;
    private TextInputEditText txt_email;
    private TextInputLayout txtIL_password;
    private TextInputEditText txt_password;
    private AppCompatButton loginbtn;
    private AppCompatTextView register_link;
    private DatabaseHelper dbhelper;
    private ValidateInput validinput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nestscroll = (NestedScrollView) findViewById(R.id.nestscroll);
        txtIL_email = (TextInputLayout) findViewById(R.id.txtIL_email);
        txt_email = (TextInputEditText) findViewById(R.id.txtEdit_email);//fixed from R.id.txt_email to match with layout
        txtIL_password = (TextInputLayout) findViewById(R.id.txtIL_password);
        txt_password = (TextInputEditText) findViewById(R.id.txtEdit_password);//fixed from R.id.txt_password to match with layout
        loginbtn = (AppCompatButton) findViewById(R.id.loginbtn);
        register_link = (AppCompatTextView) findViewById(R.id.register_link);
        dbhelper = new DatabaseHelper(Login.this);
        validinput = new ValidateInput(Login.this);
        
        register_link.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent reg_intent = new Intent(getApplicationContext(), Register.class);
                startActivity(reg_intent);

            }
        });


       loginbtn.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                if (!validinput.isTextFilled(txt_email, txtIL_email, getString(R.string.error_email))) {
                    return;
                }


                if (!validinput.isEmail(txt_email, txtIL_email,

                        getString(R.string.error_email))) {
                    return;
                }

                if (!validinput.isTextFilled(txt_password, txtIL_password, getString(R.string.error_email))) {
                    return;
                }



                    if (dbhelper.userCheck(txt_email.getText().toString().trim(), txt_password.getText().toString().trim())) {
                        Intent loginIntent = new Intent(Login.this, Home.class);
                        loginIntent.putExtra("EMAIL", txt_email.getText().toString().trim());
                        emptyText();
                        Login.this.startActivity(loginIntent);
                        Toast.makeText(getApplicationContext(), R.string.login_successful, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.login_unsuccessful, Toast.LENGTH_SHORT).show();

                    }


            }


                private void emptyText () {
                txt_email.setText(null);
                txt_password.setText(null);
            }
            });
        }}
