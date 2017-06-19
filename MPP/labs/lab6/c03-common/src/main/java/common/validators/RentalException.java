package common.validators;

/**
 * Created by Nicu on 3/11/2017.
 */
public class RentalException extends RuntimeException{

    public RentalException(String message) {
        super(message);
    }

    public RentalException(String message, Throwable cause) {
        super(message, cause);
    }

    public RentalException(Throwable cause) {
        super(cause);
    }
}
