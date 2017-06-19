package common;

import java.util.Set;

/**
 * Created by Nicu on 4/1/17.
 */
public interface RentService {
    void addRent(Rent rent);

    Set<Rent> getAllRents() ;

    Set<Rent> filterRentsByNOC(int noc);

    void deleteRent(Rent rent);

    void updateRent(Rent rent);
}
