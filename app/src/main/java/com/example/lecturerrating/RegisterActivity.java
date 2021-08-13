package com.example.lecturerrating;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private TextView haveAccount;
    private EditText username,email,password,re_password;
    private String username_s,email_s,password_s;
    private Button register;
    private ImageView eye_pass1, eye_pass2;
    private int[] witch_time={0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
        eye_click();
        register_clicked();
        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                haveAccount.setTextColor(getResources().getColor(R.color.Red));
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void register_clicked() {

        // TODO: check all string is fine
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int errors = 0;
                String message = username.getText().toString();
                //Trim whitespace
                message = message.trim();
                //Checks if the message has anything.
                if (message.length() == 0) {
                    username.setError("Please Enter a Name!");
                    errors++;

                }
                else {
                    username_s = username.getText().toString();
                }
                message = email.getText().toString().trim();
                //Checks if the message has anything.
                if (message.length() == 0) {
                    email.setError("Please Enter a Email!");
                    errors++;
                }
                else {
                    email_s = email.getText().toString();
                }
                message = password.getText().toString().trim();
                //Checks if the message has anything.
                if (message.length() == 0) {
                    password.setError("Please Enter a Password!");
                    errors++;
                }
                else {
                    password_s = password.getText().toString();
                }
                String pass = re_password.getText().toString().trim();
                //Checks if the message has anything.
                if (pass.length() == 0) {
                    re_password.setError("Please Enter your Password again!");
                    errors++;
                }
                if(!pass.equals(message)){
                    re_password.setError("The password is not match!");
                    errors++;
                }
                if(errors==0) addToAllProfiles();
            }
        });
    }

    private void eye_click() {
        eye_pass1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(witch_time[0]==0) {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                else {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                witch_time[0] = 1-witch_time[0];
            }
        });

        eye_pass2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(witch_time[1]==0) {
                    re_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                else {
                    re_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                witch_time[1] = 1-witch_time[1];
            }
        });
    }

    private void addToAllProfiles() {
        PersonalProfile p = new PersonalProfile(username_s,email_s,password_s);
        Utils base = Utils.getInstance();
        base.addNewProfile(p);
        username.setText("");
        email.setText("");
        password.setText("");
        re_password.setText("");
        Toast.makeText(this, "Register Complete successfully!", Toast.LENGTH_SHORT).show();
    }

    private void initViews() {
        haveAccount = findViewById(R.id.txt_already_have_account);
        username = findViewById(R.id.et_name_register);
        email = findViewById(R.id.et_email_register);
        password = findViewById(R.id.et_password_register);
        re_password = findViewById(R.id.et_re_password_register);
        register = findViewById(R.id.btn_register_register);
        eye_pass1 = findViewById(R.id.eye_icon1);
        eye_pass2 = findViewById(R.id.eye_icon2);

    }


}