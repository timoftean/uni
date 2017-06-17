package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Client;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Nicu on 4/9/17.
 */


public interface ClientService {
    List<Client> findAll();

    Client findClient(Long clientId);

    Client updateClient(Long clientId, int cnp, String name, Set<Long> movies);

    Client createClient(int cnp, String name);

    void deleteClient(Long clientId);

    Client updateClientNocopies(Long clientId, Map<Long, Integer> nocopies);

}