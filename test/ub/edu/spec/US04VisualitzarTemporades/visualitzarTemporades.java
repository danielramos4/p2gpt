package ub.edu.spec.US04VisualitzarTemporades;

import org.concordion.api.BeforeExample;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import ub.edu.controller.Controller;

@RunWith(ConcordionRunner.class)
public class visualitzarTemporades {

    private Controller controlador;

    @BeforeExample
    private void init() {
        controlador = new Controller();
    }

    public Iterable<String> visualitzarTemporadesSerie(String nomSerie) {
        return controlador.visualitzarTemporadesSerie(nomSerie);
    }


}
