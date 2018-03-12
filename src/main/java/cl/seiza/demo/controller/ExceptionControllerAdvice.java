package cl.seiza.demo.controller;

import cl.seiza.demo.controller.dominio.ErrorInfo;
import cl.seiza.demo.exception.BusinessException;
import cl.seiza.demo.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ExceptionControllerAdvice {
    
    private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler(value = {SystemException.class, Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorInfo handleSystemException(HttpServletRequest req, Exception e) {
        logger.error("Error de sistema", e);
        return new ErrorInfo(e, req.getRequestURL().toString());
    }

    @ExceptionHandler({BusinessException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorInfo handleBusinessException(HttpServletRequest req, Exception se) {
        logger.error("Error de negocio", se);
        return new ErrorInfo(se, req.getRequestURL().toString());
    }


}
