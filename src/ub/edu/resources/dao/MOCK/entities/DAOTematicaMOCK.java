package ub.edu.resources.dao.MOCK.entities;

import ub.edu.model.Tematica;
import ub.edu.resources.dao.entities.DAOTematica;

import java.util.*;

public class DAOTematicaMOCK implements DAOTematica {

    private Map<String, Tematica> tematiques = new HashMap<>();

    public DAOTematicaMOCK(){
        addTematica("Sci-Fi");
        addTematica("Drama");
        addTematica("Comedy");
        addTematica("Action");
        addTematica("Horror");
        addTematica("Thriller");
        addTematica("Romance");
        addTematica("Mystery");
        addTematica("Crime");
        addTematica("Animation");
        addTematica("Adventure");
        addTematica("Fantasy");
        addTematica("Superhero");
        addTematica("Family");
        addTematica("War");
        addTematica("History");
        addTematica("Documentary");
        addTematica("Western");
        addTematica("Music");
        addTematica("Sport");
        addTematica("Biography");
        addTematica("Musical");
        addTematica("Short");
    }

    private void addTematica(String nomTematica){
        tematiques.put(nomTematica, new Tematica(nomTematica));
    }

    @Override
    public Optional<Tematica> getById(String[] id) throws Exception {
        return Optional.ofNullable(tematiques.get(Objects.requireNonNull(id[0], "Tematica name cannot be null")));
    }

    @Override
    public List<Tematica> getAll() throws Exception {
        return new ArrayList<>(tematiques.values());
    }

    @Override
    public boolean add(Tematica Tematica) throws Exception {
        if (getById(new String[]{Tematica.getNomTematica()}).isPresent()) {
            return false;
        }
        tematiques.put(Tematica.getNomTematica(), Tematica);
        return true;
    }

    @Override
    public boolean update(Tematica Tematica, String[] params) throws Exception {
        Tematica.setNomTematica(Objects.requireNonNull(
                params[0], "Tematica name cannot be null"));
        return tematiques.replace(Tematica.getNomTematica(), Tematica) != null;
    }

    @Override
    public boolean delete(Tematica Tematica) throws Exception {
        return tematiques.remove(Tematica.getNomTematica()) != null;
    }
}
