package ro.ubb.catalog.core.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Nicu on 4/9/17.
 */
@Entity
@Table(name = "clients")
public class Client extends BaseEntity<Long>{
    //region Fields
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cnp",nullable = false)
    private int cnp;

    @Column(name = "name", nullable = false)
    private String name;


   //endregion

    //region Constructor
    public Client() {

    }

    public Client(String name, int cnp) {
        this.name = name;
        this.cnp = cnp;
    }
    //endregion

    //region Methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCnp(int cnp) {
        this.cnp = cnp;
    }

    public int getCnp() {
        return cnp;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client that = (Client) o;

        if (cnp != that.cnp) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        return result;
    }

    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", cnp='" + cnp +
                "} " + super.toString();
    }//endregion
}
