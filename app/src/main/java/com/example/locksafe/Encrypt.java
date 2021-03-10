package com.example.locksafe;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Encrypt extends AppCompatActivity {
    private NestedScrollView nestscroll;
    private TextInputLayout txtIL_encrypt;
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
        txtEdit_enterpassword = (TextInputEditText) findViewById(R.id.txtEdit_enterpassword);
        encryptbtn = (AppCompatButton) findViewById(R.id.encryptbtn);
        decryptbtn = (AppCompatButton) findViewById(R.id.decryptbtn);
        encrypt_output = (AppCompatTextView) findViewById(R.id.encrypt_output);

        encryptbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    output = encrypt(txtEdit_enterpassword.getText().toString());
                    encrypt_output.setText(output);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                } catch (BadPaddingException | InvalidKeyException e) {
                    e.printStackTrace();
                }
            }
        });




    }

    private String encrypt(String s) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        SecretKeySpec key = generateKey(s);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(s.getBytes());
        String encryptedValue = Base64.encodeToString(encVal, Base64.DEFAULT);
        return encryptedValue;


    }

    private SecretKeySpec generateKey(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final MessageDigest digest = MessageDigest.getInstance("SRA-256");
        byte[] bytes = s.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;

    }
}
