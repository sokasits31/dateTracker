package com.dateTracker.persistence;

import com.dateTracker.entity.Event;
import com.dateTracker.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class UserDaoTests {


    GenericDao genericDao;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {

        com.dateTracker.util.Database database = com.dateTracker.util.Database.getInstance();
        database.runSQL("loadTestDataBase.sql");
        genericDao = new GenericDao(User.class);

    }

    /**
     * Verify that we we can get all rows
     */
    @Test
    void getAllUsersSuccess() {
        List<User> tests = genericDao.getAll();
        assertEquals(3, tests.size());

    }



    /**
     * Verify that we can get by id
     */
    @Test
    void getByIdSuccess() {
        
        User retreivedUser = (User)genericDao.getById(2);
        assertEquals(2,retreivedUser.getId());
        assertEquals("steveSokasits", retreivedUser.getUserName());
    }

    /**
     * Verify successful insert of a user
     */
    @Test
    void insertSuccess() {

        User newUser = new User("joeWilliams");

        int id = genericDao.insert(newUser);
        assertNotEquals(0,id);

        User insertedUser = (User) genericDao.getById(id);

        assertEquals(newUser,insertedUser);
        assertEquals("joeWilliams", insertedUser.getUserName());
    }

    /**
     * Verify successful insert new row (Parent then child)
     */
    @Test
    void insertWithTestSuccess() {

        String userName = "newUserId";

        //Create Parent Data
        User newUser = new User("joeWilliams");

        //Create children
        Event event = new Event("Steve's Birthday", "Birthday", LocalDate.parse("1972-06-09"), newUser);

        // add children to parent
        newUser.addEvent(event);

        // Insert new user with event and get Id
        int id = (int) genericDao.insert(newUser);
        assertNotEquals(0,id);

        // Test inserted User
        User insertedUser = (User) genericDao.getById(id);
        assertEquals( "joeWilliams", insertedUser.getUserName());
        assertEquals(1,newUser.getEvents().size());

        // Test inserted Event
        genericDao = new GenericDao(Event.class);
        Event insertedEvent = (Event) genericDao.getById(1);
        assertEquals(event.getEventName(), insertedEvent.getEventName());


        //TestScore testscore = (TestScore) genericDao.getById()
        // Could continue comparing all values, but
        // it may make sense to use .equals()
        // TODO review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#mapping-model-pojo-equalshashcode
    }


    /**
     * Verify successful delete a row (Parent cascade to children)
     */
    @Test
    void deleteSuccess() {

        genericDao.delete(genericDao.getById(2));

        //Check parent
        assertNull(genericDao.getById(2));

        //Check child
        GenericDao event = new GenericDao(Event.class);
        List<Event> events = event.getAll();
        assertEquals(0,events.size());

    }




    /**
     * Verify that we can update a row
     */
    @Test
    void saveOrUpdateSuccess() {
        User user = (User) genericDao.getById(2);
        user.setUserName("happyPerson");

        genericDao.saveOrUpdate(user);

        User updatedUser = (User) genericDao.getById(2);
        assertEquals("happyPerson", updatedUser.getUserName());
    }


    /**
     * Verify that we can get row(s) based on "Like"
     */
    @Test
    void getByLikeSuccess() {
        List<User> tests = genericDao.getByPropertyLike("userName","sok");
        assertEquals(1, tests.size());

    }

    /**
     * Verify that we can get row based on "Equal"
     */
    @Test
    void getByEqualSuccess() {
        List<User> tests = genericDao.getByPropertyEqual("userName","steveSokasits");
        assertEquals(1, tests.size());

    }
}