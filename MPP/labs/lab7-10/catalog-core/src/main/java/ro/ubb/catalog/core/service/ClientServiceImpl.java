package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Client;
import ro.ubb.catalog.core.model.Movie;
import ro.ubb.catalog.core.repository.ClientRepository;
import ro.ubb.catalog.core.repository.MovieRepository;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Nicu on 4/9/17.
 */

@Service
public class ClientServiceImpl implements ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Client> findAll() {
        log.trace("findAll");

        List<Client> clients = clientRepository.findAll();

        log.trace("findAll: clients={}", clients);

        return clients;
    }

    @Override
    public Client findClient(Long clientId) {
        log.trace("findMovie: clientId={}", clientId);

        Client client = clientRepository.findOne(clientId);

        log.trace("findMovie: client={}", client);

        return client;
    }

    @Override
    @Transactional
    public Client updateClient(Long clientId, int cnp, String name, Set<Long> movies) {
        log.trace("updateClient: clientId={}, cnp={}, name={}, movies={}",
                clientId, cnp, name, movies);

        Client client = clientRepository.findOne(clientId);
        client.setCnp(cnp);
        client.setName(name);
        client.getMovies().stream()
                .map(d -> d.getId())
                .forEach(id -> {
                    if(movies.contains(id)) {
                        movies.remove(id);
                    }
                });
        List<Movie> movieList = movieRepository.findAll(movies);
        movieList.stream().forEach(m -> client.addMovie(m));

        log.trace("updatedClient: client={}", client);

        return client;
    }

    @Override
    public Client createClient(int cnp, String name) {
        log.trace("createClient: cnp={}, name={}", cnp, name);

        Client client = Client.builder()
                .cnp(cnp)
                .name(name)
                .build();

        Client c = clientRepository.save(client);

        log.trace("createClient: client={}", c);

        return c;
    }


    @Override
    public void deleteClient(Long clientId) {
        log.trace("deleteClient: clientId={}", clientId);

        clientRepository.delete(clientId);

        log.trace("deleteClient - method end");
    }

    @Override
    @Transactional
    public Client updateClientNocopies(Long clientId, Map<Long, Integer> nocopies) {
        log.trace("updateClientNocopies: clientid={}, nocopies={}", clientId, nocopies);

        Client client = clientRepository.findOne(clientId);
        client.getRents().stream()
                .forEach(r -> r.setNocopies(nocopies.get(r.getClient().getId())));

        log.trace("updateClientNocopies: client={}", client);
        return client;
    }
}
