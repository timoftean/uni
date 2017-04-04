package common.domain.validators;

/**
 * Created by Nicu on 3/11/2017.
 */
public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}

