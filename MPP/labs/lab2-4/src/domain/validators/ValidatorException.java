package domain.validators;

/**
 * Created by Nicu on 3/11/2017.
 */
public class ValidatorException extends RentalException {
    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatorException(Throwable cause) {
        super(cause);
    }
}
