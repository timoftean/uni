package client.config;

import client.ui.Console;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Nicu on 4/6/17.
 */

@Configuration
public class ConsoleConfig {
    @Bean
    Console console(){
        return new Console();
    }
}
