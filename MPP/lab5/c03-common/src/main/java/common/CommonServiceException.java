package common;

/**
 * Created by user on 4/1/2017.
 */
public class CommonServiceException extends RuntimeException {
    public CommonServiceException(Throwable cause) {
        super(cause);
    }

    public CommonServiceException(String message) {
        super(message);
    }

    public CommonServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
