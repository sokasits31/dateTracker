package com.dateTracker.Service;

import com.dateTracker.entity.Event;
import com.dateTracker.entity.User;
import com.dateTracker.persistence.GenericDao;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("/event")
public class EventService{
    @GET
    @Path("{days}")
    public Response getDaysEvent(
            @PathParam("days") long days) {

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String eventDate = currentDate.minusDays(days).format(formatter);

        GenericDao dao = new GenericDao(Event.class);

        List<Event> eventList = dao.getByPropertyEqual("Event_Date",eventDate);

        return Response.status(200)
                .entity("List of requested events : " + eventList)
                .build();
    }

    @GET
    @Path("{type}")
    public Response getTypeEvent(
            @PathParam("type") String type) {

        GenericDao dao = new GenericDao(Event.class);

        List<Event> eventList = dao.getByPropertyEqual("Event_Type",type);

        return Response.status(200)
                .entity("List of requested events : " + eventList)
                .build();
    }

    @GET
    @Path("{all}")
    public Response getAllEvents(
            @PathParam("all") int id) {

        GenericDao dao = new GenericDao(User.class);
        Event allEvents = (Event)dao.getById(id);

        return Response.status(200)
                .entity("List of all events : " + allEvents)
                .build();
    }



    @POST
    @Path("/add")
    public Response addEvent(
            @FormParam("eventName") String eventName,
            @FormParam("eventType") String eventType,
            @FormParam("eventDate") float eventDate) {

        return Response.status(200)
                .entity(" Product added successfuly!<br> Name: " + eventName + "<br> Type: " + eventType + "<br> Date: " + eventDate)
                .build();
    }
}
