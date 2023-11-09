package ub.edu.resources.dao.MOCK.entities;

import ub.edu.model.Episodi;
import ub.edu.resources.dao.entities.DAOEpisodi;
import ub.edu.resources.dao.Trio;

import java.util.*;

public class DAOEpisodiMOCK implements DAOEpisodi {

    // Utilitzem nomSerie, numTemporada, numEpisodi per identificar un episodi
    private Map<Trio<String, Integer, Integer>, Episodi> episodis = new HashMap<>();

    public DAOEpisodiMOCK(){
        addEpisodi("Stranger Things", 1, 1, "Chapter One: The Vanishing of Will Byers", 55);
        addEpisodi("Stranger Things", 1, 2, "Chapter Two: The Weirdo on Maple Street", 55);
        addEpisodi("Stranger Things", 1, 3, "Chapter Three: Holly, Jolly", 55);
        addEpisodi("Stranger Things", 1, 4, "Chapter Four: The Body", 55);
        addEpisodi("Stranger Things", 1, 5, "Chapter Five: The Flea and the Acrobat", 55);
        addEpisodi("Stranger Things", 1, 6, "Chapter Six: The Monster", 55);
        addEpisodi("Stranger Things", 1, 7, "Chapter Seven: The Bathtub", 55);
        addEpisodi("Stranger Things", 1, 8, "Chapter Eight: The Upside Down", 55);

    }

    private void addEpisodi(String nomSerie, int numTemporada, int numEpisodi, String titolEpisodi, int durada){
        episodis.put(new Trio<>(nomSerie, numTemporada, numEpisodi), new Episodi(nomSerie, numTemporada, numEpisodi, titolEpisodi, durada));
    }

    @Override
    public Optional<Episodi> getById(String[] id) throws Exception {
        String nomSerie = Objects.requireNonNull(id[0], "Serie name cannot be null");
        Integer numTemporada = Integer.parseInt(Objects.requireNonNull(id[1], "Temporada number cannot be null"));
        Integer numEpisodi = Integer.parseInt(Objects.requireNonNull(id[2], "Episodi number cannot be null"));
        return Optional.ofNullable(episodis.get(new Trio<>(nomSerie, numTemporada, numEpisodi)));
    }

    @Override
    public List<Episodi> getAll() throws Exception {
        return new ArrayList<>(episodis.values());
    }

    @Override
    public boolean add(final Episodi episodi) throws Exception {
        if (getById(new String[]{episodi.getNomSerie(), episodi.getTitolEpisodi()}).isPresent()) {
            return false;
        }
        episodis.put(new Trio<>(episodi.getNomSerie(), episodi.getNumTemporada(), episodi.getNumEpisodi()), episodi);
        return true;
    }

    @Override
    public boolean update(Episodi episodi, String[] params) throws Exception {
        episodi.setTitolEpisodi(Objects.requireNonNull(
                params[0], "Episode name cannot be null"));
        return episodis.replace(new Trio<>(episodi.getNomSerie(), episodi.getNumTemporada(), episodi.getNumEpisodi()), episodi) != null;
    }

    @Override
    public boolean delete(Episodi episodi) throws Exception {
        return episodis.remove(new Trio<>(episodi.getNomSerie(), episodi.getNumTemporada(), episodi.getNumEpisodi())) != null;
    }
}
