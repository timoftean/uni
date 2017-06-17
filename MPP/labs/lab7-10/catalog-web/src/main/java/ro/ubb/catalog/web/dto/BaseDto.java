package ro.ubb.catalog.web.dto;
import lombok.*;

import java.io.Serializable;

/**
 * Created by Nicu on 4/24/17.
 */


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BaseDto implements Serializable{
    private Long id;
}
