import common.domain.Client;
import common.domain.Movie;
import common.domain.Rent;
import common.domain.validators.*;
import common.CommonService;
import common.Message;
import repository.ClientDBRepository;
import repository.RentDBRepository;
import repository.MovieDBRepository;
import repository.Repository;
import tcp.TcpServer;
import client.service.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

import static java.util.Arrays.asList;

/**
 * Created by paul on 4/2/2017.
 */
public class ServerApp {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        String url = "jdbc:postgresql://localhost:5432/postgres";

        Validator<Movie> movieValidator = new MovieValidator();
        Validator<Client> clientValidator = new ClientValidator();
        Validator<Rent> rentValidator = new RentValidator();


        Repository<Long,Movie> movieRepository = new MovieDBRepository(movieValidator,url,"postgres","123456");
        Repository<Long,Client> clientRepository = new ClientDBRepository(clientValidator,url,"postgres","123456");
        Repository<Long,Rent> rentRepository = new RentDBRepository(rentValidator,url,"postgres","123456");

        MovieService movieService = new MovieService(movieRepository);
        ClientService clientService = new ClientService(clientRepository);
        RentService rentService = new RentService(rentRepository);

        CommonService helloService= new CommonServiceServer(executorService,clientService,movieService,rentService);

        TcpServer tcpServer = new TcpServer(executorService, CommonService.SERVICE_HOST, CommonService.SERVICE_PORT);

        tcpServer.addHandler(CommonService.SAY_HI, (request) -> {
            Future<String> result = helloService.sayHi(request.getBody());
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });
        tcpServer.addHandler(CommonService.SAY_BYE, (request) -> {
            Future<String> result = helloService.sayBye(request.getBody());
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.addHandler(CommonService.ADD_MOVIE,(request)-> {
            List<String> items= Arrays.asList(request.getBody().split(","));
            Long id=Long.valueOf(items.get(0));
            String title=items.get(1);
            String director=items.get(2);
            String genre=items.get(3);
            int copies=Integer.valueOf(items.get(4));

            Movie movie=new Movie(title,director,genre,copies);
            movie.setId(id);

            Future<String> result = helloService.addMovie(movie);
            String bodyError="";
            try{
                return new Message(Message.OK,result.get());
            }catch (InterruptedException | ExecutionException | RentalException e){
                bodyError+=e.getMessage();
            }
            return new Message(Message.ERROR, bodyError);
        });

        tcpServer.addHandler(CommonService.DELETE_MOVIE,(request)-> {
            List<String> items= Arrays.asList(request.getBody().split(","));

            Long id=Long.valueOf(items.get(0));

            Future<String> result = helloService.deleteMovie(id);
            String bodyError="";
            try{
                return new Message(Message.OK,result.get());
            }catch (InterruptedException | ExecutionException | RentalException e){
                bodyError+=e.getMessage();
            }
            return new Message(Message.ERROR, bodyError);
        });

        tcpServer.addHandler(CommonService.UPDATE_MOVIE,(request)-> {
            List<String> items= Arrays.asList(request.getBody().split(","));

            Long id=Long.valueOf(items.get(0));
            String title=items.get(1);
            String director=items.get(2);
            String genre=items.get(3);
            int copies=Integer.valueOf(items.get(4));

            Movie movie=new Movie(title,director,genre,copies);
            movie.setId(id);

            Future<String> result = helloService.updateMovie(movie);

            String bodyError="";
            try{
                return new Message(Message.OK,result.get());
            }catch (InterruptedException | ExecutionException | RentalException e){
                bodyError+=e.getMessage();
            }
            return new Message(Message.ERROR, bodyError);
        });
        tcpServer.addHandler(CommonService.PRINT_ONE_MOVIE,(request)-> {
            Future<String> result = helloService.printOneMovie(Long.valueOf(request.getBody()));
            String bodyError="";
            try{
                return new Message(Message.OK,result.get());
            }catch (InterruptedException | ExecutionException | RentalException e){
                bodyError+=e.getMessage();
            }
            return new Message(Message.ERROR, bodyError);
        });
        tcpServer.addHandler(CommonService.GET_ALL_MOVIES,(request)-> {

            Future<String> result = helloService.getAllMovies();
            String bodyError="";
            try{
                return new Message(Message.OK,result.get());
            }catch (InterruptedException | ExecutionException | RentalException e){
                bodyError+=e.getMessage();
            }
            return new Message(Message.ERROR, bodyError);
        });

        tcpServer.addHandler(CommonService.ADD_CLIENT, (request) -> {
            List<String> items= Arrays.asList(request.getBody().split(","));

            Long id=Long.valueOf(items.get(0));
            String name=items.get(1);
            int age=Integer.valueOf(items.get(2));

            Client client=new Client(name,age);
            client.setId(id);
            Future<String> result = helloService.addClient(client);
            String bodyError="";
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException | RentalException e) {
                bodyError+=e.getMessage();
            }
            return new Message(Message.ERROR, bodyError);
        });

        tcpServer.addHandler(CommonService.DELETE_CLIENT, (request) -> {
            List<String> items= Arrays.asList(request.getBody().split(","));

            Long id=Long.valueOf(items.get(0));
            Future<String> result = helloService.deleteClient(id);
            String bodyError="";
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException | RentalException e) {
                bodyError+=e.getMessage();
            }
            return new Message(Message.ERROR, bodyError);
        });

