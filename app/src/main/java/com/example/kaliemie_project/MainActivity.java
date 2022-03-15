package com.example.kaliemie_project;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.View.OnClickListener;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.kaliemie_project.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private Menu lemenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        lemenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_connect:
                boolean firstFragActive = (Navigation.findNavController(this,R.id.nav_host_fragment_content_main).getCurrentDestination().getId()==R.id.FirstFragment);
                if(firstFragActive)
                {
                    Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(R.id.action_FirstFragment_to_SecondFragment);
                }
                Toast.makeText(getApplicationContext(), "clic sur connect", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_deconnect:

                boolean thirdFragActive = (Navigation.findNavController(this,R.id.nav_host_fragment_content_main).getCurrentDestination().getId()==R.id.thirdFragment);
                if(thirdFragActive)
                {
                    Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(R.id.action_thirdFragment_to_FirstFragment);
                    menuDeconnecte();
                }

                Toast.makeText(getApplicationContext(), "clic sur deconnect", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_list:
                Toast.makeText(getApplicationContext(), "clic sur list", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_import:
                Toast.makeText(getApplicationContext(), "clic sur import", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_export:
                Toast.makeText(getApplicationContext(), "clic sur export", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void menuConnecte(){
        // Rends visible tous les items sauf : R.id.menu_connect
        lemenu.findItem(R.id.menu_connect).setVisible(false);
        lemenu.findItem(R.id.menu_deconnect).setVisible(true);
        lemenu.findItem(R.id.menu_export).setVisible(true);
        lemenu.findItem(R.id.menu_import).setVisible(true);
        lemenu.findItem(R.id.menu_list).setVisible(true);
    }

    public void menuDeconnecte(){
        // Rends non-visible tous les items sauf : R.id.menu_connect
        lemenu.findItem(R.id.menu_connect).setVisible(true);
        lemenu.findItem(R.id.menu_deconnect).setVisible(false);
        lemenu.findItem(R.id.menu_export).setVisible(false);
        lemenu.findItem(R.id.menu_import).setVisible(false);
        lemenu.findItem(R.id.menu_list).setVisible(false);
    }



}