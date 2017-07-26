package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 3/20/2017.
 */
public class MovieTest {
    private static final Long ID = new Long(1);
    private static final Long NEW_ID = new Long(2);
    private static final String NAME = new String("movieName");
    private static final String NEW_NAME = new String("newMovieName");
    private static final String GENRE = new String("genre");
    private static final String NEW_GENRE = new String("newGenre");
    private static final String DIRECTOR = new String("movieDirector");
    private static final String NEW_DIRECTOR = new String("newMovieDirector");
    private static final int AVAILABLE_COPIES = 1;
    private static final int NEW_AVAILABLE_COPIES = 2;

    private Movie movie;

    @Before
    public void setUp() throws Exception {
        movie = new Movie(NAME, DIRECTOR, GENRE, AVAILABLE_COPIES);
        movie.setId(ID);
    }

    @After
    public void tearDown() throws Exception {
        movie = null;
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals("Ids should be equal", ID, movie.getId());
    }

    @Test
    public void testSetId() throws Exception {
        movie.setId(NEW_ID);
        assertEquals("IDs should be equal", NEW_ID, movie.getId());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("Names should be equal", NAME, movie.getName());
    }

    @Test
    public void testSetName() throws Exception {
        movie.setName(NEW_NAME);
        assertEquals("Names should be equal", NEW_NAME, movie.getName());
    }

    @Test
    public void testGetDirector() throws Exception {
        assertEquals("Directors should be equal", DIRECTOR, movie.getDirector());
    }

    @Test
    public void testSetDirector() throws Exception {
        movie.setDirector(NEW_DIRECTOR);
        assertEquals("Directors should be equal", NEW_DIRECTOR, movie.getDirector());
    }

    @Test
    public void testGetGenre() throws Exception {
        assertEquals("Genres should be equal", GENRE, movie.getGenre());
    }

    @Test
    public void testSetGenre() throws Exception {
        movie.setGenre(NEW_GENRE);
        assertEquals("Genres should be equal", NEW_GENRE, movie.getGenre());
    }

    @Test
    public void testGetAvailableCopies() throws Exception {
        assertEquals("Available copies should be equal", AVAILABLE_COPIES, movie.getAvailableCopies());
    }

    @Test
    public void testSetAvailableCopies() throws Exception {
        movie.setAvailableCopies(NEW_AVAILABLE_COPIES);
        assertEquals("Available copies should be equal", NEW_AVAILABLE_COPIES, movie.getAvailableCopies());
    }
}
