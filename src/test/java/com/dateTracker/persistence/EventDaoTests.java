package com.dateTracker.persistence;

import com.dateTracker.entity.Event;
import com.dateTracker.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ejb.Local;
import javax.json.Json;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class EventDaoTests {

    private final Logger logger = LogManager.getLogger(this.getClass());

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
        genericDao = new GenericDao(Event.class);

    }

    /**
     * Verify that we we can get all rows
     */
    @Test
    void getAllEventsSuccess() {
        List<Event> tests = genericDao.getAll();
        assertEquals(6, tests.size());

    }



    /**
     * Verify that we can get by id
     */
    @Test
    void getByIdSuccess() {

        Event retreivedUser = (Event)genericDao.getById(2);
        assertEquals(2,retreivedUser.getId());
        assertEquals("birthday", retreivedUser.getEventName());
    }

    /**
     * Verify successful insert of a user
     */
    @Test
    void insertSuccess() {

        Event newEvent = new Event();

        int id = genericDao.insert(newEvent);
        assertNotEquals(0,id);

        User insertedUser = (User) genericDao.getById(id);

        assertEquals(newEvent,insertedUser);
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
        List<User> user = genericDao.getByPropertyLike("userName","sok");
        assertEquals(1, user.size());

        // Load users event to set
        Set<Event> events = user.get(0).getEvents();

        // create local variables for processing loop
        Period periodLength;               // used to total length or age of event
        Period daysUntil;                  // used to document how many dates to next event
        LocalDate upcomingEvent = null;    // used to hold next upcoming date
        String JSONresponse = "\n";        //  start of JSON Response

        for (Event e:events) {

            // Determine length of event
            periodLength = Period.between(e.getEventDate(), LocalDate.now());

            if (e.getEventType().equals("annual")) {
                // Determine next upcoming event
                upcomingEvent = e.getEventDate().plusYears(periodLength.getYears() + 1);
                daysUntil = Period.between(LocalDate.now(), upcomingEvent);
            } else {
                upcomingEvent = e.getEventDate();
                daysUntil = Period.between(LocalDate.now(), e.getEventDate());
            }

            String value = "birth";   // would be are PARAM in value from screen

            if (upcomingEvent.isAfter(LocalDate.now())
                    && e.getEventName().toLowerCase().contains(value.toLowerCase())) {

                JSONresponse += "{\n";
                JSONresponse += "\"eventName\": \"" + e.getEventName() + "\"\n";
                JSONresponse += "\"eventType\": \"" + e.getEventType() + "\"\n";
                JSONresponse += "\"eventDate\": \"" + e.getEventDate() + "\"\n";
                JSONresponse += "\"timeUntil\": \"" + daysUntil.getMonths() + " Months and "
                        + daysUntil.getDays() + " Days" + "\"\n";

                JSONresponse += "\"eventLenght\": \"" + periodLength.getYears() + " Years, "
                        + periodLength.getMonths() + " Months and "
                        + periodLength.getDays() + " Days" + "\"\n";
                JSONresponse += "\"nextUpcomingEventDate\": \"" + upcomingEvent + "\"\n";

                JSONresponse += "}\n";

            }



        }

        logger.debug(JSONresponse);

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
