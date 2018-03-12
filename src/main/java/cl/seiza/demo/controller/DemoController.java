package cl.seiza.demo.controller;

import cl.seiza.demo.dominio.DemoObject;
import cl.seiza.demo.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping(value = "/demo")
public class DemoController {
    
    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
    
    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "error/{id}", method = RequestMethod.GET)
    public void getBusinessError(@PathVariable Integer id) {
        demoService.generaError();
    }

    @RequestMapping(value = "error-sistema/{id}", method = RequestMethod.GET)
    public void getSystemError(@PathVariable Integer id) {
        if (id == 1) {
            demoService.generaSystemError();
        }
        demoService.generaSystemError2();
    }

    @RequestMapping(value = "object/{id}", method = RequestMethod.GET)
    public DemoObject getDemoObject(@PathVariable Integer id) {
        return demoService.getDemoObject(id);
    }

    @PostConstruct
    public void version() {
        String version = getClass().getPackage().getImplementationVersion();
        logger.info("Starting App version " + version);
    }


}
