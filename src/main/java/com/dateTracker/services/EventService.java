package com.dateTracker.services;

import com.dateTracker.entity.Event;
import com.dateTracker.entity.User;
import com.dateTracker.persistence.GenericDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Event service.
 */
@Path("/events")
public class EventService {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Gets all events for the user
     *
     * @param userName the user name
     * @return the all events for a particular user name
     */
    @GET
    @Path("/searchbyName/{userName}")
    public Response getAllEvents(
            @PathParam("userName") String userName) {

        logger.info("starting the getAllEvents service");

        // create local variables for processing loop
        Period periodLength;               // used to total length or age of event
        Period daysUntil;                  // used to document how many dates to next event
        LocalDate upcomingEvent = null;    // used to hold next upcoming date
        String JSONresponse = "\n";        //  start of JSON Response

        GenericDao userDao = new GenericDao(User.class);
        List <User> userList = userDao.getByPropertyEqual("userName", userName);

        User user = userList.get(0);

        GenericDao eventDao = new GenericDao(Event.class);
        List <Event> allEvents = eventDao.getByPropertyEqualint("user", user.getId());

        if (!allEvents.isEmpty()) {

            for (Event e:allEvents) {

                if (LocalDate.now().isBefore(e.getEventDate())) {
                    // Determine length of event
                    periodLength = Period.between(LocalDate.now(), e.getEventDate());
                    logger.debug("periodLength: " + LocalDate.now());
                    logger.debug("periodLength: " + e.getEventDate());
                    logger.debug("periodLength: " + periodLength.getDays());
                } else {
                    // Determine length of event
                    periodLength = Period.between(e.getEventDate(), LocalDate.now());
                    logger.debug("periodLength: " + LocalDate.now());
                    logger.debug("periodLength: " + e.getEventDate());
                    logger.debug("periodLength: " + periodLength.getDays());
                }

                if (e.getEventType().toLowerCase().equals("annual")) {
                    // Determine next upcoming event
                    upcomingEvent = e.getEventDate().plusYears(periodLength.getYears() + 1);
                    daysUntil = Period.between(LocalDate.now(), upcomingEvent);
                } else {
                    upcomingEvent = e.getEventDate();
                    daysUntil = Period.between(LocalDate.now(), e.getEventDate());
                }

                //String value = "birth";   // would be are PARAM in value from screen

                logger.debug("upcomingEvent: " + upcomingEvent);

                if (upcomingEvent.isAfter(LocalDate.now())) {
                        //&& e.getEventName().toLowerCase().contains(value.toLowerCase())) {

                    JSONresponse += "{\n";
                    JSONresponse += "\"eventName\": \"" + e.getEventName() + "\"\n";
                    JSONresponse += "\"eventType\": \"" + e.getEventType() + "\"\n";
                    JSONresponse += "\"eventDate\": \"" + e.getEventDate() + "\"\n";
                    JSONresponse += "\"timeUntil\": \"" + daysUntil.getMonths() + " Months and "
                            + daysUntil.getDays() + " Days" + "\"\n";

                    JSONresponse += "\"eventLength\": \"" + periodLength.getYears() + " Years, "
                            + periodLength.getMonths() + " Months and "
                            + periodLength.getDays() + " Days" + "\"\n";
                    JSONresponse += "\"nextUpcomingEventDate\": \"" + upcomingEvent + "\"\n";

                    JSONresponse += "}\n";
                }
            }

            logger.debug("string response: " + JSONresponse);
            return Response.status(200).entity("List of requested events : <br>" + JSONresponse).build();

        } else {
            return Response.status(404).entity("Status 404: Requested Events Not Found").build();
        }
    }


    /**
     * Gets days event.
     *
     * @param userName  the user name
     * @param eventType the event type
     * @return the days event
     * @throws JsonProcessingException the json processing exception
     */
    @POST
    @Path("/search/event")
    public Response getDaysEvent(
            @FormParam("userName") String userName,
            @FormParam("eventType") String eventType) throws JsonProcessingException {

        logger.info("starting the getDaysEvent service");
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        String eventDate = currentDate.plusDays(365).format(formatter);

        GenericDao userDao = new GenericDao(User.class);
        List <User> userList = userDao.getByPropertyEqual("userName", userName);

        User user = userList.get(0);

        GenericDao eventDao = new GenericDao(Event.class);
        List<Event> allEvents = eventDao.getByPropertyLike("eventType", eventType);

        return Response.status(200)
                .entity("List of requested events : " + allEvents)
                .build();
    }


