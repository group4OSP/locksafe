package com.example.locksafe;

import android.app.Activity;
import android.content.Context;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

//I renamed ValidInput.java to ValidateInput.java because it was throwing errors

public class ValidateInput {
    private Context context;

    public ValidateInput(Context context){
        this.context = context;
    }

   public boolean isTextFilled(TextInputEditText txtEditText, TextInputLayout txtLayout, String warning){
        String val = txtEditText.getText().toString().trim();
        if(val.isEmpty()){
            txtLayout.setError(warning);
            hideKeyboard(txtEditText);
            return false;
        }else {
            txtLayout.setErrorEnabled(false);
        }
        return true;
   }

   public boolean isEmail(TextInputEditText txtEditText, TextInputLayout txtLayout, String warning){
        String val = txtEditText.getText().toString().trim();
        if(val.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(val).matches()){
            txtLayout.setError(warning);
            hideKeyboard((txtEditText));
            return false;
        } else {
            txtLayout.setErrorEnabled(false);
        }
        return true;
   }

   public boolean inputTextMatches(TextInputEditText txtEditText1, TextInputEditText txtEditText2, TextInputLayout txtLayout, String warning){
        String val1 = txtEditText1.getText().toString().trim();
        String val2 = txtEditText2.getText().toString().trim();
        if(!val1.contentEquals(val2)){
            txtLayout.setError(warning);
            hideKeyboard(txtEditText2);
            return false;
        }else{
            txtLayout.setErrorEnabled(false);
        }
        return true;
   }

    private void hideKeyboard(View v) {
        InputMethodManager inputmethman = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputmethman.hideSoftInputFromWindow(v.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

}
