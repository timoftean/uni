package repository;

import common.domain.Rent;
import common.domain.validators.RentalException;
import common.domain.validators.Validator;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by nicu on 3/20/17.
 */

public class RentDBRepository implements Repository<Long, Rent> {
    private Validator<Rent> validator;
    private String url;
    private String username;
    private String password;

    public RentDBRepository(Validator<Rent> validator, String url, String username, String password) {
        this.validator = validator;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Optional<Rent> findOne(Long id) {
        if(id == null)
            throw new IllegalArgumentException("id must not be null");
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("select * from rents where id=?")) {
            statement.setLong(1,id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    Long rentid = resultSet.getLong("id");
                    Long movieId = resultSet.getLong("movieid");
                    Long clientId = resultSet.getLong("clientid");
                    int noCopies = resultSet.getInt("nocopies");
                    LocalDateTime rentalData = resultSet.getDate("rentaldate").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    LocalDateTime returnData = resultSet.getDate("returnData").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

                    Rent rent = new Rent(clientId,movieId,noCopies,rentalData,returnData);
                    rent.setId(rentid);
                    return Optional.of(rent);
                }
            }

        } catch(SQLException e) {
            throw new RentalException(e);
        }

        return Optional.empty();
    }

    @Override
    public Iterable<Rent> findAll() {
        List<Rent> rents = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("select * from rents")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    Long rentId = resultSet.getLong("id");
                    Long clientId = resultSet.getLong("clientid");
                    Long movieId = resultSet.getLong("movieid");
                    int noCopies = resultSet.getInt("nocopies");

                    Rent rent = new Rent(clientId,movieId,noCopies);
                    rent.setId(rentId);
                    rents.add(rent);
                }
            }
        } catch (SQLException e) {
            throw new RentalException(e);
        }

        return rents;
    }

    @Override
    public Optional<Rent> save(Rent entity) {
        if(entity == null)
            throw new IllegalArgumentException("entity must not be null");
        validator.validate(entity);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(
                     "insert into rents (clientid, movieid,nocopies) values (?,?,?)")) {
            statement.setLong(1, entity.getClientId());
            statement.setLong(2, entity.getMovieId());
            statement.setInt(3, entity.getNoCopies());
           statement.executeUpdate();

            return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.of(entity);
        }
    }

    @Override
    public Optional<Rent> delete(Long id) {
        if(id == null)
            throw new IllegalArgumentException("id must not be null.");
        Optional<Rent> rent = findOne(id);
        if(!rent.isPresent()) {
            return Optional.empty();
        }
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("delete from rent where id=?")) {
            statement.setLong(1, id);

            statement.executeUpdate();

            return rent;
        } catch(SQLException e) {
            throw new RentalException(e);
        }
    }

    @Override
    public Optional<Rent> update(Rent entity) {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        validator.validate(entity);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(
                     "update rents set (name=?,director=?, genre=?, availableCopies=?) WHERE id=?")) {
            statement.setLong(1, entity.getClientId());
            statement.setLong(2, entity.getMovieId());
            statement.setInt(3, entity.getNoCopies());
            statement.setDate(4, java.sql.Date.valueOf(entity.getRentalDate().toLocalDate()));
            statement.setDate(5, java.sql.Date.valueOf(entity.getReturnDate().toLocalDate()));
            statement.setLong(6, entity.getId());

            statement.executeUpdate();

            return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.of(entity);
        }
    }
}
