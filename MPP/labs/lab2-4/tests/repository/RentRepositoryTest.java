package repository;

import domain.Rent;
import domain.validators.RentValidator;
import domain.validators.RentalException;
import domain.validators.Validator;
import domain.validators.ValidatorException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by paul on 3/21/2017.
 */
public class RentRepositoryTest {
    private static final Long RID = new Long(8);
    private static final Long RRID = new Long(7);
    private static final Long CID = new Long(1);
    private static final Long MID = new Long(1);
    private static final int COPIES = 1;

    private Rent rent,rent2;

    private Repository<Long, Rent> rentRepository;

    private Validator<Rent> rentValidator = new RentValidator();

    @Before
    public void setUp() throws Exception {

        rentRepository = new RentFileRepository(rentValidator,"rentTestFile.txt");

        rent  = new Rent(CID,MID,COPIES);
        rent.setId(RID);
        rent2  = new Rent(CID,MID,COPIES);
        rent2.setId(RRID);
    }

    @After
    public void tearDown() throws Exception {
        rent = null;
    }


    @Test
    public void testFindOne() throws Exception {
        assertEquals("Should return empty", Optional.empty(), rentRepository.findOne((long) 10));
        assertNotEquals("Should return a rent", Optional.empty(),rentRepository.findOne((long) 2 ));
    }


    @Test
    public void testFindAll() throws Exception {
        Set<Rent> rents = (Set<Rent>) rentRepository.findAll();
        assertEquals("Should be 6",6,rents.size());
    }



    @Test
    public void testSave() throws Exception {
        Set<Rent> rents = (Set<Rent>) rentRepository.findAll();
        assertEquals("Should be 6",6,rents.size());
        rentRepository.save(rent);
        rents =(Set<Rent>)rentRepository.findAll();
        assertEquals("Should be 7",7,rents.size());
    }

    @Test(expected = RentalException.class)
    public void testSaveException() throws Exception {

        rentRepository.save(rent2);
    }

    @Test
    public void testDelete() throws Exception {
        Set<Rent> rents = (Set<Rent>) rentRepository.findAll();
        assertEquals("Should be 7",7,rents.size());
        rentRepository.delete(RID);
        rents =(Set<Rent>)rentRepository.findAll();
        assertEquals("Should be 6",6,rents.size());
    }

    @Test
    public void testUpdate() throws Exception {

    }


    @Test(expected = RentalException.class)
    public void testUpdateException() throws Exception {
        rentRepository.update(rent);
    }

}
