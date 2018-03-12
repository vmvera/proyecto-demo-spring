package cl.seiza.demo.controller.dominio;

import cl.seiza.demo.exception.BaseException;
import cl.seiza.demo.exception.CatalogoError;
import com.google.common.base.MoreObjects;

public class ErrorInfo {
    
    private int codigo;
    private String mensaje;
    private String url;

    public ErrorInfo(Exception e, String url) {
        this.url = url;
        this.mensaje = e.getMessage();
        this.codigo = (e instanceof BaseException)?((BaseException) e).getCodigo(): CatalogoError.ERROR_SISTEMA_GENERICO;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("codigo", codigo)
                .add("mensaje", mensaje)
                .add("url", url)
                .toString();
    }



}
