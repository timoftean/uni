package common;

import java.util.Set;

/**
 * Created by Nicu on 4/1/17.
 */
public interface MovieService {
    void addMovie(Movie movie);

    Set<Movie> getAllMovies();

    Set<Movie> filterMovieByType(String m);

    void deleteMovie(Movie movie);

    void updateMovie(Movie movie);


}
