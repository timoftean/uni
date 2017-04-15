package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.catalog.core.model.Client;
import ro.ubb.catalog.core.repository.ClientRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Nicu on 4/9/17.
 */
@Service("clientServiceImpl")
public class ClientServiceImpl implements ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);


    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void addClient(Client cl){
        log.trace("addClient --- method entered");

        clientRepository.save(cl);

        log.trace("add: client={}", cl);

    }

    @Override
    public Set<Client> getAllClients() {
        log.trace("findAll --- method entered");

        List<Client> clients = clientRepository.findAll();

        log.trace("findAll: clients={}", clients);

        return clients.stream().collect(Collectors.toSet());
    }

    @Override
    public Set<Client> filterClientsByAge(int age){
        return null;
    }

    @Override
    public void deleteClient(Client client){

        log.trace("deleteClient --- method entered");

        clientRepository.delete(client);

        log.trace("deleteClient: client={}", client);

    }

    @Override
    public void updateClient(Client client){

        log.trace("updateClient --- method entered");

        clientRepository.delete(client);
        clientRepository.save(client);

        log.trace("updateClient: client={}", client);

    }
}
