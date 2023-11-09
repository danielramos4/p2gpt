package ub.edu.model;

import java.util.ArrayList;
import java.util.List;

public class CarteraClients {
    private List<Client> llista;
    
    public CarteraClients() {
        llista = new ArrayList<>();
    }
    
    public CarteraClients(List<Client> allSocis) {
        llista = allSocis;
    }

    public Client find(String username) {
        for (Client c: llista) {
            if (c.getName().equals(username)) return c;
        }
        return null;
    }

    public void add(Client c) {
        llista.add(c);
    }

}
