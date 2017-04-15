package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.catalog.core.model.Movie;
import ro.ubb.catalog.core.service.MovieService;
import ro.ubb.catalog.web.dto.MoviesDto;


import java.util.Set;

/**
 * Created by Nicu on 4/9/17.
 */
@RestController
public class MovieController {

    private static final Logger log = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @RequestMapping(value="movies", produces = MediaType.APPLICATION_JSON_VALUE)
    public MoviesDto getMovies() {
        log.trace("getMovies --- method entered");

        Set<Movie> movies = movieService.getAllMovies();

        log.trace("getMovies: movies={}", movies);

        return new MoviesDto(movies);
    }
}