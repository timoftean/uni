package common;

import java.io.Serializable;
import java.io.StreamCorruptedException;

/**
 * Created by user on 3/5/2017.
 */
public class Movie implements Serializable {
    //region Fields
    private int id;
    private String name;
    private String director;
    private String genre;
    private int availableCopies; //number of available copies available for rent
    //endregion

    //region Constructor
    public Movie(){
        this.id=-1;
    }
    public Movie(int id,String name, String director, String type, int availableCopies) {
        this.id=id;
        this.name = name;
        this.director = director;
        this.genre = type;
        this.availableCopies = availableCopies;
    }
    //endregion

    //region Methods
    public int getId(){return this.id;}
    public void setId(int id){
        this.id=id;
    }
    public String getName() {
        return this.name;
    }

    public String getDirector() {
        return this.director;
    }

    public String getGenre() {
        return this.genre;
    }

    public int getAvailableCopies() {
        return this.availableCopies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", director='" + director + '\'' +
                ", genre='" + genre + '\'' +
                ", available copies =" + availableCopies +
                "} " + super.toString();
    }
    //endregion
}