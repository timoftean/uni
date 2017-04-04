package client.service;

import client.tcp.TcpClient;
import common.CommonService;
import common.domain.Client;
import common.domain.Movie;
import common.domain.Rent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by user on 4/1/2017.
 */
public class CommonServiceClient implements CommonService {
    private ExecutorService executorService;
    private TcpClient tcpClient;

    @Override
    public Future<String> sayHi(String name) {
        return null;
    }

    @Override
    public Future<String> sayBye(String name) {
        return null;
    }

    @Override
    public Future<String> addClient(Client client) {
        return null;
    }

    @Override
    public Future<String> getAllClients() {
        return null;
    }

    @Override
    public Future<String> printOneClient(Long id) {
        return null;
    }

    @Override
    public Future<String> deleteClient(Long id) {
        return null;
    }

    @Override
    public Future<String> updateClient(Client client) {
        return null;
    }

    @Override
    public Future<String> addMovie(Movie movie) {
        return null;
    }

    @Override
    public Future<String> getAllMovies() {
        return null;
    }

    @Override
    public Future<String> printOneMovie(Long id) {
        return null;
    }

    @Override
    public Future<String> deleteMovie(Long id) {
        return null;
    }

    @Override
    public Future<String> updateMovie(Movie movie) {
        return null;
    }

    @Override
    public Future<String> rentMovie(Rent rent) {
        return null;
    }

    @Override
    public Future<String> getAllRentals() {
        return null;
    }

    @Override
    public Future<String> deleteRental(Long id) {
        return null;
    }

    @Override
    public Future<String> filterMoviesByName(String name) {
        return null;
    }

    @Override
    public Future<String> filterMoviesByDirector(String director) {
        return null;
    }

    @Override
    public Future<String> filterMoviesByType(String type) {
        return null;
    }

    @Override
    public Future<String> filterMoviesByNumberOfCopies(int nocopies) {
        return null;
    }

    @Override
    public Future<String> filterClientsByName(String name) {
        return null;
    }

    @Override
    public Future<String> filterClientsByAge(int age) {
        return null;
    }

    @Override
    public Future<String> reportNumberOfClientsRentedMovie(Long id) {
        return null;
    }

    @Override
    public Future<String> reportMoviesWithMostCopies() {
        return null;
    }
}
