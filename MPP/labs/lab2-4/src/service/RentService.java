package service;

import domain.Rent;
import repository.Repository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by paul on 3/13/2017.
 */
public class RentService {
    private Repository<Long, Rent> repository;

    public RentService(Repository<Long, Rent> repository){
        this.repository=repository;
    }

    public void addRent(Rent rent){
        repository.save(rent);
    }

    public Set<Rent> getAllRents() {
        Iterable<Rent> rents = repository.findAll();
        return StreamSupport.stream(rents.spliterator(), false).collect(Collectors.toSet());
    }

    public Set<Rent> filterRentsByNOC(int noc) {
        Iterable<Rent> rents = repository.findAll();
        Set<Rent> filteredRents= new HashSet<>();
        rents.forEach(filteredRents::add);
        filteredRents.removeIf(rn -> rn.getNoCopies()!=noc);
        return filteredRents;
    }

    public void deleteRent(Rent rent) {
        repository.delete(rent.getId());
    }

    public void updateRent(Rent rent) {
        repository.update(rent);
    }
}
