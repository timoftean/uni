package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.Email;

/**
 * Created by Nicu on 4/9/17.
 */

@Entity
@Table(name = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Movie extends BaseEntity<Long> {


    @Column(name = "name", nullable = false)
    @Size(min = 5)
    @Pattern(regexp="[a-zA-Z\\-'\\s]+")
    private String name;

    @Column(name = "director", nullable = false)
    @Size(min = 5)
    @Email//(message = "director must be email")
    private String director;

    @Column(name = "genre", nullable = false)
    @NotNull
    private String genre;

    @Column(name = "availablecopies", nullable = false)
//    @Min(value = 10, message = "There have to be at least 10 copies available")
    private int availableCopies;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Rent> rents = new HashSet<>();

    public Set<Client> getClients() {
        return Collections.unmodifiableSet(
                this.rents.stream().
                        map(r -> r.getClient()).
                        collect(Collectors.toSet()));
    }

    public void addClient(Client client) {
        Rent rent = new Rent();
        rent.setClient(client);
        rent.setMovie(this);
        rents.add(rent);
    }

//    @Min(value = 10, message = "There have to be at least 10 copies available")
//    public int getAvailableCopies() {
//        return this.availableCopies;
//    }

    public void addClients(Set<Client> clients) {
        clients.stream()
                .forEach(c -> addClient(c));
    }

    public void addNocopies(Client client, Integer nocopies) {
        Rent rent = new Rent();
        rent.setClient(client);
        rent.setNocopies(nocopies);
        rent.setMovie(this);
        rents.add(rent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return name.equals(movie.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Movie{" +
              //  "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", director='" + director + '\'' +
                ", genre='" + genre + '\'' +
                ", available copies =" + availableCopies +
                "} " + super.toString();
    }
}

//public class Movie extends BaseEntity<Long>{
//    //region Fields
//    //@Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    @Column(name = "",unique = true, nullable = false)
////    private int id;
//
//    @Column(name = "name", nullable = false)
//    private String name;
//
//    @Column(name = "director", nullable = false)
//    private String director;
//
//    @Column(name = "genre", nullable = false)
//    private String genre;
//
//    @Column(name = "availablecopies", nullable = false)
//    private int availableCopies; //number of available copies available for rent
//    //endregion
//
//    //region Constructor
//    public Movie(){
//
//    }
//    public Movie(String name, String director, String type, int availableCopies) {
//   //     this.id=id;
//        this.name = name;
//        this.director = director;
//        this.genre = type;
//        this.availableCopies = availableCopies;
//    }
//    //endregion
//
//    //region Methods
//    //public int getMovieTitle(){return this.id;}
//
//    //public void setMovieTitle(int id){
////        this.id=id;
////    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public String getDirector() {
//        return this.director;
//    }
//
//    public String getGenre() {
//        return this.genre;
//    }
//

//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setDirector(String director) {
//        this.director = director;
//    }
//
//    public void setGenre(String genre) {
//        this.genre = genre;
//    }
//
//    public void setAvailableCopies(int availableCopies) {
//        this.availableCopies = availableCopies;
//    }
//
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Movie that = (Movie) o;
//
//        if (genre != that.genre) return false;
//        if (!name.equals(that.name)) return false;
//        return director.equals(that.director);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = name.hashCode();
//        result = 31 * result + director.hashCode();
//        result = 31 * result + genre.hashCode();
//        return result;
//    }
//
//    public String toString() {
//        return "Movie{" +
//              //  "id='" + id + '\'' +
//                "name='" + name + '\'' +
//                ", director='" + director + '\'' +
//                ", genre='" + genre + '\'' +
//                ", available copies =" + availableCopies +
//                "} " + super.toString();
//    }
//    //endregion
//}