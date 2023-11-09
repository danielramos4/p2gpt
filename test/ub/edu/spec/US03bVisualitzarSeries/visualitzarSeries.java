package ub.edu.spec.US03bVisualitzarSeries;

import org.concordion.api.BeforeExample;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import ub.edu.controller.Controller;

@RunWith(ConcordionRunner.class)
public class visualitzarSeries {

    private Controller controlador;

    @BeforeExample
    private void init() {
        controlador = new Controller();
    }

    public Iterable<String> visualitzarSeriesPerNom(String buit) {
        if (buit.equals("buit")) {
            controlador.initEmptyDataStructures();
        }
        return controlador.visualitzarSeriesPerNom();
    }


}
