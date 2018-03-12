package cl.seiza.demo.repository;

import cl.seiza.demo.dominio.Usuario;

import java.util.List;

public interface FiltraUsuariosDao {
    List<Usuario> getUsuarios(String nombre);
}
