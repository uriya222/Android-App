// for converting icon from svg file to xml use this site http://inloop.github.io/svg2android/


package com.example.lecturerrating;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView txt, fp, DontHaveAccount , SingUp;
    private Button login, google,facebook;
    private EditText user_name , password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

    }

    private void initViews() {
        fp = findViewById(R.id.txt_forgot_password_login);
        SingUp = findViewById(R.id.sign_up_login);
        login = findViewById(R.id.btn_login_login);
        google = findViewById(R.id.btn_google_login);
        facebook = findViewById(R.id.btn_facebook_login);
        user_name = findViewById(R.id.et_name_login);
        password = findViewById(R.id.et_password_login);
    }
}