        tcpServer.addHandler(CommonService.UPDATE_CLIENT, (request) -> {
            List<String> items= Arrays.asList(request.getBody().split(","));

            Long id=Long.valueOf(items.get(0));
            String name=items.get(1);
            int age =Integer.valueOf(items.get(2));

            Client client=new Client(name,age);
            client.setId(id);
            Future<String> result = helloService.updateClient(client);
            String bodyError="";
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException | RentalException e) {
                bodyError+=e.getMessage();
            }
            return new Message(Message.ERROR, bodyError);
        });

        tcpServer.addHandler(CommonService.PRINT_ONE_CLIENT, (request) -> {
            Future<String> result = helloService.printOneClient(Long.valueOf(request.getBody()));
            String bodyError="";
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException | RentalException e) {
                bodyError+=e.getMessage();
            }
            return new Message(Message.ERROR, bodyError);
        });

        tcpServer.addHandler(CommonService.GET_ALL_CLIENTS, (request) -> {
            Future<String> result = helloService.getAllClients();
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.addHandler(CommonService.RENT_MOVIE, (request) -> {
            List<String> items= Arrays.asList(request.getBody().split(","));
            Long id=Long.valueOf(items.get(0));
            Long idC=Long.valueOf(items.get(1));
            Long idM=Long.valueOf(items.get(2));
            int copies=Integer.valueOf(items.get(3));

            Rent rent = new Rent(idC,idM,copies);
            rent.setId(id);
            Future<String> result = helloService.rentMovie(rent);
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.addHandler(CommonService.DETELE_RENTAL, (request) -> {
            List<String> items= Arrays.asList(request.getBody().split(","));

            Long id=Long.valueOf(items.get(0));
            Future<String> result = helloService.deleteRental(id);
            String bodyError="";
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException | RentalException e) {
                bodyError+=e.getMessage();
            }
            return new Message(Message.ERROR, bodyError);
        });

        tcpServer.addHandler(CommonService.GET_ALL_RENTALS, (request) -> {
            Future<String> result = helloService.getAllRentals();
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.addHandler(CommonService.FILTER_MOVIES_BY_NAME, (request) -> {
            Future<String> result = helloService.filterMoviesByName(request.getBody());
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.addHandler(CommonService.FILTER_MOVIES_BY_DIRECTOR, (request) -> {
            Future<String> result = helloService.filterMoviesByDirector(request.getBody());
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });
        tcpServer.addHandler(CommonService.FILTER_MOVIES_BY_TYPE, (request) -> {
            Future<String> result = helloService.filterMoviesByType(request.getBody());
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.addHandler(CommonService.FILTER_CLIENTS_BY_NAME, (request) -> {
            Future<String> result = helloService.filterClientsByName(request.getBody());
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.addHandler(CommonService.FILTER_MOVIES_BY_NUMBER_OF_COPIES, (request) -> {
            Future<String> result = helloService.filterMoviesByNumberOfCopies(Integer.valueOf(request.getBody()));
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.addHandler(CommonService.FILTER_CLIENTS_BY_AGE, (request) -> {
            Future<String> result = helloService.filterClientsByAge(Integer.valueOf(request.getBody()));
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.addHandler(CommonService.NUMBER_OF_CLIENTS_THAT_RENTED_MOVIE_BY_NAME, (request) -> {
            Future<String> result = helloService.reportNumberOfClientsRentedMovie(Long.valueOf(request.getBody()));
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });


        tcpServer.addHandler(CommonService.MOVIES_WITH_MOST_COPIES_AVAILABLE, (request) -> {
            Future<String> result = helloService.reportMoviesWithMostCopies();
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });
        tcpServer.startServer();
    }
}
