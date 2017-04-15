package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Client;

import java.util.Set;

/**
 * Created by Nicu on 4/9/17.
 */
public interface ClientService {

    void addClient(Client cl);

    Set<Client> getAllClients() ;

    Set<Client> filterClientsByAge(int age) ;

    void deleteClient(Client client);

    void updateClient(Client client);


}
