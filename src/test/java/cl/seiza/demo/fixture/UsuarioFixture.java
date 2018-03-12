package cl.seiza.demo.fixture;

import cl.seiza.demo.dominio.Usuario;

import java.util.Arrays;
import java.util.List;

public class UsuarioFixture {
    
    public static List<Usuario> USUARIOS() {
        return Arrays.asList(
                new Usuario.Builder()
                        .id(1)
                        .nombre("Juan")
                        .email("demo@demo.cl")
                        .build(),
                new Usuario.Builder()
                        .id(2)
                        .nombre("Pedro")
                        .email("demo2@demo.cl")
                        .build());
    }
    
}
