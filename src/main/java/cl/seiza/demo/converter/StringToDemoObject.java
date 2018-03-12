package cl.seiza.demo.converter;

import cl.seiza.demo.dominio.DemoObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;

public class StringToDemoObject implements Converter<String, DemoObject> {

    /**
     * Recibe un string de la forma "nombre,id" 
     * @param s
     * @return un DemoObject
     */
    @Override
    public DemoObject convert(String s) {
        Assert.notNull(s,"String a convertir no puede ser null");
        String[] campos = s.split(",");
        try {
            return new DemoObject.Builder()
                    .id(Integer.parseInt(campos[1]))
                    .nombre(campos[0])
                    .build();
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("entrada no es válida: " + s);
        }
    }
}
