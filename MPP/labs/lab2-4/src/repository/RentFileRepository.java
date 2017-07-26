package repository;

import domain.Rent;
import domain.validators.RentalException;
import domain.validators.Validator;
import domain.validators.ValidatorException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by paul on 3/13/2017.
 */
public class RentFileRepository extends InMemoryRepository<Long, Rent>{
        private String fileName;

        public RentFileRepository(Validator<Rent> v, String fileName) {
            super(v);
            this.fileName = fileName;
            loadData();
        }
        private void loadData() {
            Path path = Paths.get(fileName);

            try {
                Files.lines(path).forEach(line -> {
                    List<String> items = Arrays.asList(line.split(","));
                    if(items.size()>3 ) {
                        Long id = Long.valueOf(items.get(0));
                        Long Mid = Long.valueOf(items.get(1));
                        Long Cid = Long.valueOf(items.get(2));
                        int noc = Integer.parseInt(items.get(3));
                        Rent r;
                        if (items.size() == 5 || items.size() == 6) {
                            LocalDateTime rentalDate = LocalDateTime.parse(items.get(4));

                            String returnDate = items.get(5);
                            if (returnDate.length() == 4) {
                                r = new Rent(Cid, Mid, noc, rentalDate);
                            }
                            else {
                                LocalDateTime returnDateTime = LocalDateTime.parse(returnDate);
                                r = new Rent(Cid, Mid, noc, rentalDate,returnDateTime);
                            }
                        } else {
                            r = new Rent(Cid, Mid, noc);
                        }
                        r.setId(id);
                        super.save(r);
                    }
                });
            }catch (IOException ex) {
                throw new RentalException("Saving to file",ex);
            }catch (ValidatorException e) {
                throw new RentalException("Saving to file",e);
            }
        }

        @Override
        public Optional<Rent> save(Rent entity) throws ValidatorException {
            Optional<Rent> optional = super.save(entity);
            if (optional.isPresent()) {
                throw new RentalException("Duplicate rent ID");
            }
            saveToFile(entity);
            return Optional.empty();
        }

        @Override
        public Optional<Rent> delete(Long id) {
            Optional<Rent> optional = super.delete(id);
            if (!optional.isPresent()) {
                throw new RentalException("Rent does not exist");
            }
            removeFromFile(id);
            return Optional.empty();
        }

        @Override
        public Optional<Rent> update(Rent entity) throws ValidatorException {
            Optional<Rent> optional = super.update(entity);
            if (!optional.isPresent()) {
                throw new RentalException("Rent does not exist");
            }
            removeFromFile(entity.getId());
            saveToFile(entity);
            return Optional.empty();
        }

        private void saveToFile(Rent entity) {
            Path path = Paths.get(fileName);

            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                bufferedWriter.newLine();
                bufferedWriter.write(
                        entity.getId() + "," + entity.getClientId() + "," + entity.getMovieId()+ "," + entity.getNoCopies()+","+entity.getRentalDate()+","+entity.getReturnDate());
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void removeFromFile(Long idc) {
            try {
                FileOutputStream writer = new FileOutputStream(fileName);
                writer.write(("").getBytes());
                writer.close();
                Iterable<Rent> allRents = this.findAll();
                allRents.forEach(rent -> {
                    if (rent.getId() != idc)
                        saveToFile(rent);
                });
            } catch (IOException e) {
                throw new RentalException("Remove from file", e);
            }
        }
    }

