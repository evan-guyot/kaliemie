package com.example.kaliemie_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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
    private DateFormat df = new DateFormat() {
        @NonNull
        @Override
        public StringBuffer format(@NonNull Date date, @NonNull StringBuffer stringBuffer, @NonNull FieldPosition fieldPosition) {
            return null;
        }

        @Nullable
        @Override
        public Date parse(@NonNull String s, @NonNull ParsePosition parsePosition) {
            return null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_visite);


        Bundle bundle = getIntent().getExtras();
        Integer vid = bundle.getInt("vid");

        vmodel=new Modele(this);

        laVisite = vmodel.trouveVisite(vid);



        TextView tvDatePrevue = (TextView) findViewById(R.id.visiteDatePrevue);
        tvDatePrevue.setText(laVisite.getDate_prevue().toString());


        try {
            Patient lePatient = vmodel.trouvePatient(laVisite.getPatient());



            TextView tvNom = (TextView) findViewById(R.id.visiteNom);
            tvNom.setText(lePatient.getNom());
            TextView tvPrenom = (TextView) findViewById(R.id.visitePrenom);
            tvPrenom.setText(lePatient.getPrenom());
            TextView tvAd1 = (TextView) findViewById(R.id.visitead1);
            tvAd1.setText(lePatient.getAd1());
            TextView tvCp = (TextView) findViewById(R.id.visitecp);
            tvCp.setText(lePatient.getCp());
            TextView tvVille = (TextView) findViewById(R.id.visiteville);
            tvVille.setText(lePatient.getVille());
            TextView tvNumport = (TextView) findViewById(R.id.visitenumport);
            tvNumport.setText(lePatient.getTel_port());
            TextView tvNumfixe = (TextView) findViewById(R.id.visitenumfixe);
            tvNumfixe.setText(lePatient.getTel_fixe());

        }catch (Exception e){

        }



        // remplissage des TextView
/*





        TextView tvNom = (TextView) findViewById(R.id.visiteNom);
        tvNom.setText(lePatient.getNom());
        TextView tvPrenom = (TextView) findViewById(R.id.visitePrenom);
        tvPrenom.setText(lePatient.getPrenom());
        TextView tvAd1 = (TextView) findViewById(R.id.visitead1);
        tvAd1.setText(lePatient.getAd1());
        TextView tvCp = (TextView) findViewById(R.id.visitecp);
        tvCp.setText(lePatient.getCp());
        TextView tvVille = (TextView) findViewById(R.id.visiteville);
        tvVille.setText(lePatient.getVille());
        TextView tvNumport = (TextView) findViewById(R.id.visitenumport);
        tvNumport.setText(lePatient.getTel_port());
        TextView tvNumfixe = (TextView) findViewById(R.id.visitenumfixe);
        tvNumfixe.setText(lePatient.getTel_fixe());


*/




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








    }
}