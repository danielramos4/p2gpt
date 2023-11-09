package ub.edu.spec.US05VisualitzarEpisodis;

import org.concordion.api.BeforeExample;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import ub.edu.controller.Controller;

@RunWith(ConcordionRunner.class)
public class visualitzarEpisodis {

    private Controller controlador;

    @BeforeExample
    private void init() {
        controlador = new Controller();
    }

    public Iterable<String> visualitzarEpisodisTemporadaSerie(String nomSerie, int numTemporada) {
        return controlador.visualitzaEpisodisTemporadaSerie(nomSerie, numTemporada);
    }


}
