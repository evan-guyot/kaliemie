package com.example.kaliemie_project;

import java.util.Date;

public class Visite {
    /*
     * Données ne pouvant être modifiées
     */
    private int id,patient,infirmiere,duree ;
    private Date date_prevue;
    private String compte_rendu_patient;
    /*
     * Données à saisir
     */
    private Date date_reelle;
    private String compte_rendu_infirmiere;

    public Visite(int vid,int vpatient,int vinfirmiere,Date vdate_prevue ,int vduree,String vcompte_rendu_patient) {
        id=vid;
        patient=vpatient;
        infirmiere=vinfirmiere;
        date_prevue=vdate_prevue;
        duree=vduree;
        compte_rendu_patient=vcompte_rendu_patient;
        date_reelle=vdate_prevue;
        compte_rendu_infirmiere="";
    }


    public int getId() {
        return id;
    }

    public int getPatient() {
        return patient;
    }

    public int getInfirmiere() {
        return infirmiere;
    }

    public int getDuree() {
        return duree;
    }

    public Date getDate_prevue() {
        return date_prevue;
    }

    public String getCompte_rendu_patient() {
        return compte_rendu_patient;
    }

    public Date getDate_reelle() {
        return date_reelle;
    }

    public String getCompte_rendu_infirmiere() {
        return compte_rendu_infirmiere;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPatient(int patient) {
        this.patient = patient;
    }

    public void setInfirmiere(int infirmiere) {
        this.infirmiere = infirmiere;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setDate_prevue(Date date_prevue) {
        this.date_prevue = date_prevue;
    }

    public void setCompte_rendu_patient(String compte_rendu_patient) {
        this.compte_rendu_patient = compte_rendu_patient;
    }

    public void setDate_reelle(Date date_reelle) {
        this.date_reelle = date_reelle;
    }

    public void setCompte_rendu_infirmiere(String compte_rendu_infirmiere) {
        this.compte_rendu_infirmiere = compte_rendu_infirmiere;
    }

    public void recopieVisite(Visite visite)
    {
        id=visite.getId();
        patient=visite.getPatient();
        infirmiere=visite.getInfirmiere();
        date_prevue=visite.getDate_prevue();
        duree=visite.getDuree();
        compte_rendu_patient=visite.getCompte_rendu_patient();
        date_reelle=visite.getDate_reelle();
        compte_rendu_infirmiere=visite.getCompte_rendu_infirmiere();
    }


}

