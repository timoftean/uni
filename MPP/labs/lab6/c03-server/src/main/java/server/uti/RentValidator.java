package server.uti;

import common.Rent;

/**
 * Created by paul on 3/14/2017.
 */
public class RentValidator implements Validator<Rent> {

    @Override
    public void validate(Rent rent) throws ValidatorException{
       if(rent.getClientCnp()<0){
            throw new ValidatorException("Client Cnp must be positive!");
        }

        if(rent.getMovieId()<0){
            throw new ValidatorException("Movie ID must be positive!");
        }

        if(rent.getNoCopies()<0){
            throw new ValidatorException("The number of copies must be positive!");
        }

    }

}
