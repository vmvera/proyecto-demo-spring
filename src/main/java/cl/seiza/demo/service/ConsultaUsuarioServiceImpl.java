package cl.seiza.demo.service;

import cl.seiza.demo.dominio.Usuario;
import cl.seiza.demo.exception.CatalogoError;
import cl.seiza.demo.exception.SystemException;
import cl.seiza.demo.repository.FiltraUsuariosDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaUsuarioServiceImpl implements ConsultaUsuarioService {
    
    private static final Logger logger = LoggerFactory.getLogger(ConsultaUsuarioServiceImpl.class);
    
    @Autowired
    private FiltraUsuariosDao filtraUsuariosDao;
    
    @Override
    public List<Usuario> getUsuarios(String nombre) {
        try {
            return filtraUsuariosDao.getUsuarios(nombre);
        } catch (DataAccessException e) {
            logger.error("Error al consultar usuarios, filtro nombre: {}", nombre, e);
            throw new SystemException("Error al consultar usuarios, nombre: " + nombre, CatalogoError.ERROR_CONSULTA_USUARIO, e);
        }
    }
    
}
