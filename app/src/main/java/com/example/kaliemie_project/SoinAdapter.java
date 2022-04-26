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

public class SoinAdapter extends BaseAdapter{

    private ViewHolder holder;
    private List<VisiteSoin> listSoin;
    private LayoutInflater layoutInflater; //Cet attribut a pour mission de charger notre fichier XML de la vue pour l'item.
    private DateFormat df = new DateFormat();
    private Modele vmodel;


    public SoinAdapter(Context context, List<VisiteSoin> vListSoin) {
        super();
        layoutInflater = LayoutInflater.from(context);
        listSoin = vListSoin;
        vmodel=new Modele(context);

    }


    private class ViewHolder {
        public View checkRealise;
        TextView textViewVisite;
        TextView textViewPatient;
        TextView textViewDate;
        TextView textViewDuree;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        holder.checkRealise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb =  v.findViewById(R.id.vuesoinsrealise);
                listSoin.get(position).setRealise(cb.isChecked());
                vmodel.saveVisiteSoin(listSoin.get(position));
            }
        });


        return null;
    }


}
