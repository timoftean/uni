package server;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/**
 * Created by Nicu on 4/1/17.
 */
public class ServerApp {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext("server.config");
        System.out.println("server running ...");
    }
}
