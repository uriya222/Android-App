package com.example.lecturerrating;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    private Button register,login,main_page, to_profile;
    public static final String PROFILE_KEY_MAIN = "profile";
    private PersonalProfile p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        initViews();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Main Activity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        main_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LecturerListActivity.class);
                startActivity(intent);
            }
        });
        initProfile();

    }

    private void initProfile() {
        p = Utils.getInstance(this).getProfile();
        Intent intent;
        if(p==null){
            //Toast.makeText(this, "click on register", Toast.LENGTH_SHORT).show();
            intent = new Intent(MainActivity.this, RegisterActivity.class);

        }
        else {
            intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("source","MainActivity");
        }
        startActivity(intent);
    }

    private void initViews() {
        register = findViewById(R.id.btn_register_main);
        login = findViewById(R.id.btn_login_main);
        main_page = findViewById(R.id.btn_main_page);
        to_profile = findViewById(R.id.btn_toProfile_main);
    }
}