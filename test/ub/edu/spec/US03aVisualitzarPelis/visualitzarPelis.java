package ub.edu.spec.US03aVisualitzarPelis;


import org.concordion.api.BeforeExample;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.concordion.api.MultiValueResult;

import ub.edu.controller.Controller;
import ub.edu.resources.dao.Parell;

@RunWith(ConcordionRunner.class)

public class visualitzarPelis {
    private Controller controlador;

    @BeforeExample
    private void init() {
        controlador = new Controller();
    }

    public void afegirPelicula (String nom, int estrena, int durada ) {
        controlador.afegirPelicula(nom, estrena, durada);
    }

    public Iterable<String> visualitzarPeliculesPerNom(String buit) {
        if (buit.equals("buit")) {
             controlador.initEmptyDataStructures();
        }
        return controlador.visualitzarPelisPerNom();
    }

    public Iterable<MultiValueResult>  visualitzarPeliculesPerEstrena() {
        return controlador.visualitzarPelisPerEstrena();
    }


    public Iterable<String> visualitzarPeliculesPerTematica(String tematica) {

        return controlador.visualitzarPelisPerTematica(tematica);
    }
}