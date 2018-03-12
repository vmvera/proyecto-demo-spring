package cl.seiza.demo.configuracion;


import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ImportResource({"classpath:spring/custom-converters.xml"})
@ComponentScan(basePackages = {"cl.seiza.demo"})
public class WebAppContext extends WebMvcConfigurerAdapter {

    @Bean
    public DataSource requerimientosDS() {
        return Mockito.mock(DataSource.class);
    }

}

