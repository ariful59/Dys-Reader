package com.example.rumi.dys_reader;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {


    ImageButton img1, img2, img3, img4;
    EditText edit1, edit2, edit3, edit4;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        Button but=(Button)findViewById(R.id.sweep);
        but.setOnClickListener(this);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sweep) {
            Intent intent = new Intent(this , PDFSHOWER.class);
            startActivity(intent);
            //Toast.makeText(this,"Hello Javatpoint",Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == R.id.action_settings) {
            Intent i = new Intent(this, Setting.class);
            startActivity(i);
            return true;
        }
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_update) {
            Toast.makeText(this, "Blank", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_search) {
            Toast.makeText(this, "Blank", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_setting) {
            Toast.makeText(this, "Blank", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_share) {
            Toast.makeText(this, "Blank", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_send) {
            Toast.makeText(this, "Blank", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_credits) {
            Toast.makeText(this, "Blank", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
