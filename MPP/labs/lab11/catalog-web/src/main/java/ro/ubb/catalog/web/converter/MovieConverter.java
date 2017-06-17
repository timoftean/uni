package ro.ubb.catalog.web.converter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Movie;
import ro.ubb.catalog.web.dto.MovieDto;

import java.util.stream.Collectors;

/**
 * Created by paul on 4/29/2017.
 */
@Component
public class MovieConverter extends BaseConverter<Movie, MovieDto> {

    private static final Logger log = LoggerFactory.getLogger(MovieConverter.class);

    @Override
    public MovieDto convertModelToDto(Movie movie) {
        MovieDto movieDto = MovieDto.builder()
                .name(movie.getName())
                .director(movie.getDirector())
                .genre(movie.getGenre())
                .availableCopies(movie.getAvailableCopies())
                .build();
        movieDto.setId(movie.getId());
        movieDto.setClients(movie.getClients().stream()
                .map(client -> client.getId())
                .collect(Collectors.toSet()));
        return movieDto;
    }
}

//@Component
//public class MovieConverter extends  BaseConverter<Movie, MovieDto> {
//    private static final Logger log = LoggerFactory.getLogger(MovieConverter.class);
//
//    @Override
//    public MovieDto convertModelToDto(Movie movie){
//
//        MovieDto movieDto = new MovieDto(movie.getName(), movie.getDirector(),movie.getGenre(), movie.getAvailableCopies());
//        movieDto.setId(movie.getId());
//
//        return movieDto;
//
//    }
//}
