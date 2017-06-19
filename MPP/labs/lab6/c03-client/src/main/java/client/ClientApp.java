package client;

import client.service.ClientServiceClient;
import client.service.MovieServiceClient;
import client.service.RentServiceClient;
import client.ui.Console;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Nicu on 3/5/2017.
 */
public class ClientApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("client.config");

//        MovieServiceClient movieService = context.getBean(MovieServiceClient.class);
//        RentServiceClient rentService = context.getBean(RentServiceClient.class);
//        ClientServiceClient clientService = context.getBean(ClientServiceClient.class);
//
//        Console console;
//
//        console = new Console();
//        console.runConsole();

        Console console = context.getBean(Console.class);
        console.runConsole();

    }

}
