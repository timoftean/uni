package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Nicu on 4/9/17.
 */
@Entity
@Table(name = "client")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Client extends BaseEntity<Long> {

    @Column(name = "cnp",nullable = false)
    private int cnp;

    @Column(name = "name", nullable = false)
    private String name;


    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Rent> rents = new HashSet<>();

    public Set<Movie> getMovies() {
        return Collections.unmodifiableSet(
                rents.stream()
                        .map(r -> r.getMovie())
                        .collect(Collectors.toSet())
        );
    }

    public void addMovie(Movie movie) {
        Rent rent = new Rent();
        rent.setMovie(movie);
        rent.setClient(this);
        rents.add(rent);
    }


    public void addMovies(Set<Movie> movies) {
        movies.stream()
                .forEach(m -> addMovie(m));
    }

    public void addNocopies(Movie movie, Integer nocopies) {
        Rent rent = new Rent();
        rent.setMovie(movie);
        rent.setNocopies(nocopies);
        rent.setClient(this);
        rents.add(rent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client that = (Client) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", cnp='" + cnp +
                "} " + super.toString();
    }
}
