package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Client;
import ro.ubb.catalog.core.model.Movie;
import ro.ubb.catalog.core.repository.ClientRepository;
import ro.ubb.catalog.core.repository.MovieRepository;
import ro.ubb.catalog.core.repository.RentRepository;

import java.util.List;
import java.util.Map;
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

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Movie> findAll() {
        log.trace("findAll");

        List<Movie> movies = movieRepository.findAll();

        log.trace("findAll: movies={}", movies);

        return movies;
    }

    @Override
    public Movie findMovie(Long movieId) {
        log.trace("findMovie: movieId={}", movieId);

        Movie movie = movieRepository.findOne(movieId);

        log.trace("findMovie: movie={}", movie);

        return movie;
    }

    @Override
    @Transactional
    public Movie updateMovie(Long movieID, String name, String director, String genre, Integer availableCopies,Set<Long> clients) {
        log.trace("updateMovie: movieId={},name={}, director={}, genre={}, availableCopies={}, clients={}",
                movieID, name, director,genre,availableCopies,clients);

        Movie movie = movieRepository.findOne(movieID);
        movie.setName(name);
        movie.setDirector(director);
        movie.setGenre(genre);
        movie.setAvailableCopies(availableCopies);
        movie.getClients().stream()
                .map(d -> d.getId())
                .forEach(id -> {
                    if (clients.contains(id)) {
                        clients.remove(id);
                    }
                });
        List<Client> clientList = clientRepository.findAll(clients);
        clientList.stream().forEach(c -> movie.addClient(c));

        log.trace("updateMovie: movie={}", movie);

        return movie;
    }

    @Override
    public Movie createMovie(String name, String director, String genre, Integer availableCopies) {
        log.trace("createMovie: name={}, director={}, genre={}, availableCopies={}",
                name, director,genre,availableCopies);

        Movie movie = Movie.builder()
                .name(name)
                .director(director)
                .genre(genre)
                .availableCopies(availableCopies)
                .build();

        Movie m = movieRepository.save(movie);

        log.trace("createMovie: movie={}", m);

        return m;
    }

    @Override
    public void deleteMovie(Long movieId) {
        log.trace("deleteMovie: movieId={}", movieId);

        movieRepository.delete(movieId);

        log.trace("deletemovie - method end");
    }

    @Override
    @Transactional
    public Movie updateMovieNocopies(Long movieid, Map<Long, Integer> nocopies) {
        log.trace("updateMovieNocopies: movieid={}, nocopies={}", movieid, nocopies);

        Movie movie = movieRepository.findOne(movieid);
        movie.getRents().stream()
                .forEach(r -> r.setNocopies(nocopies.get(r.getClient().getId())));

        log.trace("updateMovieNocopies: movie={}", movie);
        return movie;
    }


}
