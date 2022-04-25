package com.example.kaliemie_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonParseException;

import java.sql.Date;
import java.util.ArrayList;


public class ActImport extends AppCompatActivity {


    private Boolean permissionOverlayOk = false;
    private boolean soinsAjoute = false;

    private String[] mesparams;
    private String[] mesparamsSoin;
    private String[] mesparamsPatient;
    private String[] mesparamsVisite;
    private Async mThreadCon = null;
    private Async mThreadSoin = null;
    private Async mThreadPatient = null;
    private Async mThreadVisite = null;

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
/*
    public void retourImport(StringBuilder sb)
    {
        try {
            Modele vmodel = new Modele(this);
            JsonElement json = new JsonParser().parse(sb.toString());
            JsonArray varray = json.getAsJsonArray();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            ArrayList<Visite> listeVisite = new ArrayList<Visite>();
            ArrayList<Integer> lesPatients = new ArrayList<Integer>();


            for (JsonElement obj : varray) {
                Visite visite = gson.fromJson(obj.getAsJsonObject(), Visite.class);
                visite.setCompte_rendu_infirmiere("");
                visite.setDate_reelle(visite.getDate_prevue());
                listeVisite.add(visite);


                //chargement des soins
                if(soinsAjoute){


                    String patient_url = "https://www.btssio-carcouet.fr/ppe4/public/soins/";

                    mesparams = new String[3];
                    mesparams[0] = "3";
                    mesparams[1] = patient_url;
                    mesparams[2] = "GET";

                    Async mThreadSoin = new Async(this);
                    mThreadSoin.execute(mesparams);
                    soinsAjoute = true;
                }


                //pour chaque patient
                if (!lesPatients.contains(visite.getPatient())){
                    lesPatients.add(visite.getPatient());

                    String patient_url = "https://www.btssio-carcouet.fr/ppe4/public/personne/".concat(Integer.toString(visite.getPatient()));

                    mesparams = new String[3];
                    mesparams[0] = "3";
                    mesparams[1] = patient_url;
                    mesparams[2] = "GET";

                    Async mThreadPatient = new Async(this);
                    mThreadPatient.execute(mesparams);
                }

                //pour chaque visite
                String visite_url = "https://www.btssio-carcouet.fr/ppe4/public/visitesoins/".concat(Integer.toString(visite.getId()));

                mesparams = new String[3];
                mesparams[0] = "4";
                mesparams[1] = visite_url;
                mesparams[2] = "GET";


                Async mThreadVisite = new Async(this);
                mThreadVisite.execute(mesparams);

            }
            vmodel.deleteVisite();
            vmodel.addVisite(listeVisite);
            alertmsg("Retour", "Vos informations ont bien été importé avec succès !");
        }
        catch (Exception e) {
            alertmsg("Erreur retour import", e.getMessage());
        }
    }

    public void retourImportPatient(StringBuilder sb){

        try {
            Modele vmodel = new Modele(this);
            JsonElement json = new JsonParser().parse(sb.toString());
            JsonArray varray = json.getAsJsonArray();

            Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).serializeNulls().create();

            ArrayList<Patient> listePatient = new ArrayList<Patient>();

            for (JsonElement obj : varray) {
                Patient patient = gson.fromJson(obj.getAsJsonObject(), Patient.class);
                listePatient.add(patient);
            }

            alertmsg("Retour", "Vos informations ont bien été importé avec succès !");
            alertmsg("Retour", "Yessss !");
        }
        catch (JsonParseException e) {
            Log.d("Patient", "erreur json" + e.getMessage());

        }
    }

    public void retourImportSoinsVisite(StringBuilder sb){

        try {
            Modele vmodel = new Modele(this);
            JsonElement json = new JsonParser().parse(sb.toString());
            JsonArray varray = json.getAsJsonArray();

            Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd HH:mm:ss").serializeNulls().create();

            ArrayList<VisiteSoin> listeVisiteSoin = new ArrayList<VisiteSoin>();

            for (JsonElement obj : varray) {
                VisiteSoin visiteSoin = gson.fromJson(obj.getAsJsonObject(), VisiteSoin.class);
                listeVisiteSoin.add(visiteSoin);
            }

            alertmsg("Retour", "Vos informations ont bien été importé avec succès !");
            alertmsg("Retour", "Yessss !");
        }
        catch (JsonParseException e) {
            Log.d("VisiteSoin", "erreur json" + e.getMessage());

        }
    }

    public void retourImportSoins(StringBuilder sb){

        try {
            Modele vmodel = new Modele(this);
            JsonElement json = new JsonParser().parse(sb.toString());
            JsonArray varray = json.getAsJsonArray();

            Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd HH:mm:ss").serializeNulls().create();

            ArrayList<VisiteSoin> listeVisiteSoin = new ArrayList<VisiteSoin>();

            for (JsonElement obj : varray) {
                VisiteSoin visiteSoin = gson.fromJson(obj.getAsJsonObject(), VisiteSoin.class);
                listeVisiteSoin.add(visiteSoin);
            }

            alertmsg("Retour", "Vos informations ont bien été importé avec succès !");
            alertmsg("Retour", "Yessss !");
        }
        catch (JsonParseException e) {
            Log.d("Soin", "erreur json" + e.getMessage());

        }
    }*/




