package ub.edu.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Temporada {
    private int numTemporada;
    private String nomSerie;

    private ArrayList<Episodi> episodis;

    public Temporada(String nomSerie, int i) {
        this.nomSerie = nomSerie;
        this.numTemporada = i;
        episodis = new ArrayList<Episodi>();
    }

    public String getNomSerie() {
        return nomSerie;
    }

    public void setNomSerie(String nomSerie) {
        this.nomSerie = nomSerie;
    }
    
    public void addEpisodi(Episodi episodi){
        episodis.add(episodi);
    }

    public int getNumTemporada() {
        return numTemporada;
    }

    public List<Episodi> getEpisodis() {
        return new ArrayList<Episodi>(episodis);
    }
}
