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
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("/events")
public class EventService {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @POST
    @Path("/searchbyName")
    public Response getAllEvents(
            @FormParam("userName") String userName) throws JsonProcessingException {

        logger.info("starting the getAllEvents service");

        GenericDao userDao = new GenericDao(User.class);
        List <User> userList = userDao.getByPropertyEqual("userName", userName);

        User user = userList.get(0);

        GenericDao eventDao = new GenericDao(Event.class);
        List<Event> allEvents = eventDao.getByPropertyEqualint("user", user.getId());

        String response = "";

        if (!allEvents.isEmpty()) {
            try {
                logger.info("starting the try block");
                ObjectMapper mapper = new ObjectMapper();
                response = mapper.writeValueAsString(allEvents);
                logger.debug("in the try block and added parsed json for all EVENTS");
            } catch (IOException ioException) {
                logger.error(ioException.getMessage());
                logger.info(ioException.getMessage());
            }
            logger.debug("string response: " + response);
            return Response.status(200).entity("List of requested events : <br>" + response).build();
        } else {
            return Response.status(404).entity("Status 404: Requested Events Not Found").build();
        }
    }


    @POST
    @Path("/search/event")
    public Response getDaysEvent(
            @FormParam("userName") String userName,
            @FormParam("eventType") String eventType) throws JsonProcessingException {

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


    @POST
    @Path("/delete")
    public Response deleteEvent(
            @FormParam("userName") String userName,
            @FormParam("eventName") String eventName,
            @FormParam("submit") String submit) throws JsonProcessingException {

        logger.info("deleted the event");

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