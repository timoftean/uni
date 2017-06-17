package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Movie;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by Nicu on 4/9/17.
 */

public interface MovieService {
    List<Movie> findAll();

    Movie findMovie(Long movieId);

    Movie updateMovie(Long movieId, String name, String director, String genre,Integer availablecopies,
                          Set<Long> clients);

    Movie createMovie(String name, String director, String genre,Integer availablecopies);

    void deleteMovie(Long movieId);

    Movie updateMovieNocopies(Long movieid, Map<Long, Integer> nocopies);
}
