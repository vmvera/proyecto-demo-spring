package cl.seiza.demo.controller;

import cl.seiza.demo.configuracion.TestUtil;
import cl.seiza.demo.configuracion.WebAppContext;
import cl.seiza.demo.exception.CatalogoError;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppContext.class})
@WebAppConfiguration
public class DemoControllerTest {

    @Autowired
    protected WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void valida_manejo_excepciones_de_negocio() throws Exception {
        mockMvc.perform(
                get("/demo/error/1")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.codigo", is(CatalogoError.ERROR_SISTEMA_GENERICO)))
                .andExpect(jsonPath("$.mensaje", is("Error de prueba")))
                .andExpect(jsonPath("$.url", containsString("localhost")))
        ;
    }
    
    @Test
    public void valida_manejo_excepciones_de_sistema_no_controlado() throws Exception {
        mockMvc.perform(
                get("/demo/error-sistema/1")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.codigo", is(CatalogoError.ERROR_SISTEMA_GENERICO)))
                .andExpect(jsonPath("$.mensaje", is("/ by zero")))
                .andExpect(jsonPath("$.url", containsString("localhost")))
        ;
    }
    
    @Test
    public void valida_manejo_excepciones_de_sistema_controlado() throws Exception {
        mockMvc.perform(
                get("/demo/error-sistema/2")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.codigo", is(CatalogoError.ERROR_SISTEMA_GENERICO)))
                .andExpect(jsonPath("$.mensaje", is("Error de sistema")))
                .andExpect(jsonPath("$.url", containsString("localhost")))
        ;
    }

    @Test
    public void obtiene_demo_object_valido() throws Exception {
        mockMvc.perform(
                get("/demo/object/1")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.nombre", is("nombre prueba")))
                .andExpect(jsonPath("$.id", is(1)))
        ;
    }



}