package client.service;
import common.Client;
import common.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Nicu on 4/1/17.
 */

@Service
public class ClientServiceClient implements ClientService {

    @Autowired
    private ClientService clientService;

    @Override
    public void addClient(Client cl){
        clientService.addClient(cl);
    }

    @Override
    public Set<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @Override
    public Set<Client> filterClientsByAge(int age){
        return clientService.filterClientsByAge(age);
    }

    @Override
    public void deleteClient(Client client){
         clientService.deleteClient(client);
    }

    @Override
    public void updateClient(Client client){
        clientService.updateClient(client);
    }
}