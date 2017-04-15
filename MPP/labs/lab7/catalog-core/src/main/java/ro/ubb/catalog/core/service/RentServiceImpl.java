package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.catalog.core.model.Rent;
import ro.ubb.catalog.core.repository.RentRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Nicu on 4/9/17.
 */
@Service
public class RentServiceImpl implements RentService {

    private static final Logger log = LoggerFactory.getLogger(RentServiceImpl.class);


    @Autowired
    private RentRepository rentRepository;

    @Override
    public void addRent(Rent rent){
        log.trace("addRent --- method entered");

        rentRepository.save(rent);

        log.trace("add: rent={}", rent);

    }

    @Override
    public Set<Rent> getAllRents() {
        log.trace("findAll --- method entered");

        List<Rent> rents = rentRepository.findAll();

        log.trace("findAll: rents={}", rents);

        return rents.stream().collect(Collectors.toSet());
    }

    @Override
    public Set<Rent> filterRentsByNOC(int noc){
        return null;
    }

    @Override
    public void deleteRent(Rent rent){

        log.trace("deleteClient --- method entered");

        rentRepository.delete(rent);

        log.trace("deleteRent: client={}", rent);

    }

    @Override
    public void updateRent(Rent rent){

        log.trace("findAll --- method entered");

        rentRepository.delete(rent);
        rentRepository.save(rent);

        log.trace("updateRent: rent={}", rent);

    }
}

