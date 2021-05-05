package com.example.locksafe;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;


public class Home extends AppCompatActivity implements OnNavigationItemSelectedListener {

  private DrawerLayout drawerlayout;
  private NavigationView navigationView;
  private ScrollView scroll;
  private Toolbar toolBar;
  private Menu menu;
  private AppCompatButton passencrypt_btn;
  private AppCompatButton encryptimg_btn;
  private AppCompatButton logout_btn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
    navigationView = (NavigationView) findViewById(R.id.nav_view);
    toolBar = (Toolbar) findViewById(R.id.tool_bar);
    scroll = (ScrollView) findViewById(R.id.scroll);
    passencrypt_btn = (AppCompatButton) findViewById(R.id.passencrypt_btn);
    logout_btn = (AppCompatButton) findViewById(R.id.logout_btn);



    setSupportActionBar(toolBar);

    navigationView.bringToFront();
    ActionBarDrawerToggle tog = new ActionBarDrawerToggle(this, drawerlayout, toolBar,
        R.string.navigation_open, R.string.navigation_close);
    drawerlayout.addDrawerListener(tog);
    tog.syncState();
    navigationView.setNavigationItemSelectedListener(this);
    navigationView.setCheckedItem(R.id.home_nav);

    passencrypt_btn.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), Encrypt.class);
        v.getContext().startActivity(intent);
      }
    });

    logout_btn.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(v.getContext(),Login.class);
        v.getContext().startActivity(intent);
      }
    });

  }




  @Override
  public void onBackPressed() {

    if (drawerlayout.isDrawerOpen(GravityCompat.START)) {
      drawerlayout.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.home_nav:
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        break;
      case R.id.password_nav:
        Intent intent2 = new Intent(Home.this, Encrypt.class);
        startActivity(intent2);
        break;
      case R.id.rate_nav:
        Intent intents3 = new Intent(Home.this, Rating.class);
        startActivity(intents3);
        break;
        case R.id.logout_nav:
        Intent intents4 = new Intent(Home.this, Login.class);
        startActivity(intents4);
        break;


    }
        drawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }
  }


