package ro.ubb.catalog.web.dto;

import ro.ubb.catalog.core.model.Movie;

import java.util.List;
import java.util.Set;

/**
 * Created by paul on 4/9/2017.
 */
public class MoviesDto {

    private Set<Movie> movies;

    public MoviesDto(Set<Movie> movies) {
        this.movies = movies;
    }

    public MoviesDto() {
    }


    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}
