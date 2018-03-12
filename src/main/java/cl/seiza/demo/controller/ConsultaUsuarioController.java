package cl.seiza.demo.controller;

import cl.seiza.demo.dominio.Usuario;
import cl.seiza.demo.service.ConsultaUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/usuario")
public class ConsultaUsuarioController {
    
    @Autowired
    private ConsultaUsuarioService consultaUsuarioService;

    @RequestMapping(value = "{nombre}", method = RequestMethod.GET)
    public List<Usuario> consultaUsuarios(@PathVariable String nombre) {
        return consultaUsuarioService.getUsuarios(nombre);
    }

}
