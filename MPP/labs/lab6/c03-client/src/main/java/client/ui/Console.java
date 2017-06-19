package client.ui;

import client.service.ClientServiceClient;
import client.service.MovieServiceClient;
import client.service.RentServiceClient;
import common.Client;
import common.Movie;
import common.Rent;

import common.validators.RentalException;
import common.validators.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.IntSummaryStatistics;
import java.util.Set;

/**
 * Created by Nicu on 3/5/2017.
 */
@Component
public class Console {
    // region Fields
    @Autowired
    private MovieServiceClient movieService;

    @Autowired
    private ClientServiceClient clientService;

    @Autowired
    private RentServiceClient rentService;
    //endregion


    //region Constructor
    public Console() {

    }

    //endregion

    public MovieServiceClient getMovieService() {
        return movieService;
    }

    public void setMovieService(MovieServiceClient movieService) {
        this.movieService = movieService;
    }

    public RentServiceClient getRentService() {
        return rentService;
    }

    public void setRentService(RentServiceClient rentService) {
        this.rentService = rentService;
    }

    public ClientServiceClient getClientService() {
        return clientService;
    }

    public void setClientService(ClientServiceClient clientService) {
        this.clientService = clientService;
    }



    // region Menus
    public void runConsole() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        String command;
        while(true){
            try{
                System.out.println(
                        "MAIN MENU \n" +
                                "1. movie menu \n"+
                                "2. client menu \n"+
                                "3. rent menu \n" +
                                "0. exit"

                );
                command = bufferRead.readLine();
                switch (command){
                    case "1":
                        movieMenu();
                        return;
                    case "2":
                        clientMenu();
                        return;
                    case "3":
                        rentMenu();
                        return;
                    case "0":
                        System.exit(0);
                    default:
                        System.out.println("Invalid command !");
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void movieMenu(){
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        String command;
        while(true){
            try{
                printAllMovies();
                System.out.println(
                                "MOVIE MENU \n" +
                                "1. add movie \n"+
                                "2. delete movie \n"+
                                "3. update movie \n" +
                                "4. filter movie \n" +
                                        "5. Print the number of the clients having the minimum age.\n"+
                                "0. exit"

                );
                command = bufferRead.readLine();
                switch (command){
                    case "1":
                        addMovies();
                        break;
                    case "2":
                        deleteMovies();
                        break;
                    case "3":
                        updateMovies();
                        break;
                    case "4":
                        filterMovies();
                        break;
                    case "5":
                        break;
                    case "0":
                        System.exit(0);
                    default:
                        System.out.println("Invalid command !");
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void clientMenu(){
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        String command;
        while(true){
            try{
                printAllClient();
                System.out.println(
                                "CLIENT MENU \n"+
                                "1. add client \n"+
                                "2. delete client \n"+
                                "3. update client \n" +
                                "4. filter client \n" +
                                "0. exit"

                );
                command = bufferRead.readLine();
                switch (command){
                    case "1":
                        addClients();
                        break;
                    case "2":
                        deleteClients();
                        break;
                    case "3":
                        updateClients();
                        break;
                    case "4":
                        filterClients();
                        break;
                    case "0":
                        System.exit(0);
                    default:
                        System.out.println("Invalid command !");
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void rentMenu(){
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        String command;
        while(true){
            try{
                printAllRents();
                System.out.println(
                                "RENT MENU \n"+
                                "1. add rent \n"+
                                "2. delete rent \n"+
                                "3. update rent \n" +
                                "4. filter rent \n" +
                                "5. Client which rented the movie with the most no of copies \n"+
                                "0. exit"

                );
                command = bufferRead.readLine();
                switch (command){
                    case "1":
                        addRents();
                        break;
                    case "2":
                        deleteRents();
                        break;
                    case "3":
                        updateRents();
                        break;
                    case "4":
                        filterRents();
                        break;
                    case "5":
                        statistics();
                        break;
                    case "0":
                        System.exit(0);
                    default:
                        System.out.println("Invalid command !");
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    private void statistics(){
        Set<Rent> rents =rentService.getAllRents();
        Set<Movie> movies =movieService.getAllMovies();
        Set<Client> clients = clientService.getAllClients();
        IntSummaryStatistics summaryStatistics = movies.stream()
                .map(Movie::getAvailableCopies)
                .mapToInt(Integer::intValue)
                .summaryStatistics();
        int maxCopies = summaryStatistics.getMax();
//        System.out.println("copies " + maxCopies);
        movies.removeIf(m->m.getAvailableCopies()!=maxCopies);
//        System.out.println("movies " + movies);
        int movieId = movies.iterator().next().getId();
//        System.out.println("moviedId " + movieId);
        rents.removeIf(r->r.getMovieId()!=movieId);
//        System.out.println("rents " + rents);
        int clientId = rents.iterator().next().getClientCnp();
//        System.out.println("clientId " + clientId);
        clients.removeIf(c->c.getCnp()!=clientId);
        System.out.println("client: " + clients);
    }

    //endregion

    //region MovieService

    private void filterMovies() {
        System.out.println("filtered movies (type 'action'):");
        Set<Movie> movies = movieService.filterMovieByType("action");
        movies.stream().forEach(System.out::println);
        System.out.println("--------------------------------------------------------");
    }

    private void printAllMovies() {
        Set<Movie> movies = movieService.getAllMovies();
        movies.stream().forEach(System.out::println);
    }

    private void addMovies() {
        while (true) {
            Movie movie = readMovie();
            if (movie == null || movie.getId() < 0) {
                break;
            }
            try {
                movieService.addMovie(movie);
            } catch (ValidatorException e) {
                System.out.println("Error" + e);
            }
        }
    }

    private void deleteMovies() {
            Movie movie = readMovie();
            try {
                movieService.deleteMovie(movie);
            } catch (ValidatorException e) {
                System.out.println("Error" + e);
            } catch (RentalException e) {
                System.out.println("Error" + e);
            }
    }

    private void updateMovies() {
            Movie movie = readMovie();
            try {
                movieService.updateMovie(movie);
            } catch (ValidatorException e) {
                System.out.println(e);
            } catch (RentalException e) {
                System.out.println("Error" + e);
            }
    }

    private Movie readMovie() throws RentalException {
        System.out.println("Read movie {id, name, director, genre, availableCopies}: ");

        Movie movie;
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("movie id(int): ");
            int id = Integer.parseInt(bufferRead.readLine());
            if (id<0){
                Movie m =  new Movie();
                return m;
            }else{
                System.out.println("movie name(string): ");
                String name = bufferRead.readLine();
                System.out.println("movie director(string): ");
                String director = bufferRead.readLine();
                System.out.println("movie genre(string): ");
                String type = bufferRead.readLine();
                System.out.println("available copies(int): ");
                int availableCopies = Integer.parseInt(bufferRead.readLine());// ...
                movie = new Movie(id,name, director, type, availableCopies);
            }
            return movie;
        }catch (ValidatorException e){
            System.out.println("Error: " + e);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    //endregion

    //region ClientService
    private void addClients(){
        while (true) {
            try {
            Client cl = readClient();
            if (cl == null || cl.getCnp() < 0)
                break;

                clientService.addClient(cl);
            } catch (ValidatorException e) {
                System.out.println("Error: "+ e);
            }
        }
    }

    private void printAllClient() {
        Set<Client> clients=clientService.getAllClients();
        clients.stream().forEach(System.out::println);
    }

    private Client readClient(){
        System.out.println("Read Client {cnp,name}: ");

        Client client;
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Cnp(int): ");
            int cnp= Integer.parseInt(bufferRead.readLine());

            if (cnp<0){
                return null;
            }else{
                System.out.println("Client name(string): ");
                String name = bufferRead.readLine();
                client= new Client(name,cnp);
            }
            return client;
        }catch (ValidatorException e){
            System.out.println("Error: " + e);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void updateClients() {
            Client cl = readClient();
            try {
                clientService.updateClient(cl);
            } catch (ValidatorException e) {
                System.out.println("Error: "+ e);
            }
    }

    private void filterClients() {
        System.out.println("filtered clients (age = 15):");
        Set<Client> clients = clientService.filterClientsByAge(15);
        clients.stream().forEach(System.out::println);
        System.out.println("--------------------------------------------------------");
    }

    private void deleteClients() {
        Client cl = readClient();
        try {
            clientService.deleteClient(cl);
        } catch (ValidatorException e) {
            System.out.println("Error: "+ e);
        }
    }


    //endregion

    //region RentalService
    private void printAllRents() {
        try{
            Set<Rent> rents =rentService.getAllRents();
            rents.stream().forEach(System.out::println);

        }catch (RentalException e){
            System.out.println(e);
        }
    }
    private Rent readRental(){

        System.out.println("Read Rental {MovieId, ClientCnp, Number of copies}: ");

        Rent rent;
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Movie ID(int): ");
            int mID= Integer.parseInt(bufferRead.readLine());
            if (mID<0){
                return null;
            }else{
                System.out.println("Client CNP(int): ");
                int cID= Integer.parseInt(bufferRead.readLine());
                 System.out.println("Number of copies(int): ");
                int noc= Integer.parseInt(bufferRead.readLine());
                rent =new Rent(cID,mID,noc);
            }
            return rent;
        }catch (ValidatorException e){
            System.out.println("Error: " + e);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void addRents() {
        while (true) {
            try {
                Rent r = readRental();
                if (r == null || r.getClientCnp() < 0)
                    break;

                rentService.addRent(r);
            } catch (ValidatorException e) {
                System.out.println("Error: " + e);
            }

        }
    }

    private void deleteRents() {
        Rent r = readRental();
        try {
            rentService.deleteRent(r);
        } catch (ValidatorException e) {
            System.out.println("Error: " + e);
        }
    }

    private void filterRents() {
            System.out.println("filtered rents (number of copies=5):");
            Set<Rent> rents = rentService.filterRentsByNOC(5);
            rents.stream().forEach(System.out::println);
            System.out.println("--------------------------------------------------------");
    }

    private void updateRents() {
        Rent r = readRental();
        try {
            rentService.updateRent(r);
        } catch (ValidatorException e) {
            System.out.println("Error: " + e);
        }
    }
    //endregion
}
