package ub.edu.resources.dao.MOCK.entities;

import ub.edu.model.Temporada;
import ub.edu.resources.dao.entities.DAOTemporada;
import ub.edu.resources.dao.Parell;

import java.util.*;

public class DAOTemporadaMOCK implements DAOTemporada {

    // Utilitzem nomSerie, numTemporada per identificar una temporada
    private Map<Parell<String, Integer>, Temporada> temporades = new HashMap<>();

    public DAOTemporadaMOCK(){
        addTemporada("Stranger Things", 1);
        addTemporada("Stranger Things", 2);
        addTemporada("Stranger Things", 3);
        addTemporada("Stranger Things", 4);
        addTemporada("Stranger Things", 5);
    }

    private void addTemporada(String nomSerie, int numTemporada){
        temporades.put(new Parell<>(nomSerie,numTemporada), new Temporada(nomSerie, numTemporada));
    }

    @Override
    public Optional<Temporada> getById(String[] id) throws Exception {
        int numTemporada = Integer.parseInt(Objects.requireNonNull(id[1], "Temporada number cannot be null"));
        return Optional.ofNullable(temporades.get(new Parell<>(Objects.requireNonNull(id[0], "Serie name cannot be null"), numTemporada)));
    }

    public Optional<Temporada> getById(String nomSerie, int numTemporada) throws Exception {
        return getById(new String[]{nomSerie, Integer.toString(numTemporada)});
    }

    @Override
    public List<Temporada> getAll() throws Exception {
        return new ArrayList<>(temporades.values());
    }

    @Override
    public boolean add(final Temporada temporada) throws Exception {
        if (getById(temporada.getNomSerie(), temporada.getNumTemporada()).isPresent()) {
            return false;
        }
        temporades.put(new Parell<>(temporada.getNomSerie(), temporada.getNumTemporada()), temporada);
        return true;
    }

    @Override
    public boolean update(Temporada temporada, String[] params) throws Exception {
        temporada.setNomSerie(Objects.requireNonNull(
                params[0], "Serie name cannot be null"));
        return temporades.replace(new Parell<>(temporada.getNomSerie(), temporada.getNumTemporada()), temporada) != null;
    }

    @Override
    public boolean delete(Temporada temporada) throws Exception {
        return temporades.remove(new Parell<>(temporada.getNomSerie(), Integer.toString(temporada.getNumTemporada()))) != null;
    }
}
