package com.example.kaliemie_project;

import android.os.Build;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.provider.Settings;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.Manifest;
import android.content.pm.PackageManager;
import java.util.List;
import java.util.ArrayList;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import androidx.core.app.ActivityCompat;


import com.example.kaliemie_project.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private Menu lemenu;
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.INTERNET,Manifest.permission.READ_CONTACTS};
    private static final int MULTIPLE_PERMISSIONS = 10;
    private List<String> listPermissionsNeeded;
    private boolean permissionOverlayAsked=false;
    private boolean permissionOK=false;

    @Override
    public void onStart() {
        super.onStart();
        super.onResume();
        if(!permissionOverlayAsked) {
            checkPermissionAlert();
        }
        checkPermissions();
    }

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
                if(this.permissionOK) {
                    boolean firstFragActive = (Navigation.findNavController(this, R.id.nav_host_fragment_content_main).getCurrentDestination().getId() == R.id.FirstFragment);
                    if (firstFragActive) {
                        Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(R.id.action_FirstFragment_to_SecondFragment);
                    }
                }
                else {
                    if(!Settings.canDrawOverlays(this)){

                        Toast.makeText(getApplicationContext(), "Les overlays sont refusés", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                Uri.parse("package:" + getPackageName()));
                        startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
                    }

                    Toast.makeText(getApplicationContext(), "Les permisions sont refusées", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissionsList[], int[] grantResults) {
        switch (requestCode) {
            case MULTIPLE_PERMISSIONS:{
                if (grantResults.length > 0) {
                    String permissionsDenied = "";
                    int posi=0;
                    for (String per : permissionsList) {
                        if(grantResults[posi] == PackageManager.PERMISSION_DENIED){
                            permissionsDenied += "\n" + per;
                        }
                        posi++;
                    }
                    // Show permissionsDenied
                    if(permissionsDenied.length()>0) {
                        Toast.makeText(getApplicationContext(),    "Nous ne pouvons pas continuer l'application car ces permissions sont nécéssaires : \n"+permissionsDenied,Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        permissionOK=true;
                    }
                }
                return;
            }
        }
    }

    private  void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result;
            List<String> listPermissionsNeeded = new ArrayList<>();
            for (String p : permissions) {
                result = checkSelfPermission(p);
                if (result != PackageManager.PERMISSION_GRANTED) {
                    listPermissionsNeeded.add(p);
                }
            }
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), MULTIPLE_PERMISSIONS);
            } else {
                // Toutes les permissions sont ok
                permissionOK = true;
            }
        }
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

    public void alertmsg(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setMessage(msg)
                .setTitle(title);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });

        AlertDialog dialog = builder.create();
        dialog.getWindow().setType(WindowManager.LayoutParams.
                TYPE_APPLICATION_OVERLAY);
        dialog.show();
    }

    public static final int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE= 5469;

    public void checkPermissionAlert() {
        permissionOverlayAsked=true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
            }
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // on regarde quelle Activity a répondu
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode) {
            case ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (Settings.canDrawOverlays(this)) {
                        alertmsg("Permission ALERT","Permission OK");
                        return;
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Les permissions n'ont pas été acceptées "
                                , Toast.LENGTH_SHORT).show();
                    }
                }
        }

    }




}