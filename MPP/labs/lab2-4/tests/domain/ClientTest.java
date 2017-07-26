package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 3/20/2017.
 */
public class ClientTest {
    private static final Long ID = new Long(1);
    private static final Long NEW_ID = new Long(2);
    private static final String NAME = new String("clientName");
    private static final String NEW_NAME = new String("newClientName");
    private static final int AGE = 18;
    private static final int NEW_AGE = 20;

    private Client client;

    @Before
    public void setUp() throws Exception {
        client = new Client(NAME, AGE);
        client.setId(ID);
    }

    @After
    public void tearDown() throws Exception {
        client = null;
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals("Ids should be equal", ID, client.getId());
    }

    @Test
    public void testSetId() throws Exception {
        client.setId(NEW_ID);
        assertEquals("Ids should be equal", NEW_ID, client.getId());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("Names should be equals", NAME, client.getName());
    }

    @Test
    public void testGetAge() throws Exception {
        assertEquals("Ages should be equal", AGE, client.getAge());
    }

    @Test
    public void testSetName() throws Exception {
        client.setName(NEW_NAME);
        assertEquals("Names should be equal", NEW_NAME, client.getName());
    }

    @Test
    public void testSetAge() throws Exception {
        client.setAge(NEW_AGE);
        assertEquals("Ages should be equal", NEW_AGE, client.getAge());
    }
}
