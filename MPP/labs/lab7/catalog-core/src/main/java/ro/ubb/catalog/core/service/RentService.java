package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Rent;

import java.util.Set;

/**
 * Created by Nicu on 4/9/17.
 */
public interface RentService {
    void addRent(Rent rent);

    Set<Rent> getAllRents() ;

    Set<Rent> filterRentsByNOC(int noc);

    void deleteRent(Rent rent);

    void updateRent(Rent rent);
}
