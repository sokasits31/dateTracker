package com.dateTracker.services;

import com.dateTracker.entity.Event;
import com.dateTracker.persistence.GenericDao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import static javax.ws.rs.client.Entity.entity;

/**
 * This class is the Rest Service that has endpoints to get all events, get events by username,
 * get movies based on a keyword(s) in the event name, add an event or delete an event
 */

@Path("/events")
public class DisplayEvent {

    @POST
    @Path("/search/all")
    public Response getAllEvents() throws JsonProcessingException {
        GenericDao userDAO = new GenericDao(Event.class);

        List<Event> usersList = userDAO.getAll();
        ObjectMapper mapper = new ObjectMapper();
        String responseString = mapper.writeValueAsString(usersList);
        return Response.status(200).entity(responseString).build();
    }

    @POST
    @Path("/add")
    public Response addUser(
            @FormParam("userName") String id,
            @FormParam("eventName") String name,
            @FormParam("submit") String submit,
            @FormParam("eventDate") String date) {

        return Response.status(200)
                .entity(" Product added successfuly!<br> Id: "+id+"<br> Name: " + name +"<br> + Date: " + date +"<br> + Submit: " + submit)
                .build();
    }

    /**
     * This method returns a JSON response of a movie containing the keyword passed into the path
     * @param title a movie title keyword
     */

}