package com.example.app_food_ktck;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.example.app_food_ktck.ui.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_food_ktck.databinding.ActivityMainBinding;

import fragment.food;
import fragment.home;
import fragment.profile;

public class MainActivity extends AppCompatActivity {

    private static final int FRAGMENT_HOME2 = 1;
    private static final int FRAGMENT_FOOD = 2;
    private static final int FRAGMENT_PROFILE = 3;
    private int currentFagment = FRAGMENT_HOME2;

    private NavigationView mnavigationView;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        mnavigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_food, R.id.nav_profile)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.content_fagment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(mnavigationView, navController);

        replaceFragment(new HomeFragment());
        mnavigationView.setCheckedItem(R.id.nav_home);
        setTitleToobar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onNavigationItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.nav_home){
            openHomeFragment();
        }else  if(id == R.id.nav_food){
            openfoodFragment();
        }else  if(id == R.id.nav_profile){
            openprofileFragment();
        }
        setTitleToobar();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.content_fagment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void openHomeFragment(){
        if(currentFagment != FRAGMENT_HOME2){
            replaceFragment(new home());
            currentFagment = FRAGMENT_HOME2;
        }

    }
    private void openfoodFragment(){
        if(currentFagment != FRAGMENT_FOOD){
            replaceFragment(new food());
            currentFagment = FRAGMENT_FOOD;
        }

    }
    private void openprofileFragment(){
        if(currentFagment != FRAGMENT_PROFILE){
            replaceFragment(new profile());
            currentFagment = FRAGMENT_PROFILE;
        }

    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_fagment,fragment);
        transaction.commit();
    }

    private void setTitleToobar(){
        String title="";
        switch (currentFagment){
            case FRAGMENT_HOME2:
                title= getString(R.string.menu_home);
                break;
            case FRAGMENT_FOOD:
                title= getString(R.string.menu_food);
                break;
            case FRAGMENT_PROFILE:
                title= getString(R.string.menu_profile);
                break;
        }
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);
        }
    }

}