    public void retourImport(StringBuilder sb)
    {
        //alertmsg("retour Import", sb.toString());

        try {
            Modele vmodel = new Modele(this);
            JsonElement json = new JsonParser().parse(sb.toString());
            JsonArray varray = json.getAsJsonArray();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            ArrayList<Visite> listeVisite = new ArrayList<Visite>();
            ArrayList<Integer> lesPatients = new ArrayList<Integer>();
            vmodel.deletePatient();
            vmodel.deleteVisite();
            vmodel.deleteVisiteSoin();
            for (JsonElement obj : varray) {
                Visite visite = gson.fromJson(obj.getAsJsonObject(), Visite.class);
                visite.setCompte_rendu_infirmiere("");
                visite.setDate_reelle(visite.getDate_prevue());
                Integer p = visite.getPatient();
                Integer v = visite.getId();
                if(!lesPatients.contains(p)) {
                    lesPatients.add(p);
                }
                listeVisite.add(visite);

            }
            vmodel.addVisite(listeVisite);
            for (Integer lint:lesPatients
            ) {


                String urlImpPatient = "https://www.btssio-carcouet.fr/ppe4/public/personne/"
                        .concat(lint.toString());
                mesparamsPatient = new String[3];
                mesparamsPatient[0] = "3";
                mesparamsPatient[1] = urlImpPatient;
                mesparamsPatient[2] = "GET";
                mThreadPatient = new Async(this);
                mThreadPatient.execute(mesparamsPatient);
            }
            for (Visite v:listeVisite
            ) {
                String urlImpVisiteSoins = "https://www.btssio-carcouet.fr/ppe4/public/visitesoins/"
                        .concat(String.valueOf(v.getId()));
                mesparamsVisite = new String[3];
                mesparamsVisite[0] = "4";
                mesparamsVisite[1] = urlImpVisiteSoins;
                mesparamsVisite[2] = "GET";
                mThreadVisite = new Async(this);
                mThreadVisite.execute(mesparamsVisite);
            }
            if(vmodel.listeSoin().size() == 0) {
                String urlImpSoins = "https://www.btssio-carcouet.fr/ppe4/public/soins/";
                mesparamsSoin = new String[3];
                mesparamsSoin[0] = "5";
                mesparamsSoin[1] = urlImpSoins;
                mesparamsSoin[2] = "GET";
                mThreadSoin = new Async(this);
                mThreadSoin.execute(mesparamsSoin);
            }
            //mThreadImpPatients.execute(mesparamsP);
            //mThreadImpPatients.execute(mesparamsVS);
            //mThreadImpPatients.execute(mesparamsS);



            alertmsg("Retour", "Vos informations ont bien été importé avec succès !");
            alertmsg("retour Import", sb.toString());
        }
        catch (Exception e) {
            alertmsg("Erreur retour import", e.getMessage());
        }
    }

    public void retourImportPatient(StringBuilder sb) {
        //alertmsg("retour ImportPatient", sb.toString());
        try {
            Modele vmodel = new Modele(this);
            JsonElement json = new JsonParser().parse(sb.toString());
            JsonArray varray = json.getAsJsonArray();
            Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).serializeNulls().create();
            ArrayList<Patient> listePatient = new ArrayList<Patient>();
            for (JsonElement obj : varray) {
                Patient unPatient = gson.fromJson(obj.getAsJsonObject(), Patient.class);
                listePatient.add(unPatient);
            }

            vmodel.addPatient(listePatient);
            //alertmsg("Retour Patient", "Les informations du patient ont bien été importé !");
            //alertmsg("retour Import Patient", sb.toString());
        } catch (JsonParseException e) {
            Log.d("Patient", "erreur json" + e.getMessage());
        }
    }

    public void retourImportSoinsVisite(StringBuilder sb) {
        //alertmsg("retour ImportPatient", sb.toString());
        try {
            Modele vmodel = new Modele(this);
            JsonElement json = new JsonParser().parse(sb.toString());
            JsonArray varray = json.getAsJsonArray();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd HH:mm:ss").serializeNulls().create();
            ArrayList<VisiteSoin> listeVisiteSoins = new ArrayList<VisiteSoin>();
            for (JsonElement obj : varray) {
                VisiteSoin uneVisiteSoin = gson.fromJson(obj.getAsJsonObject(), VisiteSoin.class);
                listeVisiteSoins.add(uneVisiteSoin);
            }
            vmodel.addVisiteSoin(listeVisiteSoins);
            //alertmsg("Retour", "Les informations visite soins ont bien été importé !");
            //alertmsg("retour ImportVisiteSoins", sb.toString());
        } catch (JsonParseException e) {
            Log.d("VisiteSoin", "erreur json" + e.getMessage());
        }
    }

    public void retourImportSoins(StringBuilder sb) {
        //alertmsg("retour ImportPatient", sb.toString());
        try {
            Modele vmodel = new Modele(this);
            JsonElement json = new JsonParser().parse(sb.toString());
            JsonArray varray = json.getAsJsonArray();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd HH:mm:ss").serializeNulls().create();
            ArrayList<Soin> listeSoins = new ArrayList<Soin>();
            for (JsonElement obj : varray) {
                Soin unSoin = gson.fromJson(obj.getAsJsonObject(), Soin.class);
                listeSoins.add(unSoin);
            }
            vmodel.addSoin(listeSoins);
            //alertmsg("Retour", "Les informations Soins ont bien été importé !");
            //alertmsg("retour Import", sb.toString());
        } catch (JsonParseException e) {
            Log.d("Soin", "erreur json" + e.getMessage());
        }
    }







}