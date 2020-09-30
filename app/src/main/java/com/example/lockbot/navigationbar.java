package com.example.lockbot;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class navigationbar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private Toolbar toolbarX;
    ImageView menuIcon;
    LinearLayout contentView;
    private View textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigationbar);

        toolbarX = findViewById(R.id.toolbarX);
        setSupportActionBar(toolbarX);

        //Menu Hooks
       // drawerLayout = findViewById(R.id.drawer_layout);
        //navigationView = findViewById(R.id.navigation_view);
        //toolbar = findViewById(R.id.toolbarX);
        //  menuIcon = findViewById(R.id.menu_icon);
      //  contentView = findViewById(R.id.content);

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_view);
        //textView=findViewById(R.id.textView);
        toolbarX=findViewById(R.id.toolbar);

        setSupportActionBar(toolbarX);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbarX, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        navigationView.setNavigationItemSelectedListener(this);

    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:

            case R.id.nav_bus:
                break;
        }

        return true;
    }
}