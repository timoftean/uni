package client.config;

import client.service.ClientServiceClient;
import common.ClientService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;


/**
 * Created by Nicu on 4/1/17.
 */
@Configuration
public class ClientServiceClientConfig {
    @Bean("clientService")
    public RmiProxyFactoryBean rmiProxyFactoryBean(){
        RmiProxyFactoryBean factoryBean = new RmiProxyFactoryBean();
        factoryBean.setServiceUrl("rmi://localhost:9009/ClientService");
        factoryBean.setServiceInterface(ClientService.class);
        return factoryBean;
    }

    @Bean
    public ClientServiceClient clientServiceClient(){
        return new ClientServiceClient();
    }
}
