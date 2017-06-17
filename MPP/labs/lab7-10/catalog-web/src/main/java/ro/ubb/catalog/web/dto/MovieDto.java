package ro.ubb.catalog.web.dto;

import lombok.*;

import java.util.Set;


/**
 * Created by paul on 4/29/2017.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MovieDto extends BaseDto {
    private String name;
    private String director;
    private String genre;
    private Integer availableCopies;
    private Set<Long> clients;

    @Override
    public String toString() {
        return "Movie{" +
                //  "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", director='" + director + '\'' +
                ", genre='" + genre + '\'' +
                ", available copies =" + availableCopies +
                ", clients = '" + clients +

                "} " + super.toString();
    }
}
