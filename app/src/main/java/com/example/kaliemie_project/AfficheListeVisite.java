package com.example.kaliemie_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import java.util.List;


public class AfficheListeVisite extends AppCompatActivity {

    private ListView listView;
    private List<Visite> listeVisite;
    private Modele vmodele;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_liste_visite);

        vmodele=new Modele(this);
        listeVisite = vmodele.listeVisite();
        listView = (ListView)findViewById(R.id.lvListe);
        VisiteAdapter visiteAdapter = new VisiteAdapter(this, listeVisite);
        listView.setAdapter(visiteAdapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Toast.makeText(getApplicationContext(),"Choix : "+listeVisite.get(position).getId(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), AfficheVisite.class);
                Bundle bundle = new Bundle();
                intent.putExtra("vid", listeVisite.get(position).getId());
                intent.putExtra("vpatient", listeVisite.get(position).getPatient());
                intent.putExtra("vinfirmiere", listeVisite.get(position).getInfirmiere());
                intent.putExtra("vdate_prevue_string", listeVisite.get(position).getDate_prevue().toString());
                intent.putExtra("vduree", listeVisite.get(position).getDuree());
                intent.putExtra("vcompte_rendu_patient", listeVisite.get(position).getCompte_rendu_patient());
                intent.putExtras(bundle);
                startActivity(intent);





            }
        });

    }
}