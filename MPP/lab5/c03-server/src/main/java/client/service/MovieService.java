package client.service;


import common.domain.Movie;
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

    public Movie getOneMovie(Long id){
       return repository.findOne(id).get();
    }
    public Set<Movie> filterMoviesByType(String m) {
        Iterable<Movie> movies = repository.findAll();
        Set<Movie> filteredMovies= new HashSet<>();
        movies.forEach(filteredMovies::add);
        filteredMovies.removeIf(movie -> !movie.getGenre().contains(m));

        return filteredMovies;
    }

    public Set<Movie> filterMoviesByName(String name) {
        Iterable<Movie> movies = repository.findAll();
        Set<Movie> filteredMovies = new HashSet<>();
        movies.forEach(filteredMovies::add);
        filteredMovies.removeIf(movie -> movie.getName() != name);

        return filteredMovies;
    }

    public Set<Movie> filterMoviesByDirector(String director) {
        Iterable<Movie> movies = repository.findAll();
        Set<Movie> filteredMovies = new HashSet<>();
        movies.forEach(filteredMovies::add);
        filteredMovies.removeIf(movie -> movie.getDirector() != director);

        return filteredMovies;
    }

    public Set<Movie> filterMoviesByNumberOfCopies(int nocopies) {
        Iterable<Movie> movies = repository.findAll();
        Set<Movie> filteredMovies = new HashSet<>();
        movies.forEach(filteredMovies::add);
        filteredMovies.removeIf(movie -> movie.getAvailableCopies() != nocopies);

        return filteredMovies;
    }

    public void deleteMovie(Long id) {
        repository.delete(id);
    }

    public void updateMovie(Movie movie) {
        repository.update(movie);
   }
}
