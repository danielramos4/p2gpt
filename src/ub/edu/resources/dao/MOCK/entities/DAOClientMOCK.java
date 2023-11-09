package ub.edu.resources.dao.MOCK.entities;

import ub.edu.model.Client;
import ub.edu.resources.dao.entities.DAOClient;

import java.util.*;

public class DAOClientMOCK implements DAOClient {

    private Map<String, Client> xarxaPersones = new HashMap<>();

    public DAOClientMOCK() {
        addPersona("ajaleo@gmail.com", "ajaleoPassw7");
        addPersona("dtomacal@yahoo.cat", "Qwertyft5");
        addPersona("heisenberg@gmail.com", "the1whoknocks");
        addPersona("rick@gmail.com", "wabalabadapdap22");
        addPersona("nietolopez10@gmail.com", "pekFD91m2a");
        addPersona("nancyarg10@yahoo.com", "contra10LOadc");
        addPersona("CapitaCC@gmail.com", "Alistar10");
        addPersona("nauin2@gmail.com", "kaynJGL20");
        addPersona("juancarlos999@gmail.com", "staIamsA12");
        addPersona("judit121@gmail.com", "Ordinador1");
    }

    private void addPersona(String nom, String pwd){
        xarxaPersones.put(nom, new Client(nom, pwd));
    }

    @Override
    public List<Client> getAll() {
        return new ArrayList<>(xarxaPersones.values());
    }

    @Override
    public Optional<Client> getById(String[] id) {
        return Optional.ofNullable(xarxaPersones.get(Objects.requireNonNull(id[0], "Name cannot be null")));
    }

    @Override
    public boolean add(final Client client) {
        if (xarxaPersones.containsKey(client.getName())) {
            return false;
        }
        xarxaPersones.put(client.getName(), client);
        return true;
    }

    @Override
    public boolean update(final Client client, String[] params) {
        client.setName(Objects.requireNonNull(
                params[0], "Name cannot be null"));
        client.setPwd(Objects.requireNonNull(
                params[1], "Password cannot be null"));
        return xarxaPersones.replace(client.getName(), client) != null;
    }

    @Override
    public boolean delete(final Client client) {
        return xarxaPersones.remove(client.getName()) != null;
    }


}
