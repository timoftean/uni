import domain.Client;
import domain.Movie;

import domain.Rent;
import domain.validators.ClientValidator;
import domain.validators.MovieValidator;
import domain.validators.RentValidator;
import domain.validators.Validator;
import repository.*;
import service.ClientService;
import service.MovieService;
import service.RentService;
import ui.Console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Nicu on 3/5/2017.
 */
public class main {
    public static void main(String[] args) {

        Validator<Movie> movieValidator = new MovieValidator();
        Validator<Client> clientValidator = new ClientValidator();
        Validator<Rent> rentValidator = new RentValidator();

        Repository<Long, Movie> movieRepository;
        Repository<Long, Client> clientRepository;
        Repository<Long, Rent> rentRepository;

        MovieService movieService;
        ClientService clientService;
        RentService rentService;

        Console console;

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        String command;
        try{
                System.out.println(
                        "Choose workspace data load type: \n" +
                                "\t1. empty workspace \n"+
                                "\t2. text file \n"+
                                "\t3. xml file \n" +
                                "\t4. database file \n" +
                                "\t0. exit"

                );
                command = bufferRead.readLine();
                switch (command){
                    case "1":
                        movieRepository = new InMemoryRepository<>(movieValidator);
                        clientRepository = new InMemoryRepository<>(clientValidator);
                        rentRepository = new InMemoryRepository<>(rentValidator);

                        movieService = new MovieService(movieRepository);
                        clientService= new ClientService(clientRepository);
                        rentService = new RentService(rentRepository);

                        console = new Console(movieService, clientService, rentService);
                        console.runConsole();
                    case "2":
                        movieRepository = new MovieFileRepository(movieValidator, "movieFile.txt");
                        clientRepository = new ClientFileRepository(clientValidator,"clientFile.txt");
                        rentRepository = new RentFileRepository(rentValidator,"rentFile.txt");

                        movieService = new MovieService(movieRepository);
                        clientService = new ClientService(clientRepository);
                        rentService = new RentService(rentRepository);

                        console = new Console(movieService, clientService, rentService);
                        console.runConsole();
                    case "3":
                        movieRepository = new MovieXmlRepository(movieValidator);
                        clientRepository = new ClientXmlRepository(clientValidator);
                        rentRepository = new RentXmlRepository(rentValidator);

                        movieService = new MovieService(movieRepository);
                        clientService = new ClientService(clientRepository);
                        rentService = new RentService(rentRepository);

                        console = new Console(movieService, clientService, rentService);
                        console.runConsole();
                    case "4":
                        String url = "jdbc:postgresql://localhost:5432/postgres";
                        movieRepository = new MovieDBRepository(movieValidator,url,"postgres","123456");
                        clientRepository = new ClientDBRepository(clientValidator,url,"postgres","123456");
                        rentRepository = new RentDBRepository(rentValidator,url,"postgres","123456");

                        movieService = new MovieService(movieRepository);
                        clientService = new ClientService(clientRepository);
                        rentService = new RentService(rentRepository);

                        console = new Console(movieService, clientService, rentService);
                        console.runConsole();

                    case "0":
                        System.exit(0);
                    default:
                        System.out.println("Invalid command ! Bye!");
                }
        }catch (IOException e){
                e.printStackTrace();
        }

    }

}
