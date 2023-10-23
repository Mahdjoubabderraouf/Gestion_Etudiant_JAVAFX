package com.projectbdd.projectbdd;

import javafx.beans.property.SimpleStringProperty;

public class Enseignant {
    private final String nom_ens;
    private final String prenom_ens;
    private final String matricule;

    public Enseignant(String nom, String prenom, String mat){
        nom_ens = nom;
        prenom_ens = prenom;
        matricule = mat;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getNom_ens() {
        return nom_ens;
    }

    public String getPrenom_ens() {
        return prenom_ens;
    }

}
