package client.ui;

import common.CommonService;
import common.CommonServiceException;
import common.domain.Client;
import common.domain.Movie;
import common.domain.Rent;
import common.domain.validators.ValidatorException;
//import javafx.util.Pair;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Nicu on 3/5/2017.
 */

public class Console {
    private CommonService commonService;

    public Console(CommonService commonService) {
        this.commonService = commonService;
    }

    public void RunUi() {
        runConsole();

        commonService.sayHi("Mike");
        commonService.sayBye("Mike");
    }

    public String printOptions() {
        String menu;
        menu = "Rents app\n" +
                "1. Add Client\n" +
                "2. Print all Clients\n" +
                "3. Print one Client\n" +
                "4. Delete one Client\n" +
                "5. Update one Client\n\n" +
                "6. Add Movie\n" +
                "7. Print all Movies\n" +
                "8. Print one Movie\n" +
                "9. Delete one Movie\n" +
                "10. Update one Movie\n\n" +
                "11. Rent Movie\n" +
                "12. Return movie\n" +
                "13. Print all movies that were bought at least once\n\n"+
                "14. Filter Movies by Director\n" +
                "15. Filter Movies by Number of Copies\n" +
                "16. Filter Clients by Name\n" +
                "17. Filter Clients by Age\n" +
                "18. Filter Movies by Name\n\n" +
//                "19. Filter Movies by Director\n" +
                "19. Filter Movies by Genre\n\n"+
                "20. Count Clients that rented Movie with title\n\n"+
                "0. Exit\n";

        return menu;
    }

    public void runConsole(){
        System.out.println(printOptions());
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            while(true) {
                try {
                    int option = Integer.parseInt(bufferRead.readLine());
                    switch (option) {
                        case 1:
                            addClients();
                            break;
                        case 2:
                            getAllClients();
                            break;
                        case 3:
                            printOneClient();
                            break;
                        case 4:
                            deleteClient();
                            break;
                        case 5:
                            updateClient();
                            break;
                        case 6:
                            addMovies();
                            break;
                        case 7:
                            getAllMovies();
                            break;
                        case 8:
                            printOneMovie();
                            break;
                        case 9:
                            deleteMovie();
                            break;
                        case 10:
                            updateMovie();
                            break;
                        case 11:
                            rentMovie();
                            break;
                        case 12:
                            returnMovie();
                            break;
                        case 13:
                            printAllRentedMovies();
                            break;
                        case 14:
                            filterMoviesByDirector();
                            break;
                        case 15:
                            filterMoviesByNoCopies();
                            break;
                        case 16:
                            filterClientsByName();
                            break;
                        case 17:
                            filterClientsByAge();
                            break;
                        case 18:
                            filterMoviesbyName();
                            break;
                        case 19:
                            filterMoviesByGenre();
                            break;
                        case 20:
                            countClientsByRentedMovie();
                            break;
                        case 0:
                            return;
                        default:
                            System.out.println("Invalid\n");
                    }
                    System.out.println(printOptions());
                }
                catch(NumberFormatException e){
                    System.out.println("Wrong input");
                }
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    private void rentMovie() {
        while (true) {
            Rent rent = readRent();
            if (rent == null || rent.getId() < 0 ) {
                break;
            }
            Future<String> result = commonService.rentMovie(rent);
            printFutureResult(result);

        }
    }

    private Rent readRent() {
        System.out.println("Rent movie for client {rentId}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long rentId = Long.valueOf(bufferRead.readLine());// ...
//            Long idMovie = Long.valueOf(bufferRead.readLine());// ...

//            Pair<Long, Long> pair = new Pair<>(idClient, idMovie);

            Rent rent = new Rent();
            rent.setId(rentId);

            return rent;
        } catch (IOException | NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    private void deleteMovie() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
//        while (true) {
            try {
                System.out.println("Read book {id}");
                Long id = Long.valueOf(bufferRead.readLine());// ...
                if (id > 0) {
                    Future<String> result = commonService.deleteMovie(id);
                    printFutureResult(result);
//                    break;
                }
                System.out.println("Invalid Id");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
//        }
    }

    private void printOneMovie() {
        System.out.println("Insert movie id {id}");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferedReader.readLine());

            if (id > 0) {
                Future<String> result = commonService.printOneMovie(id);

                printFutureResult(result);
//                break;
            }
            System.out.println("Invalid id");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private void getAllMovies() {
        Future<String> result = commonService.getAllMovies();
        printFutureResult(result);
    }

    private void deleteClient() {
        System.out.println("Read client {id}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                Long id = Long.valueOf(bufferRead.readLine());// ...
                if (id > 0) {
                    Future<String> result=commonService.deleteClient(id);
                    printFutureResult(result);
                    break;
                }
                System.out.println("Invalid Id");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void updateClient() {
        while (true) {
            Client client = readClient();
            if (client == null || client.getId() < 0) {
                break;
            }
            Future<String> result = commonService.updateClient(client);
            printFutureResult(result);
        }
    }

    private void updateMovie() {
        while(true) {
            Movie movie = readMovie();
            if(movie == null || movie.getId() < 0) {
                break;
            }

            Future<String> result = commonService.updateMovie(movie);
            printFutureResult(result);
        }
    }

    private void getAllClients() {
        Future<String> result = commonService.getAllClients();
        printFutureResult(result);
    }

    private void printOneClient() {
        System.out.println("Insert client id {id}");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        while(true) {
            try {
                Long id = Long.valueOf(bufferedReader.readLine());
                if (id > 0) {
                    Future<String> result = commonService.printOneClient(id);

                    printFutureResult(result);
//                    break;
                }
                System.out.println("Invalid ID");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
//        }
    }

    private void printFutureResult(Future<String> result) {
        CompletableFuture.supplyAsync(()->{
            try {
                return result.get();
            } catch(InterruptedException e){
                throw new CommonServiceException(e.getMessage());
            } catch(ExecutionException e){
                throw new CommonServiceException(e.getMessage());
            }
        }).thenRun(System.out::println);
    }

    private void addClients() {
        while (true) {
            Client client = readClient();
            if(client != null || client.getId() < 0) {
                break;
            }
            try {
                Future<String> result = commonService.addClient(client);
                printFutureResult(result);
            } catch (ValidatorException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Client readClient() {
        System.out.println("Read client {id, name, age}");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferedReader.readLine());
            String name = bufferedReader.readLine();
            int age = Integer.parseInt(bufferedReader.readLine());

            Client client = new Client(name, age);
            client.setId(id);
            return client;
        } catch(IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Movie readMovie() {
        System.out.println("Read movie {id, name, director, genre, availableCopies}");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferedReader.readLine());
            String name = bufferedReader.readLine();
            String director = bufferedReader.readLine();
            String genre = bufferedReader.readLine();
            int availableCopies = Integer.parseInt(bufferedReader.readLine());

            Movie movie = new Movie(name, director, genre, availableCopies);
            movie.setId(id);
            return movie;
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void addMovies() {
        while(true) {
            Movie movie = readMovie();

            if(movie == null || movie.getId()<0)
                break;

            Future<String> result = commonService.addMovie(movie);
            printFutureResult(result);
        }
    }
}
