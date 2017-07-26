package repository;

import domain.Client;
import domain.Movie;
import domain.Rent;
import domain.validators.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by paul on 3/21/2017.
 */
public class MovieRepositoryTest {

    private static final Long MID = new Long(8);
    private static final String MNAME = new String("movieName");
    private static final String GENRE = new String("action");
    private static final String NEW_GENRE = new String("nobueno");
    private static final String DIRECTOR = new String("movieDirector");
    private static final int AVAILABLE_COPIES = 1;


    private Movie movie,notValidMovie;

    private Repository<Long, Movie> movieRepository;
    private Validator<Movie> movieValidator = new MovieValidator();

    @Before
    public void setUp() throws Exception {
        movieRepository = new MovieFileRepository(movieValidator,"movieTestFile.txt");

        movie = new Movie(MNAME, DIRECTOR, GENRE, AVAILABLE_COPIES);
        movie.setId(MID);

        notValidMovie = new Movie(MNAME, DIRECTOR, NEW_GENRE, AVAILABLE_COPIES);
        notValidMovie.setId(MID);
    }

    @After
    public void tearDown() throws Exception {
        movie = null;
    }


    @Test
    public void testFindOne() throws Exception {
        assertEquals("Should return empty", Optional.empty(), movieRepository.findOne((long) 10));
        assertNotEquals("Should return a movie", Optional.empty(),movieRepository.findOne((long) 1 ));
    }


    @Test
    public void testFindAll() throws Exception {
        Set<Movie> movies = (Set<Movie>) movieRepository.findAll();
        assertEquals("Should be 7",7,movies.size());
    }



    @Test
    public void testSave() throws Exception {
        Set<Movie> movies = (Set<Movie>) movieRepository.findAll();
        assertEquals("Should be 7",7,movies.size());
        movieRepository.save(movie);
        movies =(Set<Movie>)movieRepository.findAll();
        assertEquals("Should be 8",8,movies.size());
    }

    @Test(expected = ValidatorException.class)
    public void testSaveException() throws Exception {
        movieRepository.save(notValidMovie);
    }

    @Test
    public void testDelete() throws Exception {
        Set<Movie> movies = (Set<Movie>) movieRepository.findAll();
        assertEquals("Should be 8",8,movies.size());
        movieRepository.delete(MID);
        movies =(Set<Movie>)movieRepository.findAll();
        assertEquals("Should be 7",7,movies.size());
    }

    @Test
    public void testUpdate() throws Exception {

    }


    @Test(expected = RentalException.class)
    public void testUpdateException() throws Exception {
        movieRepository.update(notValidMovie);
    }
}
