package service;


import domain.Movie;
import repository.Repository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
/**
 * Created by Nicu on 3/5/2017.
 */

public class MovieService {
    private Repository<Long, Movie> repository;

    public MovieService(Repository<Long, Movie> repository) {
        this.repository = repository;
    }

    public void addMovie(Movie movie) {
        repository.save(movie);
    }

    public Set<Movie> getAllMovies() {
        Iterable<Movie> movies = repository.findAll();
        return StreamSupport.stream(movies.spliterator(), false).collect(Collectors.toSet());
    }

    public Set<Movie> filterMovieByType(String m) {
        Iterable<Movie> movies = repository.findAll();
        Set<Movie> filteredMovies= new HashSet<>();
        movies.forEach(filteredMovies::add);
        filteredMovies.removeIf(movie -> !movie.getGenre().contains(m));

        return filteredMovies;
    }

    public void deleteMovie(Movie movie) {
//        Iterable<Movie> movies = repository.findAll();
//        movies.removeIf(m -> m.getName() == movie.getName())
        repository.delete(movie.getId());
    }

    public void updateMovie(Movie movie) {
        repository.update(movie);
   }
}
