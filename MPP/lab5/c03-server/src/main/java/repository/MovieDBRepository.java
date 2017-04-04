package repository;

import common.domain.Movie;
import common.domain.validators.RentalException;
import common.domain.validators.Validator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by victo on 3/19/2017.
 */
public class MovieDBRepository implements Repository<Long, Movie> {
    private Validator<Movie> validator;
    private String url;
    private String username;
    private String password;

    public MovieDBRepository(Validator<Movie> validator, String url, String username, String password) {
        this.validator = validator;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Optional<Movie> findOne(Long id) {
        if(id == null)
            throw new IllegalArgumentException("id must not be null");
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("select * from movies where id=?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    Long movieId = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String director = resultSet.getString("director");
                    String genre = resultSet.getString("genre");
                    int availableCopies = resultSet.getInt("availableCopies");

                    Movie movie = new Movie(name, director, genre, availableCopies);
                    movie.setId(movieId);
                    return Optional.of(movie);
                }
            }

        } catch(SQLException e) {
            throw new RentalException(e);
        }

        return Optional.empty();
    }

    @Override
    public Iterable<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("select * from movies")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    Long movieId = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String director = resultSet.getString("director");
                    String genre = resultSet.getString("genre");
                    int availableCopies = resultSet.getInt("availableCopies");

                    Movie movie = new Movie(name, director, genre, availableCopies);
                    movie.setId(movieId);

                    movies.add(movie);
                }
            }
        } catch (SQLException e) {
            throw new RentalException(e);
        }

        return movies;
    }

    @Override
    public Optional<Movie> save(Movie entity) {
        if(entity == null)
            throw new IllegalArgumentException("entity must not be null");
        validator.validate(entity);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(
                     "insert into movies (name, director, genre, availableCopies) values (?,?,?,?)")) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDirector());
            statement.setString(3, entity.getGenre());
            statement.setInt(4, entity.getAvailableCopies());

            statement.executeUpdate();

            return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.of(entity);
        }
    }

    @Override
    public Optional<Movie> delete(Long id) {
        if(id == null)
            throw new IllegalArgumentException("id must not be null.");
        Optional<Movie> client = findOne(id);
        if(!client.isPresent()) {
            return Optional.empty();
        }
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("delete from movies where id=?")) {
            statement.setLong(1, id);

            statement.executeUpdate();

            return client;
        } catch(SQLException e) {
            throw new RentalException("DB movie error: ", e);
        }
    }

    @Override
    public Optional<Movie> update(Movie entity) {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        validator.validate(entity);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(
                     "update movies set (name=?,director=?, genre=?, availableCopies=?) WHERE id=?")) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDirector());
            statement.setString(3, entity.getGenre());
            statement.setInt(4, entity.getAvailableCopies());
            statement.setLong(5, entity.getId());

            statement.executeUpdate();

            return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.of(entity);
        }
    }
}
