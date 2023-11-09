package ub.edu.model;

import java.util.ArrayList;

public class Pelicula {

    private String titol;
    private String descripcio;
    private String url;
    private String idioma;
    private int anyPrimeraEmissio;
    private int durada;
    private float valoracio;
    private ArrayList<Tematica> llistaTematiques;

    public Pelicula(String nom, int estrena, int durada) {
        this.titol = nom;
        this.anyPrimeraEmissio = estrena;
        this.durada = durada;
        llistaTematiques = new ArrayList<Tematica>();
    }

    public Pelicula(String titol, String descripcio, String url, int estrena, String idioma, int durada, float valoracio) {
        this.titol = titol;
        this.anyPrimeraEmissio = estrena;
        this.descripcio  = descripcio;
        this.url = url;
        this.idioma = idioma;
        this.durada = durada;
        this.valoracio = valoracio;
        llistaTematiques = new ArrayList<Tematica>();
    }

    public String getTitol() {
        return titol;
    }

    public int getAnyEstrena() {
        return anyPrimeraEmissio;
    }

    public void addTematica(Tematica t) {
        this.llistaTematiques.add(t);
    }

    public ArrayList<Tematica> getTematiques() {
        return llistaTematiques;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }
}
