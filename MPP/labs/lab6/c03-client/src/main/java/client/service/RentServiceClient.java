package client.service;

import common.Movie;
import common.MovieService;
import common.Rent;
import common.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Nicu on 4/1/17.
 */

@Service
public class RentServiceClient implements RentService {

    @Autowired
    private RentService rentService;

    @Override
    public void addRent(Rent rent){
        rentService.addRent(rent);
    }

    @Override
    public Set<Rent> getAllRents(){
        return rentService.getAllRents();
    }

    @Override
    public Set<Rent> filterRentsByNOC(int noc){
        return rentService.filterRentsByNOC(noc);
    }

    @Override
    public void deleteRent(Rent rent){
        rentService.deleteRent(rent);
    }

    @Override
    public void updateRent(Rent rent){
        rentService.updateRent(rent);
    }

}