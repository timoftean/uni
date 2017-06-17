package ro.ubb.catalog.web.dto;

import lombok.*;
import ro.ubb.catalog.core.model.Rent;

import javax.persistence.Column;

/**
 * Created by Nicu on 5/1/17.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RentDto  {
    private Long clientId;
    private Long movieId;
    private Integer noCopies;

}
