package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.catalog.core.model.Movie;
import ro.ubb.catalog.core.repository.MovieRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Nicu on 4/9/17.
 */
@Service
public class MovieServiceImpl implements MovieService {

    private static final Logger log = LoggerFactory.getLogger(MovieServiceImpl.class);


    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void addMovie(Movie movie){
        log.trace("addMovie --- method entered");

        movieRepository.save(movie);

        log.trace("add: movie={}", movie);

    }

    @Override
    public Set<Movie> getAllMovies() {
        log.trace("findAll --- method entered");

        List<Movie> movies = movieRepository.findAll();

        log.trace("findAll: movies={}", movies);

        return movies.stream().collect(Collectors.toSet());
    }

    @Override
    public Set<Movie> filterMovieByType(String type){
        return null;
    }

    @Override
    public void deleteMovie(Movie movie){

        log.trace("deleteMovie --- method entered");

        movieRepository.delete(movie);

        log.trace("deleteMovie: movie={}", movie);

    }

    @Override
    public void updateMovie(Movie movie){

        log.trace("updateMovie --- method entered");

        movieRepository.delete(movie);
        movieRepository.save(movie);

        log.trace("updateMovie: movie={}", movie);

    }
}
