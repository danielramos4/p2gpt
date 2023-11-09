package ub.edu.resources.service;

import ub.edu.resources.dao.entities.*;
import ub.edu.resources.dao.relationships.*;

public interface AbstractFactoryData {

    // Entitats
    DAOClient createDAOClient();
    DAOEpisodi createDAOEpisodi();
    DAOTemporada createDAOTemporada();
    DAOPelicula createDAOPelicula();
    DAOSerie createDAOSerie();
    DAOTematica createDAOTematica();

    // Relacions
    DAORelacioTematicaPelicula createDAORelacioTematicaPelicula();

    //Cal afegir les creacions de les altres classes DAO que es necessitin
}
