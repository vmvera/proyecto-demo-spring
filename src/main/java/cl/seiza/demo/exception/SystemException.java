package cl.seiza.demo.exception;

public class SystemException extends BaseException {
    
    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(String message, int codigo, Throwable cause) {
        super(message, codigo, cause);
    }
    
}
