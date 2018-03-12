package cl.seiza.demo.service;

import cl.seiza.demo.dominio.DemoObject;

public interface DemoService {
    void generaError();

    int generaSystemError();

    int generaSystemError2();

    DemoObject getDemoObject(Integer id);

    DemoObject creaObject(String entrada);
}
