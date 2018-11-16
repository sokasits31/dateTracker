package com.dateTracker.services;

import com.dateTracker.entity.Event;
import com.dateTracker.entity.User;
import com.dateTracker.persistence.GenericDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("/events")
public class EventService {


    @POST
    @Path("search/all")
    public Response getAllEvents(
            @FormParam("userName") int id) {

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        String eventDate = currentDate.plusDays(365).format(formatter);

        GenericDao dao = new GenericDao(User.class);
        Event allEvents = (Event)dao.getById(id);

        return Response.status(200)
                .entity("List of all events : " + allEvents)
                .build();
    }


    @GET
    @Path("search/event")
    public Response getDaysEvent(
            @FormParam("userName") String userName,
            @FormParam("eventName") String eventName) {

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        String eventDate = currentDate.plusDays(365).format(formatter);

        GenericDao dao = new GenericDao(Event.class);

        List<Event> eventList = dao.getByPropertyEqual("Event_Date",eventDate);

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
            @FormParam("user_id") int userId,
            @FormParam("submit") String submit) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        LocalDate localDate = LocalDate.parse(eventDate, formatter);

        Event event = new Event();

        event.setEventName(eventName);
        event.setEventType(eventType);

        event.setEventDate(localDate);
        event.setId(userId);


        GenericDao dao = new GenericDao(Event.class);
        dao.insert(event);

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
            @FormParam("submit") String submit) {

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
            @FormParam("submit") String submit) {

        User user = new User();

        GenericDao dao = new GenericDao(Event.class);

        user.setUserName(userName);

        dao.delete(user);

        return Response.status(200)
                .entity(" User Events deleted successfuly!<br> Name: " + userName)
                .build();
    }
}
