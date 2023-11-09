package ub.edu.model;

public class Episodi {
    // Episodi: nomSerie, numTemporada, numEpisodi, títolEpisodi, durada, descripció, data d’estrena
    private String nomSerie;
    private int numTemporada;
    private int numEpisodi;
    private String titolEpisodi;
    private int durada;


    public Episodi(String nomSerie, int numTemporada, int numEpisodi, String titolEpisodi, int durada) {
        this.nomSerie = nomSerie;
        this.numTemporada = numTemporada;
        this.numEpisodi = numEpisodi;
        this.titolEpisodi = titolEpisodi;
        this.durada = durada;
    }

    public int getNumEpisodi() {
        return numEpisodi;
    }

    public String getTitol() {
        return titolEpisodi;
    }

    public String getNomSerie() {
        return nomSerie;
    }

    public String getTitolEpisodi() {
        return titolEpisodi;
    }

    public int getNumTemporada() {
        return numTemporada;
    }

    public void setTitolEpisodi(String nom) {
        this.titolEpisodi = nom;
    }
}
