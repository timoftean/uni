package client.service;

import common.CommonService;
import common.domain.Client;
import common.domain.Movie;
import common.domain.Rent;
import common.domain.validators.RentalException;
import common.domain.validators.ValidatorException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by paul on 4/2/2017.
 */
public class CommonServiceServer implements CommonService {
    
    private ExecutorService executorService;
    private ClientService clientService;
    private MovieService movieService;
    private RentService rentService;

    public CommonServiceServer(ExecutorService executorService, ClientService clientService, MovieService movieService, RentService rentService) {
        this.executorService = executorService;
        this.clientService = clientService;
        this.movieService = movieService;
        this.rentService = rentService;
    }


    @Override
    public Future<String> sayHi(String name) {
        Future<String> result = executorService.submit(() -> "Hi " + name);
        return result;
    }

    @Override
    public Future<String> sayBye(String name) {
        Future<String> result = executorService.submit(() -> "Bye " + name);
        return result;
    }

    @Override
    public Future<String> addClient(Client client) {
        Future<String> result=executorService.submit(()->{
            clientService.addClient(client);
            return "Client successfully added";
        });
        return result;
    }

    @Override
    public Future<String> getAllClients() {
        Future<String> result=executorService.submit(()->clientService.getAllClients().toString());
        return result;
    }

    @Override
    public Future<String> printOneClient(Long id) {
        Future<String> result=executorService.submit(()->clientService.getOneClient(id).toString());
        return result;
    }

    @Override
    public Future<String> deleteClient(Long id) {
        Future<String> result=executorService.submit(()->{
            try{
                clientService.deleteClient(id);
            }catch (RentalException e){
                return e.getMessage();
            }
            return "Client successfully deleted";
        });
        return result;
    }

    @Override
    public Future<String> updateClient(Client client) {
        Future<String> result=executorService.submit(()->{
            try{
                clientService.updateClient(client);
            }catch (ValidatorException e){
                return e.getMessage();
            }catch (RentalException e){
                return e.getMessage();
            }
            return "Client successfully updated";
        });
        return result;
    }

    @Override
    public Future<String> addMovie(Movie movie) {
        Future<String> result=executorService.submit(()->{
            try{
                movieService.addMovie(movie);
            }catch (ValidatorException e){
                return e.getMessage();
            }catch (RentalException e){
                return e.getMessage();
            }
            return "Movie successfully added";
        });
        return result;
    }

    @Override
    public Future<String> getAllMovies() {
        Future<String> result=executorService.submit(()->movieService.getAllMovies().toString());
        return result;
    }

    @Override
    public Future<String> printOneMovie(Long id) {
        Future<String> result=executorService.submit(()->movieService.getOneMovie(id).toString());
        return result;
    }

    @Override
    public Future<String> deleteMovie(Long id) {
        Future<String> result=executorService.submit(()->{
            try{
                movieService.deleteMovie(id);
            }catch (ValidatorException e){
                return e.getMessage();
            }catch (RentalException e){
                return e.getMessage();
            }
            return "Movie successfully deleted";
        });
        return result;
    }

    @Override
    public Future<String> updateMovie(Movie movie) {
        Future<String> result=executorService.submit(()->{
            try{
                movieService.updateMovie(movie);
            }catch (ValidatorException e){
                return e.getMessage();
            }catch (RentalException e){
                return e.getMessage();
            }
            return "Movie successfully updated";
        });
        return result;
    }

    @Override
    public Future<String> rentMovie(Rent rent) {
        Future<String> result=executorService.submit(()->{
            try{
                rentService.addRent(rent);
            }catch (ValidatorException e){
                return e.getMessage();
            }catch (RentalException e){
                return e.getMessage();
            }
            return "Rent successfully deleted";
        });
        return result;
    }

    @Override
    public Future<String> getAllRentals() {
        Future<String> result=executorService.submit(()->rentService.getAllRents().toString());
        return result;
    }

    @Override
    public Future<String> deleteRental(Long id) {
        Future<String> result=executorService.submit(()->{
            try{
                rentService.deleteRent(id);
            }catch (ValidatorException e){
                return e.getMessage();
            }catch (RentalException e){
                return e.getMessage();
            }
            return "Rent successfully deleted";
        });
        return result;
    }

    @Override
    public Future<String> filterMoviesByName(String name) {
        Future<String> result=executorService.submit(()->movieService.filterMoviesByName(name).toString());
        return result;
    }

    @Override
    public Future<String> filterMoviesByDirector(String director) {
        Future<String> result=executorService.submit(()->movieService.filterMoviesByDirector(director).toString());
        return result;
    }

    @Override
    public Future<String> filterMoviesByType(String type) {
        Future<String> result=executorService.submit(()->movieService.filterMoviesByType(type).toString());
        return result;
    }

    @Override
    public Future<String> filterMoviesByNumberOfCopies(int nocopies) {
        Future<String> result=executorService.submit(()->movieService.filterMoviesByNumberOfCopies(nocopies).toString());
        return result;
    }

    @Override
    public Future<String> filterClientsByName(String name) {
        Future<String> result=executorService.submit(()->clientService.filterClientsByName(name).toString());
        return result;
    }

    @Override
    public Future<String> filterClientsByAge(int age) {
        Future<String> result=executorService.submit(()->clientService.filterClientsByAge(age).toString());
        return result;
    }

    @Override
    public Future<String> reportNumberOfClientsRentedMovie(Long id) {
        Future<String> result=null;
        return result;
    }

    @Override
    public Future<String> reportMoviesWithMostCopies() {
        Future<String> result=null;
        return result;
    }
}
