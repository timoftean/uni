package ro.ubb.catalog.web.dto;

import ro.ubb.catalog.core.model.Rent;

import java.util.List;
import java.util.Set;

/**
 * Created by paul on 4/9/2017.
 */
public class RentsDto {
    private Set<Rent> rents;


    public RentsDto(Set<Rent> rents) {
        this.rents = rents;
    }

    public RentsDto() {
    }

    public Set<Rent> getRents() {
        return rents;
    }

    public void setRents(Set<Rent> rents) {
        this.rents = rents;
    }
}
