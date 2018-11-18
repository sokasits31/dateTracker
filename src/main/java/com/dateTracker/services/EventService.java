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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("/events")
public class EventService {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @GET
    @Path("/search/all/{userName}")
    public Response getAllEvents(
            @PathParam("userName") String userName) throws JsonProcessingException {

        logger.info("starting the getAllEvents service");
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        String eventDate = currentDate.plusDays(365).format(formatter);
        logger.debug("the new event date is " + eventDate);

        GenericDao userDao = new GenericDao(User.class);
        List <User> userList = userDao.getByPropertyEqual("user_name", userName);

        User user = userList.get(0);

        GenericDao eventDao = new GenericDao(Event.class);
        List<Event> allEvents = eventDao.getByPropertyEqualint("user_id", user.getId());

        ObjectMapper mapper = new ObjectMapper();
        String response = mapper.writeValueAsString(allEvents);
        logger.debug("Response: " + response);

        return Response.status(200)
                .entity("List of all events : " + allEvents)
                .build();
    }


    @GET
    @Path("/search/event/{userName}/{eventType}")
    public Response getDaysEvent(
            @PathParam("userName") String userName,
            @PathParam("eventType") String eventType) throws JsonProcessingException {

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        String eventDate = currentDate.plusDays(365).format(formatter);

        GenericDao dao = new GenericDao(Event.class);

        List<Event> eventList = dao.getByPropertyEqual("event_type",eventType);



        return Response.status(200)
                .entity("List of requested events : " + eventList)
                .build();
    }


    @POST
    @Path("/add")
    public Response addEvent(
            @FormParam("eventName") String eventName,
            @FormParam("eventType") String eventType,
            @FormParam("eventDate") String eventDate,
            @FormParam("userName")  String userName,
            @FormParam("submit") String submit) throws JsonProcessingException {

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

        //event.setEventName(eventName);
        //event.setEventType(eventType);
        //event.setEventDate(localDate);
        //event.setId(userId);

        logger.info("loaded the event");

        GenericDao eventDao = new GenericDao(Event.class);
        eventDao.insert(event);

        return Response.status(200)
                .entity(" User Event added successfuly!<br> Name: " + eventName + "<br> Type: " + eventType + "<br> Date: " + eventDate)
                .build();
    }


    @POST
    @Path("/update")
    public Response updateEvent(
            @FormParam("eventName") String eventName,
            @FormParam("eventType") String eventType,
            @FormParam("eventDate") String eventDate,
            @FormParam("user_id") int userId,
            @FormParam("submit") String submit) throws JsonProcessingException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        LocalDate localDate = LocalDate.parse(eventDate, formatter);

        Event event = new Event();

        event.setEventName(eventName);
        event.setEventType(eventType);
        event.setEventDate(localDate);
        event.setId(userId);

        GenericDao dao = new GenericDao(Event.class);
        dao.saveOrUpdate(event);

        return Response.status(200)
                .entity(" User Event updated successfuly!<br> Id: "+userId+"<br> Name: " + eventName +"<br> + Date: " + eventDate +"<br> + Submit: " + submit)
                .build();
    }


    @DELETE
    @Path("/delete")
    public Response deleteEvent(
            @FormParam("userName") String userName,
            @FormParam("submit") String submit) throws JsonProcessingException {

        User user = new User();

        GenericDao dao = new GenericDao(Event.class);

        user.setUserName(userName);

        dao.delete(user);

        return Response.status(200)
                .entity(" User Events deleted successfuly!<br> Name: " + userName)
                .build();
    }
}
