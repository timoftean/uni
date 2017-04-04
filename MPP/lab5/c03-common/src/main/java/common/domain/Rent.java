package common.domain;
import java.time.LocalDateTime;
/**
 * Created by user on 3/13/2017.
 */
public class Rent extends BaseEntity<Long> {
    //region Fields
//    Client client;
//    Movie movie;

    private Long ClientID;
    private Long MovieID;
    private int noCopies;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;

    
    // the number of movie copies the client rents at the time
    //endregion

    //region Constructor
    public Rent() {}

    public Rent(Long ClientID, Long MovieID, int noCopies) {
        this.ClientID = ClientID;
        this.MovieID = MovieID;
        this.noCopies = noCopies;
        this.rentalDate = LocalDateTime.now();
        this.returnDate = null;
    }

    public Rent(Long ClientID, Long MovieID, int noCopies, LocalDateTime rentalDate, LocalDateTime returnDate){
        this.ClientID = ClientID;
        this.MovieID = MovieID;
        this.noCopies = noCopies;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }
    public Rent(Long ClientID, Long MovieID, int noCopies, LocalDateTime rentalDate){
        this.ClientID = ClientID;
        this.MovieID = MovieID;
        this.noCopies = noCopies;
        this.rentalDate = rentalDate;
        this.returnDate = null;
    }
    //endregion

    //region Methods

    public Long getClientId() {
        return this.ClientID;
    }

    public LocalDateTime getRentalDate() {return this.rentalDate;}

    public void setReturnDate(){ this.returnDate=LocalDateTime.now();}

    public Long getMovieId() {

        return this.MovieID;
    }

    public int getNoCopies() {
        return this.noCopies;
    }

    public LocalDateTime getReturnDate() {return returnDate;}

    public String toString() {
        return "Rent{" +
                "clientId='" + ClientID + '\'' +
                ", movieId='" + MovieID + '\'' +
                ", no of copies='" + noCopies + '\'' +
                "} " + super.toString();
    }
    //endregion
}