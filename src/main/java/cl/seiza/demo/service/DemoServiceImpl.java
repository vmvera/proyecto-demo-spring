package cl.seiza.demo.service;

import cl.seiza.demo.dominio.DemoObject;
import cl.seiza.demo.exception.BusinessException;
import cl.seiza.demo.exception.CatalogoError;
import cl.seiza.demo.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private ConversionService conversionService;


    @Override
    public void generaError() {
        throw new BusinessException("Error de prueba", new IllegalArgumentException());
    }

    @Override
    public int generaSystemError() {
        return 10 / 0;
    }

    @Override
    public int generaSystemError2() {
        throw new SystemException("Error de sistema", CatalogoError.ERROR_SISTEMA_GENERICO,new RuntimeException());
    }
    
    @Override
    public DemoObject getDemoObject(Integer id) {
        return new DemoObject.Builder()
                .nombre("nombre prueba")
                .id(id)
                .build();
    }
    
    @Override
    public DemoObject creaObject(String entrada) {
        return conversionService.convert(entrada, DemoObject.class);
    }
    
}
