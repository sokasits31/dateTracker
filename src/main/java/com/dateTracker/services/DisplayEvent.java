package com.dateTracker.services;

import com.dateTracker.entity.Event;
import com.dateTracker.entity.User;
import com.dateTracker.persistence.GenericDao;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * This class is the Rest Service that has endpoints to get all events, get events by username,
 * get movies based on a keyword(s) in the event name, add an event or delete an event
 */

@Path("/events")
public class DisplayEvent {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @GET
    @Path("/search/all")
    public Response getAllEvents() throws JsonProcessingException {
        GenericDao userDAO = new GenericDao(Event.class);

        List<Event> eventsList = userDAO.getAll();
        //ObjectMapper mapper = new ObjectMapper();
        //String responseString = mapper.writeValueAsString(usersList);
        //return Response.status(200).entity(responseString).build();

        String stringResponse = "";
        if (!eventsList.isEmpty()) {
            try {
                logger.info("starting the try block");
                ObjectMapper mapper = new ObjectMapper();
                stringResponse = mapper.writeValueAsString(eventsList);
                logger.debug("in the try block and added parsed json for all EVENTS");
            } catch (IOException ioException) {
                logger.error(ioException.getMessage());
                logger.info(ioException.getMessage());
            }
            logger.debug("string response: " + stringResponse);
            return Response.status(200).entity(stringResponse).build();
        } else {
            return Response.status(404).entity("Status 404: Not Found").build();
        }
    }

    @GET
    @Path("/searchbyName/{userName}")
    public Response getAllEvents(@PathParam("userName") String name) throws JsonProcessingException {
        GenericDao eventDAO = new GenericDao(Event.class);
        GenericDao userDAO = new GenericDao(User.class);
        logger.info(name);
        //instantiate and retrieves list of user object by username
        logger.debug("getting list of users");
        List<User> userList = userDAO.getByPropertyEqual("userName", name);
        logger.info(userList);
        //access list to get user object to get the id
        // if userList.size() is greater than zero
        logger.debug("getting user from list");
        User user = userList.get(0);
        //else
        // return Response.status(404).entity("Status 404: Not Found").build();
        logger.info(user);
        logger.debug("getting users id");
        int id = user.getId();
        logger.info(id);
        List<Event> eventsList = eventDAO.getByPropertyEqualint("user", id);
        logger.info(eventsList);

        String stringResponse = "";
        if (!eventsList.isEmpty()) {
            try {
                logger.info("starting the try block");
                ObjectMapper mapper = new ObjectMapper();
                stringResponse = mapper.writeValueAsString(eventsList);
                logger.debug("in the try block and added parsed json for all EVENTS");
            } catch (IOException ioException) {
                logger.error(ioException.getMessage());
                logger.info(ioException.getMessage());
            }
            logger.debug("string response: " + stringResponse);
            return Response.status(200).entity(stringResponse).build();
        } else {
            return Response.status(404).entity("Status 404: Not Found").build();
        }
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