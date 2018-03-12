package cl.seiza.demo.controller;

import cl.seiza.demo.configuracion.TestUtil;
import cl.seiza.demo.configuracion.WebAppContext;
import cl.seiza.demo.exception.CatalogoError;
import cl.seiza.demo.fixture.UsuarioFixture;
import cl.seiza.demo.repository.FiltraUsuariosDao;
import cl.seiza.demo.service.ConsultaUsuarioService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppContext.class})
@WebAppConfiguration
public class UsuarioControllerTest {

    @Autowired
    protected WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    
    @Autowired
    @InjectMocks
    private ConsultaUsuarioService consultaUsuarioService;
    
    @Mock
    private FiltraUsuariosDao filtraUsuariosDao;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void consulta_usuarios() throws Exception {
        when(filtraUsuariosDao.getUsuarios("j")).thenReturn(UsuarioFixture.USUARIOS());
        mockMvc.perform(
                get("/usuario/j")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].nombre", is("Juan")))
                .andExpect(jsonPath("$[0].email", containsString("demo@demo.cl")))
        ;
    }

    @Test
    public void consulta_usuarios_error_bd() throws Exception {
        when(filtraUsuariosDao.getUsuarios("k")).thenThrow(new BadSqlGrammarException("task", "sql", new SQLException()));
        mockMvc.perform(
                get("/usuario/k")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.codigo", is(CatalogoError.ERROR_CONSULTA_USUARIO)))
                .andExpect(jsonPath("$.mensaje", is("Error al consultar usuarios, nombre: k")))
                .andExpect(jsonPath("$.url", containsString("localhost")))
        ;
    }


}
