package common;

import common.domain.Client;
import common.domain.Movie;
import common.domain.Rent;

import java.util.concurrent.Future;

/**
 * Created by user on 4/1/2017.
 */
public interface CommonService {
    String SERVICE_HOST = "localhost";
    int SERVICE_PORT=1234;

    String SAY_HI = "sayHi";
    String SAY_BYE = "sayBye";

    String ADD_CLIENT = "addClient";
    String GET_ALL_CLIENTS="getAllClients";
    String PRINT_ONE_CLIENT="printOneClient";
    String DELETE_CLIENT = "deleteClient";
    String UPDATE_CLIENT = "updateClient";

    String ADD_MOVIE = "addMovie";
    String GET_ALL_MOVIES = "getAllMovies";
    String PRINT_ONE_MOVIE = "printOneMovie";
    String DELETE_MOVIE = "deleteMovie";
    String UPDATE_MOVIE = "updateMovie";

    String RENT_MOVIE = "rentMovie";
    String GET_ALL_RENTALS = "getAllRentals";
    String DETELE_RENTAL = "deleteRental";

    String FILTER_MOVIES_BY_NAME = "filterMoviesByName";
    String FILTER_MOVIES_BY_DIRECTOR = "filterMoviesByDirector";
    String FILTER_MOVIES_BY_TYPE = "filterMoviesByType";
    String FILTER_MOVIES_BY_NUMBER_OF_COPIES = "filterMoviesByNumberOfCopies";

    String FILTER_CLIENTS_BY_NAME = "filterClientsByName";
    String FILTER_CLIENTS_BY_AGE = "filterClientsByAge";

    String NUMBER_OF_CLIENTS_THAT_RENTED_MOVIE_BY_NAME = "reportNumberOfClientsRentedMovie";
    String MOVIES_WITH_MOST_COPIES_AVAILABLE = "reportMoviesWithMostCopies";

    Future<String> sayHi(String name);

    Future<String> sayBye(String name);

    Future<String> addClient(Client client);

    Future<String> getAllClients();

    Future<String> printOneClient(Long id);

    Future<String> deleteClient(Long id);

    Future<String> updateClient(Client client);

    Future<String> addMovie(Movie movie);

    Future<String> getAllMovies();

    Future<String> printOneMovie(Long id);

    Future<String> deleteMovie(Long id);

    Future<String> updateMovie(Movie movie);

    Future<String> rentMovie(Rent rent);

    Future<String> getAllRentals();

    Future<String> deleteRental(Long id);

    Future<String> filterMoviesByName(String name);

    Future<String> filterMoviesByDirector(String director);

    Future<String> filterMoviesByType(String type);

    Future<String> filterMoviesByNumberOfCopies(int nocopies);

    Future<String> filterClientsByName(String name);

    Future<String> filterClientsByAge(int age);

    Future<String> reportNumberOfClientsRentedMovie(Long id);

    Future<String> reportMoviesWithMostCopies();
}
