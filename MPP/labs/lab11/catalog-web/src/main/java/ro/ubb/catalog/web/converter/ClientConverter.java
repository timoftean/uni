package ro.ubb.catalog.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Client;
import ro.ubb.catalog.web.dto.ClientDto;

import java.util.stream.Collectors;

/**
 * Created by Nicu on 4/24/17.
 */


@Component
public class ClientConverter extends BaseConverter<Client,ClientDto> {

    private static final Logger log = LoggerFactory.getLogger(ClientConverter.class);

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto clientDto = ClientDto.builder()
                .cnp(client.getCnp())
                .name(client.getName())
                .build();
        clientDto.setId(client.getId());

        clientDto.setMovies(client.getMovies().stream()
                .map(movie -> movie.getId())
                .collect(Collectors.toSet()));
        return clientDto;
    }
}

//@Component
//public class ClientConverter extends BaseConverter<Client, ClientDto> {
//
//    private static final Logger log = LoggerFactory.getLogger(ClientConverter.class);
//
//    @Override
//    public ClientDto convertModelToDto(Client client) {
//        ClientDto clientDto = new ClientDto(client.getName(), client.getCnp());
//        clientDto.setId(client.getId());
//        return clientDto;
//    }
//}

