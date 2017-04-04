package common.domain.validators;

import common.domain.Movie;

import java.util.Arrays;

/**
 * Created by Nicu on 3/11/2017.
 */
public class MovieValidator implements Validator<Movie> {

    String[] movieGenre={"action","comedy","crime","drama","fantasy","historical","horror","war","western","science fiction"};

    @Override
    public void validate(Movie movie) throws ValidatorException {
       if(movie.getId() < 0){
           throw new ValidatorException("Movie Id must be positive !");
       }

       if(movie.getAvailableCopies()<1){
           throw new ValidatorException("Movie cannot have less than 1 copy");
       }

       if(!Arrays.asList(movieGenre).contains(movie.getGenre())){
           throw new ValidatorException(movie.getGenre() + " is not a valid movie genre !");
       }
       if(movie.getName().length() < 5){
           throw new ValidatorException( "Movie name must contain at least 5 characters !");
       }
        if(movie.getDirector().length() < 5){
            throw new ValidatorException( "Movie name must contain at least 5 characters !");
        }
    }
}
