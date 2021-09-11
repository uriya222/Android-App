package com.example.lecturerrating;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.navigation.NavigationView;

public class LecturerListActivity extends AppCompatActivity {

    private NavigationView menu;
    private DrawerLayout d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_list);
        menu = findViewById(R.id.navigation);

         d = findViewById(R.id.drawerLayout);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(d.isDrawerOpen(GravityCompat.START)){
                    d.closeDrawer(GravityCompat.START);
                }
                else {
                    d.openDrawer(GravityCompat.START);
                }
            }
        });
    }
}