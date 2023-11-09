package ub.edu.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Serie {
    private String titol;
    private String descripcio;
    private String imatge;
    private int anyPrimeraEmissio;

    private Set<Temporada> temporades;


    public Serie(String nomSerie, int anyEstrena) {
        this.titol = nomSerie;
        this.anyPrimeraEmissio = anyEstrena;
        temporades = new HashSet<>();
    }

    public Serie(String nomSerie, String descripcio, String url, int anyEstrena, String idioma) {
        this.titol = nomSerie;
        this.descripcio = descripcio;
        this.imatge = url;
        this.anyPrimeraEmissio = anyEstrena;
        temporades = new HashSet<>();
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol){
        this.titol = titol;
    }

    public void addTemporada(Temporada temporada){
        temporades.add(temporada);
    }

    public List<Temporada> getTemporades() { return new ArrayList<Temporada>(temporades);}



}