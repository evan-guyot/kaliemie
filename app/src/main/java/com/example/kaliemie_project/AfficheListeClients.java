package com.example.kaliemie_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class AfficheListeClients extends AppCompatActivity {


    private ListView listView;
    private List<Patient> listeClients;
    private Modele vmodele;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_liste_clients);

        vmodele=new Modele(this);
        listeClients = vmodele.listePatient();
        listView = (ListView)findViewById(R.id.lvListeClients);
        PatientAdapter patientAdapter = new PatientAdapter(this, listeClients);
        listView.setAdapter(patientAdapter);



    }
}