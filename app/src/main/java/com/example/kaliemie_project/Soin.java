package com.example.kaliemie_project;

import java.util.Date;
public class Soin {
    int id_categ_soins;
    int id_type_soins;
    int id;
    String libel;
    String description;
    float  coefficient;
    Date date;

    public Soin(){

    }
    public Soin(
            int vid_categ_soins,
            int vid_type_soins,
            int vid,
            String vlibel,
            String vdescription,
            float  vcoefficient,
            Date vdate)
    {
        id_categ_soins=vid_categ_soins;
        id_type_soins=vid_type_soins;
        id=vid;
        libel=vlibel;
        description=vdescription;
        coefficient=vcoefficient;
        date=vdate;
    }
    public void recopieSoin(Soin vsoin)

    {
        id_categ_soins=vsoin.getId_categ_soins();
        id_type_soins=vsoin.getId_type_soins();
        id=vsoin.getId();
        libel=vsoin.getLibel();
        description=vsoin.getDescription();
        coefficient=vsoin.getCoefficient();
        date=vsoin.getDate();
    }
    public int getId_categ_soins() {
        return id_categ_soins;
    }

    public void setId_categ_soins(int id_categ_soins) {
        this.id_categ_soins = id_categ_soins;
    }

    public int getId_type_soins() {
        return id_type_soins;
    }

    public void setId_type_soins(int id_type_soins) {
        this.id_type_soins = id_type_soins;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibel() {
        return libel;
    }

    public void setLibel(String libel) {
        this.libel = libel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
