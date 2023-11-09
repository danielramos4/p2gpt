package ub.edu.resources.service;

import ub.edu.model.*;
import ub.edu.resources.dao.*;
import ub.edu.resources.dao.entities.*;
import ub.edu.resources.dao.relationships.*;

import java.util.List;
import java.util.Optional;

public class DataService {

    // Entitats
    private DAOClient daoClient;
    private DAOPelicula daoPelicula;
    private DAOSerie daoSerie;
    private DAOTemporada daoTemporada;
    private DAOEpisodi daoEpisodi;
    private DAOTematica daoTematica;

    // Relacions
    private DAORelacioTematicaPelicula daoRelacioTematicaPelicula;

    public DataService(AbstractFactoryData factory) {

        // Entitats
        this.daoEpisodi = factory.createDAOEpisodi();
        this.daoPelicula = factory.createDAOPelicula();
        this.daoClient = factory.createDAOClient();
        this.daoSerie = factory.createDAOSerie();
        this.daoTematica = factory.createDAOTematica();
        this.daoTemporada = factory.createDAOTemporada();

        // Relacions
        this.daoRelacioTematicaPelicula = factory.createDAORelacioTematicaPelicula();
        // TO DO: Crear els altres DAO de les altres estructures
    }


    public List<Pelicula> getAllPelicules() throws Exception {
        List<Pelicula> pelicules = daoPelicula.getAll();
        List<Tematica> tematiques = daoTematica.getAll();
        List<Parell<String, String>> relacions = daoRelacioTematicaPelicula.getAll();
        for (Parell<String, String> relacio: relacions){
            for (Pelicula pelicula: pelicules) {
                if (pelicula.getTitol().equals(relacio.getElement1())) {
                    for (Tematica tematica: tematiques) {
                        if (tematica.getNomTematica().equals(relacio.getElement2())) {
                            pelicula.addTematica(tematica);
                        }
                    }
                }
            }
        }
        return pelicules;
    }

    public List<Tematica> getAllTematiques() throws Exception {
        return daoTematica.getAll();
    }

    public List<Client> getAllPersones() throws Exception {
        return daoClient.getAll();
    }

    public List<Serie> getAllSeries() throws Exception {
        List<Serie> series = daoSerie.getAll();
        List<Temporada> temporades = daoTemporada.getAll();
        List<Episodi> episodis = daoEpisodi.getAll();
        for (Temporada temporada: temporades) {
            for (Episodi episodi: episodis) {
                if (temporada.getNomSerie().equals(episodi.getNomSerie()) && episodi.getNumTemporada() == (temporada.getNumTemporada())) {
                    temporada.addEpisodi(episodi);
                }
            }
            for (Serie serie: series) {
                if (serie.getTitol().equals(temporada.getNomSerie())) {
                    serie.addTemporada(temporada);
                }
            }
        }
        return series;
    }


    public Optional<Client> getClientByUsername(String usuari) {
        try {
            return daoClient.getById(new String[]{usuari});
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<Parell<String, String>> getAllRelacionsPeliculesTematiques() {
        try {
            return daoRelacioTematicaPelicula.getAll();
        } catch (Exception e) {
            return null;
        }
    }
}
