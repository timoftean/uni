package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Movie;

import java.util.Set;

/**
 * Created by Nicu on 4/9/17.
 */
public interface MovieService {
    void addMovie(Movie movie);

    Set<Movie> getAllMovies();

    Set<Movie> filterMovieByType(String m);

    void deleteMovie(Movie movie);

    void updateMovie(Movie movie);


}