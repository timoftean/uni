package ro.ubb.catalog.web.dto;

import lombok.*;
import ro.ubb.catalog.core.model.Client;

import java.util.List;
import java.util.Set;

import java.util.Set;


/**
 * Created by paul on 4/9/2017.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ClientsDto {
    private Set<ClientDto> clients;
}

