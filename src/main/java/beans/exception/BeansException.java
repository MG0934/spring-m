package beans.exception;

public class BeansException extends RuntimeException{
    public BeansException(String message) {
        super(message);
    }

    public BeansException() {
        super();
    }

    public BeansException(Throwable cause) {
        super(cause);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
