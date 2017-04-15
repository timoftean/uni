package ro.ubb.catalog.web.dto;

import ro.ubb.catalog.core.model.Client;

import java.util.List;
import java.util.Set;


/**
 * Created by paul on 4/9/2017.
 */
public class ClientsDto {

    private Set<Client> clients;

    public ClientsDto() {
    }

    public ClientsDto(Set<Client> clients) {

        this.clients = clients;
    }

    public Set<Client> getClients() {

        return clients;
    }

    public void setClients(Set<Client> clients) {

        this.clients = clients;
    }
}
