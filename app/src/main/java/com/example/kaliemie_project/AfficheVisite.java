package com.example.kaliemie_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.db4o.marshall.Context;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Calendar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Date;



public class AfficheVisite extends AppCompatActivity {

    private ListView listView;
    private Date ddatereelle;
    private List<VisiteSoin> listeSoin;
    private Visite laVisite;
    private Modele vmodel;
    private Calendar myCalendar = Calendar.getInstance();
    private EditText datereelle;
    private EditText commentaire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_visite);


        commentaire = (EditText) findViewById(R.id.visitecommentaire);


        Bundle bundle = getIntent().getExtras();
        Integer vid = bundle.getInt("vid");

        vmodel=new Modele(this);

        // récupération de l'objet Visite
        laVisite = vmodel.trouveVisite(vid);

        TextView tvDatePrevue = (TextView) findViewById(R.id.visiteDatePrevue);
        tvDatePrevue.setText(laVisite.getDate_prevue().toString());

        // Remplissage des textView en fonction des données du patient
        Patient lePatient = vmodel.trouvePatient(laVisite.getPatient());
        TextView tvNom = (TextView) findViewById(R.id.visiteNom);
        tvNom.setText(lePatient.getNom());
        TextView tvPrenom = (TextView) findViewById(R.id.visitePrenom);
        tvPrenom.setText(lePatient.getPrenom());
        TextView tvAd1 = (TextView) findViewById(R.id.visitead1);
        tvAd1.setText(lePatient.getAd1());
        TextView tvCp = (TextView) findViewById(R.id.visitecp);
        tvCp.setText(String.valueOf(lePatient.getCp()));
        TextView tvVille = (TextView) findViewById(R.id.visiteville);
        tvVille.setText(lePatient.getVille());
        TextView tvNumport = (TextView) findViewById(R.id.visitenumport);
        tvNumport.setText(String.valueOf(lePatient.getTel_port()));
        TextView tvNumfixe = (TextView) findViewById(R.id.visitenumfixe);
        tvNumfixe.setText(String.valueOf(lePatient.getTel_fixe()));


        // récupération des soins de la visite
        listeSoin = vmodel.trouveSoinsUneVisite(laVisite.getId());
        Log.d("Soins", "trouveSoinsUneVisite" + String.valueOf(listeSoin.size()));
        listView = (ListView)findViewById(R.id.lvListeSoins);

        SoinAdapter soinAdapter = new SoinAdapter(this, listeSoin);
        listView.setAdapter(soinAdapter);



        // récupération du commentaire, si'l existe
        if(laVisite.getCompte_rendu_infirmiere() != ""){
            commentaire.setText(laVisite.getCompte_rendu_infirmiere());
        }


        datereelle=(EditText) findViewById(R.id.visiteDateReelle);
        if(laVisite.getDate_reelle().toString().length()==0)
        {
            ddatereelle = new Date();
        }
        else
        {
            ddatereelle=laVisite.getDate_reelle();
        }

        android.text.format.DateFormat df = new android.text.format.DateFormat();
        datereelle.setText(df.format("dd/MM/yyyy", ddatereelle));

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                datereelle.setText(df.format("dd/MM/yyyy", myCalendar.getTime()));
                ddatereelle=myCalendar.getTime();
            }

        };
        datereelle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AfficheVisite.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        Button save = (Button) findViewById(R.id.visitesave);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                laVisite.setDate_reelle(myCalendar.getTime());


                laVisite.setCompte_rendu_infirmiere(commentaire.getText().toString());

                vmodel.saveVisite(laVisite);

            }
        });




    }
}