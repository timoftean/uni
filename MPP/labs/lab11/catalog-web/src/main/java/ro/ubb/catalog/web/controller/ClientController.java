package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.Client;;
import ro.ubb.catalog.core.service.ClientService;
import ro.ubb.catalog.web.converter.ClientConverter;
import ro.ubb.catalog.web.dto.ClientDto;
import ro.ubb.catalog.web.dto.ClientsDto;
import ro.ubb.catalog.web.dto.EmptyJsonResponse;

import java.util.*;

/**
 * Created by Nicu
 */

@RestController
public class ClientController {

    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientConverter clientConverter;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ClientsDto getClients() {
        log.trace("getClients");

        List<Client> clients = clientService.findAll();

        log.trace("getClients: clients={}", clients);

        return new ClientsDto(clientConverter.convertModelsToDtos(clients));
    }

    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.PUT)
    public Map<String, ClientDto> updateClient(
            @PathVariable final Long clientId,
            @RequestBody final Map<String, ClientDto> clientDtoMap) {
        log.trace("updateClient: clientId={}, clientDtoMap={}", clientId, clientDtoMap);

        ClientDto clientDto = clientDtoMap.get("client");
        Client client = clientService.updateClient(clientId, clientDto.getCnp(),  clientDto.getName(),clientDto.getMovies());

        Map<String, ClientDto> result = new HashMap<>();
        result.put("client", clientConverter.convertModelToDto(client));

        log.trace("updateClients: result={}", result);

        return result;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public Map<String, ClientDto> createClient(
            @RequestBody final Map<String, ClientDto> clientDtoMap) {
        log.trace("createClient: clientDtoMap={}", clientDtoMap);

        ClientDto clientDto = clientDtoMap.get("client");
        Client client = clientService.createClient(
                clientDto.getCnp(),clientDto.getName());
        client.setRents(Collections.emptySet());
        Map<String, ClientDto> result = new HashMap<>();
        result.put("client", clientConverter.convertModelToDto(client));

        log.trace("updateClient: result={}", result);

        return result;
    }

    @RequestMapping(value = "clients/{clientId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteClient(@PathVariable final Long clientId) {
        log.trace("deleteClient: clientId={}", clientId);

        clientService.deleteClient(clientId);

        log.trace("deleteClient - method end");

        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }
}
