package common;

import java.io.Serializable;

/**
 * Created by user on 3/7/2017.
 */
public class Client implements Serializable{
    //region Fields
    private String name;
    private int cnp;
//    List<Long> rentedMovies; // the rented movies this client has currently in his possession
    //endregion

    //region Constructor
    public Client() {
        this.cnp=-1;
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

    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", cnp='" + cnp +
                "} " + super.toString();
    }//endregion
}
