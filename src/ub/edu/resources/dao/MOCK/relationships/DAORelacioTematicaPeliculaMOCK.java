package ub.edu.resources.dao.MOCK.relationships;

import ub.edu.resources.dao.Parell;
import ub.edu.resources.dao.relationships.DAORelacioTematicaPelicula;

public class DAORelacioTematicaPeliculaMOCK extends DAORelacioMOCK<Parell<String, String>> implements DAORelacioTematicaPelicula{
    public DAORelacioTematicaPeliculaMOCK() {
        addTematicaPelicula("Sci-Fi", "Avatar");
        addTematicaPelicula("Action", "Inception");
        addTematicaPelicula("Sci-Fi", "Inception");
        addTematicaPelicula("Adventure", "Inception");
        addTematicaPelicula("Adventure", "Interstellar");
        addTematicaPelicula("Drama", "Interstellar");
        addTematicaPelicula("Sci-Fi", "Interstellar");
        addTematicaPelicula("Sci-Fi", "The Matrix");
        addTematicaPelicula("Action", "The Matrix");
        addTematicaPelicula("Drama", "Shawshank Redemption");
    }

    private boolean addTematicaPelicula(String nomTematica, String nomPelicula) {
        return relacions.add(new Parell<>(nomTematica, nomPelicula));
    }
}
