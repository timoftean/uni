package ro.ubb.catalog.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Created by paul on 4/9/2017.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RentsDto {
    Set<RentDto> rents;
}