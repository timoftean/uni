package domain.validators;

import domain.Client;

import java.util.Arrays;

/**
 * Created by Nicu on 3/11/2017.
 */
public class ClientValidator implements Validator<Client> {

    String[] movieGenre={"action","comedy","crime","drama","fantasy","historical","horror","war","western","science fiction"};

    @Override
    public void validate(Client client) throws ValidatorException {
        if(client.getId() < 0){
            throw new ValidatorException("Client Id must be positive !");
        }

        if(client.getAge() <14 || client.getAge() > 95){
            throw new ValidatorException( "Client age not in range [14..95] !");
        }

        if(client.getName().length() < 4){
            throw new ValidatorException( "Client name must contain at least 5 characters !");
        }
    }
}
