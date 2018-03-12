package cl.seiza.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ImportResource({"classpath:spring/custom-converters.xml"})
@ComponentScan(basePackages = {"cl.seiza.demo"})
public class IntegrationTestContext {

    @Bean
    public DataSource requerimientosDS() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
        ds.setUrl("jdbc:jtds:sqlserver://localhost/desarrollo;instance=poc");
        ds.setUsername("user");
        ds.setPassword("pass");
        return ds;
    }


}


