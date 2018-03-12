package cl.seiza.demo.repository;

import cl.seiza.demo.dominio.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class FiltraUsuariosDaoImpl implements FiltraUsuariosDao {

    private SimpleJdbcCall simpleJdbcCall;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.simpleJdbcCall =
                new SimpleJdbcCall(dataSource)
                        .withProcedureName("GET_USUARIOS")
                        .returningResultSet("rs1", BeanPropertyRowMapper.newInstance(Usuario.class));
    }

    @Override
    public List<Usuario> getUsuarios(String nombre) {
        SqlParameterSource in = new MapSqlParameterSource().addValue("NOMBRE", nombre);
        Map<String, Object> result = simpleJdbcCall.execute(in);
        return (List<Usuario>) result.get("rs1");
    }

}
