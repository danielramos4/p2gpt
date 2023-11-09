package ub.edu.controller;

import org.concordion.api.MultiValueResult;

import ub.edu.model.*;
import ub.edu.resources.dao.Parell;
import ub.edu.resources.service.AbstractFactoryData;
import ub.edu.resources.service.DataService;
import ub.edu.resources.service.FactoryMOCK;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    private AbstractFactoryData factory;
    private DataService dataService;
    private CarteraClients carteraClients;
    private Map<String, Serie> llistaSeries;
    private Map<String, Pelicula> llistaPelicules;
    private Map<String, Tematica> llistaTemes;

    public Controller() {
        factory = new FactoryMOCK();
        dataService = new DataService(factory);

        initEmptyDataStructures();
        loadData();
    }

    public void initEmptyDataStructures(){
        carteraClients = new CarteraClients();
        llistaSeries = new HashMap<>();
        llistaPelicules = new HashMap<>();
        llistaTemes = new HashMap<>();
    }

    private void loadData(){
        loadCarteraClients();
        loadPelicules();
        loadSeries();
        loadTematiques();
        relacionarPeliculesTemes();
    }

    private void relacionarPeliculesTemes() {
        List<Parell<String, String>> relacionsPT = dataService.getAllRelacionsPeliculesTematiques();

        for (Parell p : relacionsPT) {
            Tematica tema = llistaTemes.get(p.getElement1());
            Pelicula peli = llistaPelicules.get(p.getElement2());
            peli.addTematica(tema);
        }
    }

    public boolean loadCarteraClients() {
        List<Client> l = null;
        try {
            l = dataService.getAllPersones();
        } catch (Exception e) {
            return false;
        }
        if (l != null) {
            carteraClients = new CarteraClients(l);
            return true;
        }else return false;
    }

    public boolean loadPelicules() {
        List<Pelicula> l = null;
        try {
            l = dataService.getAllPelicules();
        } catch (Exception e) {
            return false;
        }
        if (l != null) {
            for (Pelicula p : l) {
                llistaPelicules.put(p.getTitol(), p);
            }
            return true;
        }else return false;
    }

    public boolean loadSeries() {
        List<Serie> l = null;
        try {
            l = dataService.getAllSeries();
        } catch (Exception e) {
            return false;
        }
        if (l != null) {
            for (Serie s : l) {
                llistaSeries.put(s.getTitol(), s);
            }
            return true;
        }else return false;
    }

    public boolean loadTematiques() {
        List<Tematica> l = null;
        try {
            l = dataService.getAllTematiques();
        } catch (Exception e) {
            return false;
        }
        if (l != null) {
            for (Tematica t : l) {
                llistaTemes.put(t.getNomTematica(), t);
            }
            return true;
        }else return false;
    }

    private List<Serie> getSeriesList() {
        return new ArrayList<>(llistaSeries.values());
    }

    public boolean isPasswordSegur(String password) {
        Pattern pattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public boolean isMail(String correu) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(correu);
        return matcher.find();
    }

    public Iterable<String> visualitzarPelisPerNom() {
        SortedSet<String> pelisDisponibles = new TreeSet<>();
        if (getPelisList().isEmpty()) {
            pelisDisponibles.add("No hi ha pel·lícules disponibles");
        } else {
            List<Pelicula> sortedList = getPelisList();
            sortedList.sort(new Comparator<Pelicula>() {
                public int compare(Pelicula a1, Pelicula a2) {
                    return (a1.getTitol().compareTo(a2.getTitol()));
                }
            });


            for (Pelicula p : sortedList) {
                pelisDisponibles.add(p.getTitol());
            }

        }
        return pelisDisponibles;
    }

    public Iterable<MultiValueResult> visualitzarPelisPerEstrena() {
        List<MultiValueResult> pelisDisponibles = new ArrayList<>();
        if (getPelisList().isEmpty()) {
            MultiValueResult m = new MultiValueResult();
            m.with("titol", "No hi ha pel·lícules disponibles");
            m.with("any", " ");

            pelisDisponibles.add(m);
        } else {
            List<Pelicula> sortedList = getPelisList();
            sortedList.sort(new Comparator<Pelicula>() {
                public int compare(Pelicula a1, Pelicula a2) {
                    return (Integer.compare(a2.getAnyEstrena(), a1.getAnyEstrena()));
                }
            });


            for (Pelicula p : sortedList) {
                MultiValueResult m = new MultiValueResult();
                m.with("titol", p.getTitol());
                m.with("any", p.getAnyEstrena());
                pelisDisponibles.add(m);
            }

        }
        return pelisDisponibles;
    }


    private List<Pelicula> getPelisList() {
        return new ArrayList<>(llistaPelicules.values());
    }


    public String findClient(String username) {
        Client client = carteraClients.find(username);
        if (client != null) return "Client ja existent en el Sistema";
        else return "Client desconeguda";
    }


    public String validatePassword(String b) {
        if (!isPasswordSegur(b)) {
            return "Contrassenya no prou segura";
        } else
            return "Contrassenya segura";
    }


    public String validateUsername(String b) {
        if (!isMail(b))
            return "Correu en format incorrecte";
        else
            return "Correu en format correcte";
    }

    // Validem la Client a la capa de persistencia i no a memoria, per seguretat en les possibles sincronitzacions
    public String validateRegistreClient(String username, String password) {
        if  (dataService.getClientByUsername(username).isPresent() )
            return "Client duplicat";
        else if (isMail(username) && isPasswordSegur(password))
            return "Registre vàlid";
        else return "Format incorrecte";
    }

    public String loguejarClient(String username, String password) {
        Client client = carteraClients.find(username);
        if (client == null) {
            return "Correu inexistent";
        }
        if (client.getPwd().equals(password)) {
            return "Login correcte";
        } else {
            return "Contrassenya incorrecta";
        }
    }

    public String recuperarContrassenya(String username) {
        Client client = carteraClients.find(username);
        if (client == null) {
            return "Correu inexistent";
        }
        return client.getPwd();
    }


    public void afegirPelicula(String nom, int estrena, int durada) {
        Pelicula p = new Pelicula(nom, estrena, durada);
        llistaPelicules.put(nom, p);
    }

    public Iterable<String> visualitzarSeriesPerNom() {
        SortedSet<String> seriesDisponibles = new TreeSet<>();
        if (getSeriesList().isEmpty()) {
            seriesDisponibles.add("No hi ha sèries disponibles");
        } else {
            for (Serie r : getSeriesList()) {
                seriesDisponibles.add(r.getTitol());
            }
        }
        return seriesDisponibles;
    }

    public void afegirSerie(String nomSerie, int anyEstrena) {
        Serie s = new Serie(nomSerie, anyEstrena);
        llistaSeries.put(nomSerie, s);
    }



    public Iterable<String> visualitzarTemporadesSerie(String nomSerie) {
        SortedSet<String> temporadesDisponibles = new TreeSet<>();
        Serie serie = llistaSeries.get(nomSerie);
        if (serie == null) {
            temporadesDisponibles.add("Aquesta sèrie no està disponible en el sistema");
        } else {
            List<Temporada> temporades = serie.getTemporades();
            if (temporades.size() == 0) {
                temporadesDisponibles.add("Aquesta sèrie encara no té temporades");
            } else {
                for (Temporada t : serie.getTemporades()) {
                    temporadesDisponibles.add(String.valueOf(t.getNumTemporada()));
                }
            }
        }
        return temporadesDisponibles;
    }


    public Iterable<String> visualitzaEpisodisTemporadaSerie(String nomSerie, int numTemporada) {
        List<String> episodisDisponibles = new ArrayList<>();
        Serie serie = llistaSeries.get(nomSerie);
        if (serie == null) {
            episodisDisponibles.add("Aquesta sèrie no està disponible en el sistema");
        } else {
            List<Temporada> temporades = serie.getTemporades();
            if (temporades == null) {
                episodisDisponibles.add("Aquesta sèrie no té encara temporades");
            } else {
                int i = 0;
                boolean trobat = false;
                while  (i<temporades.size() && !trobat) {
                    Temporada temporada = serie.getTemporades().get(i);
                    if (temporada.getNumTemporada() == numTemporada) {
                        trobat = true;
                    } else i++;
                }
                if (trobat) {
                    Temporada temporada = serie.getTemporades().get(i);

                    List<Episodi> sortedList = temporada.getEpisodis();
                    sortedList.sort(new Comparator<Episodi>() {
                        public int compare(Episodi a1, Episodi a2) {
                            return (Integer.compare(a1.getNumEpisodi(), a2.getNumEpisodi()));
                        }
                    });


                    for (Episodi e : sortedList) {
                        episodisDisponibles.add(e.getTitol());
                    }
                } else {
                    episodisDisponibles.add("Aquesta sèrie no té aquesta temporada");
                }
            }
        }
        return episodisDisponibles;
    }


    public Iterable<String> visualitzarPelisPerTematica(String nomTematica) {
        ArrayList<String> pelisDisponibles = new ArrayList<>();
        if (getPelisList().isEmpty()) {
            pelisDisponibles.add("No hi ha pel·lícules disponibles");
        } else {
            ArrayList<String> sortedList = new ArrayList<>();
            for (Pelicula p : getPelisList()) {
                ArrayList<Tematica> tematiques = p.getTematiques();

                for (Tematica t : tematiques) {
                    if (t.getNomTematica().equals(nomTematica)) {
                        sortedList.add(p.getTitol());
                    }
                }
            }
            sortedList.sort(new Comparator<String>() {
                public int compare(String a1, String a2) {
                        return (a1.compareTo(a2));
                }
                });
            for (String s : sortedList) {
                    pelisDisponibles.add(s);
                }
            }

        return pelisDisponibles;
    }
}
