package cl.seiza.demo.service;

import cl.seiza.demo.configuracion.WebAppContext;
import cl.seiza.demo.dominio.DemoObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppContext.class})
@WebAppConfiguration
public class DemoServiceTest {

    @Autowired
    protected WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    
    @Autowired
    private DemoService demoService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void convierte_string_to_demo_object() {
        DemoObject resultado = demoService.creaObject("nombre,100");
        assertThat(resultado.getId(), is(100));
        assertThat(resultado.getNombre(), is("nombre"));
    }

    @Test
    public void convierte_string_to_demo_object_entrada_no_valida() {
        try {
            demoService.creaObject("XXX");
        } catch (ConversionFailedException e) {
            assertThat(e.getMessage(), containsString("entrada no es válida: XXX"));
        }
    }

}