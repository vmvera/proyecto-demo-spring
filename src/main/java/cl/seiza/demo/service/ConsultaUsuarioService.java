package cl.seiza.demo.service;

import cl.seiza.demo.dominio.Usuario;

import java.util.List;

public interface ConsultaUsuarioService {
    List<Usuario> getUsuarios(String nombre);
}
