package com.example.locksafe;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Encrypt extends AppCompatActivity {
    private NestedScrollView nestscroll;
    private TextInputLayout txtIL_encrypt;
    private AppCompatButton randombtn;
    private TextInputEditText txtEdit_enterpassword;
    private AppCompatButton encryptbtn;
    private AppCompatButton decryptbtn;
    private AppCompatTextView encrypt_output;
    private String output;

    private String AES = "AES";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);
        randombtn = (AppCompatButton) findViewById(R.id.random_btn);
        txtEdit_enterpassword = (TextInputEditText) findViewById(R.id.txtEdit_enterpassword);
        encryptbtn = (AppCompatButton) findViewById(R.id.encryptbtn);
        decryptbtn = (AppCompatButton) findViewById(R.id.decryptbtn);
        encrypt_output = (AppCompatTextView) findViewById(R.id.encrypt_output);

         encryptbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check if password < 15 characters
                if (txtEdit_enterpassword.length() < 15){
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.nestscroll), "Password cannot be less than 15 characters", Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else {

                    try {
                        output = encrypt(txtEdit_enterpassword.getText().toString());
                        encrypt_output.setText(output);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            }
        });

        decryptbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                //check if password < 15 characters
                if (txtEdit_enterpassword.length() < 15){
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.nestscroll), "Password cannot be less than 15 characters", Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else {

                    try {
                        output = decrypt(output, txtEdit_enterpassword.getText().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    encrypt_output.setText(output);
                }
            }
        });

        randombtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int leftLimit = 48; // character '0'
                int rightLimit = 122; // character 'z'
                int targetStringLength = 20;
                Random random = new Random();
                // The StringBuilder class creates a mutable sequence of characters
                // This line constructs a string builder with no characters in it and an initial
                // capacity specified by the argument, in this case 20
                StringBuilder str = new StringBuilder(targetStringLength);
                // Loop that appends characters one by one to str
                for (int i = 0; i < targetStringLength; i++) {
                    // Generates random floats between 0.0 and 1.0 then converts
                    // them to ints between the limits set
                    int randomLimitedInt = leftLimit + (int)
                            (random.nextFloat() * (rightLimit - leftLimit + 1));
                    str.append((char) randomLimitedInt);
                }
                String generatedString = str.toString();
                // Displays the new random password in the text view
                txtEdit_enterpassword.setText(generatedString);
            }
        });
    }

    private String decrypt(String output, String password) throws Exception{
        SecretKeySpec key = generateKey(password);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodeVal = Base64.decode(output, Base64.DEFAULT);
        byte[] decryptVal = cipher.doFinal(decodeVal);
        String decryptedVal = new String(decryptVal);
        return decryptedVal;
    }

    private String encrypt(String password) throws Exception {
        SecretKeySpec key = generateKey(password);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptVal = cipher.doFinal(password.getBytes());
        String encryptedVal = Base64.encodeToString(encryptVal, Base64.DEFAULT);
        return encryptedVal;
    }

    private SecretKeySpec generateKey(String password) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec sKeySpec = new SecretKeySpec(key, "AES");
        return sKeySpec;

    }
}
