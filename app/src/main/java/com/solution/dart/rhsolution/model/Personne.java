package com.solution.dart.rhsolution.model;

/**
 * Created by socrates on 26/03/15.
 */
public class Personne {

    private Integer id;
    private String nom;
    private  String prenom;
    private String age;

    public Personne() {

    }

    public Personne(String nom, String prenom, String age){
          this.nom=nom;
          this.prenom=prenom;
          this.age=age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAge(String age) {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return

                "nom ='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age='" + age + '\'' +""
                ;
    }
}
