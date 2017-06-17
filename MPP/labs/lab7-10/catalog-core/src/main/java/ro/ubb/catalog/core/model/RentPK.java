package ro.ubb.catalog.core.model;

import lombok.*;

import java.io.Serializable;

/**
 * Created by Nicu on 5/8/17.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class RentPK implements Serializable {
    private Client client;
    private Movie movie;
}