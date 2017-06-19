package server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import server.repository.ClientDBRepository;
import server.repository.MovieDBRepository;
import server.repository.RentDBRepository;

/**
 * Created by Nicu on 4/6/17.
 */

@Configuration
//@ComponentScan()
public class RepoDaoConfig {

    @Bean
    public ClientDBRepository clientDBRepository(){
        return new ClientDBRepository();
    }
    @Bean
    public MovieDBRepository movieDBRepository(){
        return new MovieDBRepository();
    }
    @Bean
    public RentDBRepository rentDBRepository(){
        return new RentDBRepository();
    }
}
