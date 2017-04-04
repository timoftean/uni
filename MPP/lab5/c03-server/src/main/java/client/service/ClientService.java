package client.service;

import common.domain.Client;
import repository.Repository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by paul on 3/13/2017.
 */
public class ClientService {
    private Repository<Long, Client> repository;

    public ClientService(Repository<Long, Client> repository) {
        this.repository = repository;
    }

    public void addClient(Client cl) {
        repository.save(cl);
    }

    public Set<Client> getAllClients() {
        Iterable<Client> clients = repository.findAll();
        return StreamSupport.stream(clients.spliterator(), false).collect(Collectors.toSet());
    }
    public Client getOneClient(Long id) { return repository.findOne(id).get();}

    public Set<Client> filterClientsByAge(int age) {
        Iterable<Client> clients = repository.findAll();
        Set<Client> filteredClients= new HashSet<>();
        clients.forEach(filteredClients::add);
        filteredClients.removeIf(cl -> cl.getAge()!=age);

        return filteredClients;
    }

    public Set<Client> filterClientsByName(String name) {
        Iterable<Client> clients = repository.findAll();
        Set<Client> filteredClients= new HashSet<>();
        clients.forEach(filteredClients::add);
        filteredClients.removeIf(cl -> cl.getName()!=name);

        return filteredClients;
    }

    public void deleteClient(Long id) {
        repository.delete(id);
    }

    public void updateClient(Client client) {
        repository.update(client);
    }
}

