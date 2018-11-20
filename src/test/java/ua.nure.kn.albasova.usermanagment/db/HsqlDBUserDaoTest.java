package ua.nure.kn.albasova.usermanagment.db;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import ua.nure.kn.albasova.usermanagment.User;

import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.*;

public class HsqlDBUserDaoTest extends DatabaseTestCase {
    private User user;
    private UserDAO dao;
    private ConnectionFactory connectionFactory;
    private static final Long ID = 0L;

    @Before
    public void setUp() throws Exception {
        getConnection();
        user = new User("Ivan","Ivanov",new Date());
        dao = new HsqlDBUserDao(connectionFactory);
    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * Test for creating new user record in DB method
     * @throws DatabaseException
     */
    @Test
    public void testCreate() throws DatabaseException {
        assertNull(user.getId());
        User userResult = dao.create(user);
        assertNotNull(userResult);
        assertNotNull(userResult.getId());
        assertEquals(user.getFirstName(),userResult.getFirstName());
        assertEquals(user.getLastName(),userResult.getLastName());
        assertEquals(user.getDateOfBirth(),userResult.getDateOfBirth());
    }
    @Override
    protected IDatabaseConnection getConnection() throws Exception {
        connectionFactory = new ConnectionFactoryImpl("org.hsqldb.jdbcDriver",
                "jdbc:hsqldb:file:db/Java_1_University","sa","");
        return new DatabaseConnection(connectionFactory.createConnection());
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        IDataSet dataSet = new XmlDataSet(getClass().getClassLoader()
                .getResourceAsStream("usersDataSet.xml"));
        return dataSet;
    }

    /**
     * Test method to find and return all user records in DB
     * @throws DatabaseException
     */

    @Test
    public void testFindAll() throws DatabaseException {
        User userResult = dao.create(user);
        int etalonSize = 3;
        Collection collection = dao.findAll();
        assertNotNull("Collection is null", collection);
        assertEquals("Collection size.", etalonSize, collection.size());
    }

    /**
     * Test for finding  user by ID
     * @throws DatabaseException
     */

    @Test
    public void testFind () throws DatabaseException {
        User testUser = dao.find(ID);
        assertNotNull(testUser);
        assertEquals( user.getFirstName(),testUser.getFirstName());
        assertEquals(user.getLastName(),testUser.getLastName());
    }

    /**
     * Test for updating user record in DB method
     * @throws DatabaseException
     */
    @Test
    public void testUpdate() throws DatabaseException {
        User user = new User("Ivan","Komarov",new Date());
        user.setId(0L);
        dao.update(user);
        User testUser = dao.find(user.getId());
        assertNotNull(testUser);
        assertEquals(user.getLastName(), testUser.getLastName());
    }

    /**
     * Test for user deleting method
     * @throws DatabaseException
     */
    @Test
    public void testDelete() throws DatabaseException {
        User testUser = new User(ID, "Ivan", "Ivanov", new Date());
        dao.delete(testUser);
        assertNull(dao.find(ID));
    }
}

