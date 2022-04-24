package com.example.kaliemie_project;

public class VisiteSoin {
    private int visite;
    private int id_categ_soins;
    private int id_type_soins;
    private int id_soins;
    private boolean prevu;
    private boolean realise;

    public VisiteSoin(){

    }
    public VisiteSoin(
            int vvisite,
            int vid_categ_soins,
            int vid_type_soins,
            int vid_soins,
            boolean vprevu,
            boolean vrealise
    )
    {
        visite=vvisite;
        id_categ_soins=vid_categ_soins;
        id_type_soins=vid_type_soins;
        id_soins=vid_soins;
        prevu=vprevu;
        realise=vrealise;
    }
    public void recopieVisiteSoin(VisiteSoin vVisiteSoin)
    {
        visite=vVisiteSoin.getVisite();
        id_categ_soins=vVisiteSoin.getId_categ_soins();
        id_type_soins=vVisiteSoin.getId_type_soins();
        id_soins=vVisiteSoin.getId_soins();
        prevu=vVisiteSoin.isPrevu();
        realise=vVisiteSoin.isRealise();
    }

    public int getVisite() {
        return visite;
    }

    public void setVisite(int visite) {
        this.visite = visite;
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

    public int getId_soins() {
        return id_soins;
    }

    public void setId_soins(int id_soins) {
        this.id_soins = id_soins;
    }

    public boolean isPrevu() {
        return prevu;
    }

    public void setPrevu(boolean prevu) {
        this.prevu = prevu;
    }

    public boolean isRealise() {
        return realise;
    }

    public void setRealise(boolean realise) {
        this.realise = realise;
    }
}
