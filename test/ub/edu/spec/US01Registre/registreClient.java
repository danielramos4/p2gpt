package ub.edu.spec.US01Registre;

import org.concordion.api.BeforeExample;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import ub.edu.controller.Controller;

@RunWith(ConcordionRunner.class)
public class registreClient {
    private Controller controlador;

    @BeforeExample
    private void init() {
        controlador = new Controller();
    }


    public String getClient(String a) {
        return controlador.findClient(a);
    }

    public String validatePassword(String b) {
        return controlador.validatePassword(b);
    }

    public String validateUsername(String a) {
        return controlador.validateUsername(a);
    }

    public String isValidRegistre(String personaName, String password) {
        return controlador.validateRegistreClient(personaName, password);
    }
}
