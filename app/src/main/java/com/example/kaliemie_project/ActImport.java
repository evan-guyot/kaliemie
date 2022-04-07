package com.example.kaliemie_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class ActImport extends AppCompatActivity {


    private Boolean permissionOverlayOk = false;


    private String[] mesparams;
    private Async mThreadCon = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_import);

        Bundle b = getIntent().getExtras();
        permissionOverlayOk = b.getBoolean("permOverlayOk");



        String url = "https://www.btssio-carcouet.fr/ppe4/public/mesvisites/3";


        mesparams = new String[3];
        mesparams[0] = "2";
        mesparams[1] = url;
        mesparams[2] = "GET";
        Async mThreadCon = new Async(this);



        Button importbtn = (Button) findViewById(R.id.importbtn);
        importbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mThreadCon.execute(mesparams);
                finish();
            }
        });
    }

    public void alertmsg(String title, String msg) {
        if (permissionOverlayOk) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setMessage(msg)
                    .setTitle(title);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                }
            });

            AlertDialog dialog = builder.create();
            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
            dialog.show();
        }
        else {
            Toast.makeText(getApplicationContext(), title.concat(" -->").concat(msg), Toast.LENGTH_SHORT).show();
        }
    }




    public void retourImport (StringBuilder sb)
    {alertmsg("Titre",sb.toString());}

}