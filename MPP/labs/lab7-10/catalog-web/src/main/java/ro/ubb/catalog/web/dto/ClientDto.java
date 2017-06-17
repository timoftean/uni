package ro.ubb.catalog.web.dto;

import lombok.*;

import java.util.Set;

/**
 * Created by Nicu on 4/24/17.
 */


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClientDto extends BaseDto {
    private String name;
    private Integer cnp;
    private Set<Long> movies;

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", cnp='" + cnp + '\'' +
                ", movies = '" + movies +
                "} " + super.toString();
    }


}
