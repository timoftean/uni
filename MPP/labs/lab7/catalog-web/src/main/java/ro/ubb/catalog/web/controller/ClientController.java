package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.catalog.core.model.Client;
import ro.ubb.catalog.core.service.ClientService;
import ro.ubb.catalog.web.dto.ClientsDto;

import java.util.Set;

/**
 * Created by macbookpro on 4/9/17.
 */
@RestController
public class ClientController {

    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @RequestMapping(value="clients", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientsDto getClients() {
        log.trace("getClients --- method entered");

        Set<Client> clients = clientService.getAllClients();

        log.trace("getClients: clients={}", clients);

        return new ClientsDto(clients);
    }
}