    /**
     * Add event response.
     *
     * @param eventName the event name
     * @param eventType the event type
     * @param eventDate the event date
     * @param userName  the user name
     * @param submit    the submit
     * @return the response
     * @throws JsonProcessingException the json processing exception
     */
    @POST
    @Path("/add")
    public Response addEvent(
            @FormParam("eventName") String eventName,
            @FormParam("eventType") String eventType,
            @FormParam("eventDate") String eventDate,
            @FormParam("userName")  String userName,
            @FormParam("submit") String submit) throws JsonProcessingException {

        logger.info("starting the addEvent service");

        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        //LocalDate localDate = LocalDate.parse(eventDate, formatter);
        LocalDate localDate = LocalDate.parse(eventDate);
        logger.debug("the new event date is " + localDate);
        logger.debug("userName = " + userName);

        GenericDao userDao = new GenericDao(User.class);
        List <User> userList = userDao.getByPropertyEqual("userName", userName);

        User user = userList.get(0);

        logger.debug("userId = " + user.getId());

        Event event = new Event(eventName, eventType, localDate, user);

        logger.info("loaded the event");

        GenericDao eventDao = new GenericDao(Event.class);
        eventDao.insert(event);

        if (event.getId() > 0) {
            return Response.status(200)
                    .entity(" Product added successfuly! <br> User Name: " + userName + "<br> Event Name: " + eventName + "<br> Event Type: " + eventType + "<br> Date: " + eventDate + "<br> Submit: " + submit)
                    .build();
        }else {
            return Response.status(500)
                    .entity(" Product added unsuccessfuly! <br> User Name: " + userName + "<br> Event Name: " + eventName + "<br> Event Type: " + eventType + "<br> Date: " + eventDate + "<br> Submit: " + submit)
                    .build();
        }
    }


    /**
     * Update event response.
     *
     * @param eventName the event name
     * @param eventType the event type
     * @param eventDate the event date
     * @param userName  the user name
     * @param submit    the submit
     * @return the response
     * @throws JsonProcessingException the json processing exception
     */
    @POST
    @Path("/update")
    public Response updateEvent(
            @FormParam("eventName") String eventName,
            @FormParam("eventType") String eventType,
            @FormParam("eventDate") String eventDate,
            @FormParam("userName") String userName,
            @FormParam("submit") String submit) throws JsonProcessingException {

        logger.info("starting the updateEvents service");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        LocalDate localDate = LocalDate.parse(eventDate, formatter);

        Event event = new Event();

        GenericDao dao = new GenericDao(Event.class);
        dao.saveOrUpdate(event);

        return Response.status(200)
                .entity(" User Event updated successfuly!<br> UserName: "+userName+"<br> Name: " + eventName +"<br> + Date: " + eventDate +"<br> + Submit: " + submit)
                .build();
    }


    /**
     * Delete event response.
     *
     * @param userName  the user name
     * @param eventName the event name
     * @param submit    the submit
     * @return the response
     * @throws JsonProcessingException the json processing exception
     */
    @POST
    @Path("/delete")
    public Response deleteEvent(
            @FormParam("userName") String userName,
            @FormParam("eventName") String eventName,
            @FormParam("submit") String submit) throws JsonProcessingException {

        logger.info("starting the deleteEvent service");

        GenericDao eventDao = new GenericDao(Event.class);
        List<Event> event = eventDao.getByPropertyEqual("eventName", eventName);
        eventDao.delete(event.get(0));

        if (event.get(0).getId() > 0) {
            return Response.status(200)
                    .entity(" User Event deleted successfuly!<br> User Name: " + userName + "<br> Event Name: " + eventName)
                    .build();
        }else {
            return Response.status(500)
                    .entity(" User Event deleted successfuly!<br> User Name: " + userName + "<br> Event Name: " + eventName)
                    .build();
        }
    }
}