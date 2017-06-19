package common;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by nicu on 4/1/17.
 */
public interface ClientService {

     void addClient(Client cl);

     Set<Client> getAllClients() ;

     Set<Client> filterClientsByAge(int age) ;

     void deleteClient(Client client);

     void updateClient(Client client);


}
