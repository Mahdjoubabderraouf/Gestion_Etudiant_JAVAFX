package com.projectbdd.projectbdd;

public class Etudiant {
    private final String matricule_etu;
    private final String nom_etu;
    private final String prenom_etu;
    private final String date;
    private final String adresse;


    public Etudiant(String matriculeEtu, String nomEtu, String prenomEtu, String date, String adresse) {
        matricule_etu = matriculeEtu;
        nom_etu = nomEtu;
        prenom_etu = prenomEtu;
        this.date = date;
        this.adresse = adresse;
    }
    public Etudiant(String nomEtu, String prenomEtu) {
        matricule_etu = "";
        nom_etu = nomEtu;
        prenom_etu = prenomEtu;
        this.date = "";
        this.adresse = "";
    }

    public String getAdresse() {
        return adresse;
    }

    public String getDate() {
        return date;
    }

    public String getMatricule_etu() {
        return matricule_etu;
    }

    public String getNom_etu() {
        return nom_etu;
    }

    public String getPrenom_etu() {
        return prenom_etu;
    }
}
