package ub.edu.resources.service;

import ub.edu.resources.dao.MOCK.entities.*;
import ub.edu.resources.dao.MOCK.relationships.DAORelacioTematicaPeliculaMOCK;
import ub.edu.resources.dao.entities.*;
import ub.edu.resources.dao.relationships.*;

public class FactoryMOCK implements AbstractFactoryData {

    // Entitats

    @Override
    public DAOClient createDAOClient() {
        return new DAOClientMOCK();
    }

    @Override
    public DAOEpisodi createDAOEpisodi() {
        return new DAOEpisodiMOCK();
    }

    @Override
    public DAOTemporada createDAOTemporada() {
        return new DAOTemporadaMOCK();
    }

    @Override
    public DAOPelicula createDAOPelicula() {
        return new DAOPeliculaMOCK();
    }

    @Override
    public DAOSerie createDAOSerie() {
        return new DAOSerieMOCK();
    }

    @Override
    public DAOTematica createDAOTematica() {
        return new DAOTematicaMOCK();
    }

    // Relacions
    @Override
    public DAORelacioTematicaPelicula createDAORelacioTematicaPelicula() {
        return new DAORelacioTematicaPeliculaMOCK();
    }


    // TO DO Pràctica 2:  Crear els altres DAOs de les altres classes
}
