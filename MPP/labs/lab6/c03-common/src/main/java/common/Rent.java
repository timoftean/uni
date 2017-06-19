package common;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * Created by user on 3/13/2017.
 */
public class Rent implements Serializable {
    //region Fields
//    Client client;
//    Movie movie;

    private int clientcnp;
    private int movieid;
    private int nocopies;


    
    // the number of movie copies the client rents at the time
    //endregion

    //region Constructor

    public Rent(){
        this.clientcnp=-1;
    }

    public Rent(int clientcnp, int movieid, int nocopies) {
        this.clientcnp = clientcnp;
        this.movieid = movieid;
        this.nocopies = nocopies;
    }
    //endregion

    //region Methods

    public int getClientCnp() {
        return this.clientcnp;
    }

    public int getMovieId() {
        return this.movieid;
    }
    public void setClientCnp(int cnp){
        this.clientcnp=cnp;
    }
    public void setMovieid(int movieid){
        this.movieid=movieid;
    }
    public void setNoCopies(int nocopies){
        this.nocopies=nocopies;
    }
    public int getNoCopies() {
        return this.nocopies;
    }

    public String toString() {
        return "Rent{" +
                "clientCnp='" + clientcnp + '\'' +
                ", movieId='" + movieid + '\'' +
                ", no of copies='" + nocopies + '\'' +
                "} " + super.toString();
    }
    //endregion
}