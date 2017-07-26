package repository;

import domain.Client;
import domain.Movie;
import domain.validators.RentalException;
import domain.validators.Validator;
import domain.validators.ValidatorException;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by Nicu on 3/11/2017.
 */
public class MovieFileRepository extends InMemoryRepository<Long, Movie> {
    private String fileName;

    public MovieFileRepository(Validator<Movie> validator, String fileName) {
        super(validator);
        this.fileName = fileName;

        loadData();
    }

    private void loadData() {
        Path path = Paths.get(fileName);

        try {
            Files.lines(path).forEach(line -> {
                List<String> items = Arrays.asList(line.split(","));
                if(items.size()==5) {
                    Long id = Long.valueOf(items.get(0));
                    String name = items.get((1));
                    String director = items.get((2));
                    String genre = items.get((3));
                    int availableCopies = Integer.parseInt(items.get(4));

                    Movie movie = new Movie(name, director, genre, availableCopies);
                    movie.setId(id);
                    super.save(movie);
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ValidatorException e) {
            throw new RentalException("Reading from file",e);
        }
    }

    @Override
    public Optional<Movie> save(Movie entity) throws ValidatorException {
        Optional<Movie> optional = super.save(entity);
        if (optional.isPresent()) {
            throw new RentalException("Duplicate movie ID");
        }
        saveToFile(entity);
        return Optional.empty();
    }

    @Override
    public Optional<Movie> delete(Long id) {
        Optional<Movie> optional = super.delete(id);
        if (!optional.isPresent()) {
            throw new RentalException("Movie does not exist");
        }
        removeFromFile(id);
        return Optional.empty();
    }

    @Override
    public Optional<Movie> update(Movie entity) throws ValidatorException {
        Optional<Movie> optional = super.update(entity);
        if (!optional.isPresent()) {
            throw new RentalException("Movie does not exist");
        }
        removeFromFile(entity.getId());
        saveToFile(entity);
        return Optional.empty();
    }
    private void saveToFile(Movie entity) {
        Path path = Paths.get(fileName);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.newLine();
            bufferedWriter.write(
                    entity.getId() + "," + entity.getName() + "," + entity.getDirector() + ","+ entity.getGenre() +"," + entity.getAvailableCopies());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RentalException("Saving to file",e);
        }
    }

    private void removeFromFile(Long idc){
        try {
            FileOutputStream writer = new FileOutputStream(fileName);
            writer.write(("").getBytes());
            writer.close();
            Iterable<Movie> allMovies = this.findAll();
            allMovies.forEach(movie -> {
                if(movie.getId()!=idc)
                    saveToFile(movie);
            });
        } catch (IOException e) {
            throw new RentalException("Remove from file",e);
        }


    }
}
