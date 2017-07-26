package repository;

import domain.Client;
import domain.Movie;
import domain.Rent;
import domain.validators.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by paul on 3/21/2017.
 */
public class ClientRepositoyTest {

    private static final Long ID = new Long(8);
    private static final Long NEW_ID = new Long(2);
    private static final String NAME = new String("clientName");
    private static final String NEW_NAME = new String("newClientName");
    private static final int AGE = 18;
    private static final int NEW_AGE = 10;



    private Client client,notValidClient;

    private Repository<Long, Client> clientRepository;

    private Validator<Client> clientValidator = new ClientValidator();

    @Before
    public void setUp() throws Exception {
        clientRepository = new ClientFileRepository(clientValidator, "clientTestFile.txt");
        client = new Client(NAME, AGE);
        client.setId(ID);

        notValidClient = new Client(NEW_NAME,NEW_AGE);
        notValidClient.setId(NEW_ID);
    }

    @After
    public void tearDown() throws Exception {
        client = null;
    }


    @Test
    public void testFindOne() throws Exception {
        assertEquals("Should return empty", Optional.empty(), clientRepository.findOne((long) 10));
        assertNotEquals("Should return a client", Optional.empty(),clientRepository.findOne((long) 1 ));
    }


    @Test
    public void testFindAll() throws Exception {
        Set<Client> clients = (Set<Client>) clientRepository.findAll();
        assertEquals("Should be 7",7,clients.size());
    }



    @Test
    public void testSave() throws Exception {
        Set<Client> clients = (Set<Client>) clientRepository.findAll();
        assertEquals("Should be 7",7,clients.size());
        clientRepository.save(client);
        clients =(Set<Client>)clientRepository.findAll();
        assertEquals("Should be 8",8,clients.size());
    }

    @Test(expected = ValidatorException.class)
    public void testSaveException() throws Exception {
        clientRepository.save(notValidClient);
    }

    @Test
    public void testDelete() throws Exception {
        Set<Client> clients = (Set<Client>) clientRepository.findAll();
        assertEquals("Should be 8",8,clients.size());
        clientRepository.delete(ID);
        clients =(Set<Client>)clientRepository.findAll();
        assertEquals("Should be 7",7,clients.size());
    }

    @Test
    public void testUpdate() throws Exception {

    }


    @Test(expected = RentalException.class)
    public void testUpdateException() throws Exception {
        clientRepository.update(notValidClient);
    }
}
