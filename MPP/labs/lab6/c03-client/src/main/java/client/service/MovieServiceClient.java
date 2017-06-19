package client.service;


import common.Movie;
import common.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Nicu on 4/1/17.
 */
@Service
public class MovieServiceClient implements MovieService {

    @Autowired
    private MovieService movieService;

    @Override
    public void addMovie(Movie movie){
        movieService.addMovie(movie);
    }

    @Override
    public Set<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

    @Override
    public Set<Movie> filterMovieByType(String m){
        return movieService.filterMovieByType(m);
    }

    @Override
    public void deleteMovie(Movie movie){
        movieService.deleteMovie(movie);
    }

    @Override
    public void updateMovie(Movie movie){
        movieService.updateMovie(movie);
    }

}