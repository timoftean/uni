package repository;


import domain.Client;
import domain.validators.RentalException;
import domain.validators.Validator;
import domain.validators.ValidatorException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by paul on 3/12/2017.
 */
public class ClientFileRepository extends InMemoryRepository<Long, Client>{
    private String fileName;

    public ClientFileRepository(Validator<Client> v, String fileName) {
        super(v);
        this.fileName = fileName;
        loadData();
    }
    private void loadData() {
        Path path = Paths.get(fileName);

        try {
            Files.lines(path).forEach(line -> {
                List<String> items = Arrays.asList(line.split(","));
                if (items.size()==3) {

                    Long id = Long.valueOf(items.get(0));
                    String name = items.get((1));
                    int age = Integer.parseInt(items.get(2));

                    Client cl = new Client(name, age);
                    cl.setId(id);
                    super.save(cl);
                }
            });
        }catch (IOException ex) {
            throw new RentalException("Read rom file",ex);
        } catch(ValidatorException e){
            throw new RentalException("Read from file",e);
        }
    }

    @Override
    public Optional<Client> save(Client entity) throws ValidatorException {
        Optional<Client> optional = super.save(entity);
        if (optional.isPresent()) {
            throw new RentalException("Duplicate client ID");
        }
        saveToFile(entity);
        return Optional.empty();
    }

    @Override
    public Optional<Client> delete(Long id) {
        Optional<Client> optional = super.delete(id);
        if (!optional.isPresent()) {
            throw new RentalException("Client does not exist");
        }
        removeFromFile(id);
        return Optional.empty();
    }

    @Override
    public Optional<Client> update(Client entity) throws ValidatorException {
        Optional<Client> optional = super.update(entity);
        if (!optional.isPresent()) {
            throw new RentalException("Client does not exist");
        }
        removeFromFile(entity.getId());
        saveToFile(entity);
        return Optional.empty();
    }

    private void saveToFile(Client entity) {
        Path path = Paths.get(fileName);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.newLine();
            bufferedWriter.write(
                    entity.getId() + "," + entity.getName() + "," + entity.getAge());
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
            Iterable<Client> allClients = this.findAll();
            allClients.forEach(client -> {
                if(client.getId()!=idc)
                    saveToFile(client);
            });
        } catch (IOException e) {
            throw new RentalException("Remove from file",e);
        }


    }
}
