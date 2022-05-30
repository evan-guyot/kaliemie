package com.example.kaliemie_project;



import android.content.Context;
import android.graphics.Color;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

public class PatientAdapter extends BaseAdapter{

    private ViewHolder holder;
    private List<Patient> listPatient;
    private LayoutInflater layoutInflater; //Cet attribut a pour mission de charger notre fichier XML de la vue pour l'item.
    private DateFormat df = new DateFormat();
    private Modele vmodel;


    public PatientAdapter(Context context, List<Patient> vListPatient) {
        super();
        layoutInflater = LayoutInflater.from(context);
        listPatient = vListPatient;
        vmodel=new Modele(context);

    }


    private class ViewHolder {
      /*  public CheckBox checkRealise;*/
        TextView TextViewPatient;
    }



    @Override
    public int getCount() {
        return listPatient.size();
    }

    @Override
    public Object getItem(int position) {
        return listPatient.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listPatient.get(position).getId();
    }



    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.layout_vuepatient, null);
            holder.TextViewPatient = (TextView) view.findViewById(R.id.vuepatient);
            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }

        Patient patients = vmodel.trouvePatient(listPatient.get(position).getId());


        holder.TextViewPatient.setText(patients.getPrenom() +" "+ patients.getNom());



        return view;
    }



}
