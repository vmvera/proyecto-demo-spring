package cl.seiza.demo.exception;

public abstract class BaseException extends RuntimeException {
    
    private int codigo;

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.codigo = CatalogoError.ERROR_SISTEMA_GENERICO;
    }

    public BaseException(String message, int codigo, Throwable cause) {
        super(message, cause);
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
    
}
