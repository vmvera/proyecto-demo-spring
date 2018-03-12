package cl.seiza.demo.repository;

import cl.seiza.demo.config.IntegrationTestContext;
import cl.seiza.demo.dominio.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {IntegrationTestContext.class})
@WebAppConfiguration
public class FiltraUsuariosDaoTestIT {
    
    @Autowired
    private FiltraUsuariosDao filtraUsuariosDao;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void obtiene_usuarios() {

        List<Usuario> resultado = filtraUsuariosDao.getUsuarios("j");
        assertThat(resultado.size(),is(1));
        assertThat(resultado.get(0).getId(),is(1));
        assertThat(resultado.get(0).getNombre(),is("Juan"));
        assertThat(resultado.get(0).getEmail(),is("demo@test.cl"));
        
    }

    

}