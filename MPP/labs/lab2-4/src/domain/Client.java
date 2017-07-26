package domain;

import java.util.List;

/**
 * Created by user on 3/7/2017.
 */
public class Client extends BaseEntity<Long> {
    //region Fields
    private String name;
    private int age;
//    List<Long> rentedMovies; // the rented movies this client has currently in his possession
    //endregion

    //region Constructor
    public Client() {}

    public Client(String name, int age) {
        this.name = name;
        this.age = age;
    }
    //endregion

    //region Methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", age='" + age +
                "} " + super.toString();
    }//endregion
}
