package ro.ubb.catalog.core.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Nicu on 4/9/17.
 */

@Entity
@Table(name = "movies")
public class Movie extends BaseEntity<Long>{
    //region Fields
    //@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "",unique = true, nullable = false)
//    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "director", nullable = false)
    private String director;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "availablecopies", nullable = false)
    private int availableCopies; //number of available copies available for rent
    //endregion

    //region Constructor
    public Movie(){

    }
    public Movie(String name, String director, String type, int availableCopies) {
   //     this.id=id;
        this.name = name;
        this.director = director;
        this.genre = type;
        this.availableCopies = availableCopies;
    }
    //endregion

    //region Methods
    //public int getMovieTitle(){return this.id;}

    //public void setMovieTitle(int id){
//        this.id=id;
//    }

    public String getName() {
        return this.name;
    }

    public String getDirector() {
        return this.director;
    }

    public String getGenre() {
        return this.genre;
    }

    public int getAvailableCopies() {
        return this.availableCopies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie that = (Movie) o;

        if (genre != that.genre) return false;
        if (!name.equals(that.name)) return false;
        return director.equals(that.director);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + director.hashCode();
        result = 31 * result + genre.hashCode();
        return result;
    }

    public String toString() {
        return "Movie{" +
              //  "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", director='" + director + '\'' +
                ", genre='" + genre + '\'' +
                ", available copies =" + availableCopies +
                "} " + super.toString();
    }
    //endregion
}