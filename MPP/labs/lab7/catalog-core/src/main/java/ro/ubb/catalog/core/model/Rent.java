package ro.ubb.catalog.core.model;

import com.sun.xml.internal.rngom.parse.host.Base;

import javax.persistence.*;

/**
 * Created by macbookpro on 4/9/17.
 */

@Entity
@Table(name = "rents")
public class Rent extends BaseEntity<Long>{
    //region Fields
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movietitle", nullable = false)
    private String movieTitle;

    @Column(name = "clientcnp", nullable = false)
    private int clientcnp;

    @Column(name = "nocopies", nullable = false)
    private int nocopies;


    //endregion

    //region Constructor

    public Rent(){

    }

    public Rent(int clientcnp, String movieTitle, int nocopies) {
        this.clientcnp = clientcnp;
        this.movieTitle = movieTitle;
        this.nocopies = nocopies;
    }
    //endregion

    //region Methods

    public int getClientCnp() {
        return this.clientcnp;
    }

    public String getMovieTitle() {
        return this.movieTitle;
    }

    public void setClientCnp(int cnp){
        this.clientcnp=cnp;
    }

    public void setMovieTitle(String movieTitle){
        this.movieTitle =movieTitle;
    }

    public void setNoCopies(int nocopies){
        this.nocopies=nocopies;
    }

    public int getNoCopies() {
        return this.nocopies;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rent that = (Rent) o;

        if (movieTitle != that.movieTitle) return false;
        if (clientcnp!=that.clientcnp) return false;
        return nocopies == that.nocopies;
    }

    @Override
    public int hashCode() {
        int result = this.toString().hashCode();
        return result;
    }

    public String toString() {
        return "Rent{" +
                "clientCnp='" + clientcnp + '\'' +
                ", movieTitle='" + movieTitle + '\'' +
                ", no of copies='" + nocopies + '\'' +
                "} " + super.toString();
    }
    //endregion
}
