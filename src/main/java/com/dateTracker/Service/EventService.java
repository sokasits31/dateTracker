package com.dateTracker.Service;

import com.dateTracker.persistence.GenericDao;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

@Path("/hello")
public class EventService{
    @GET
    @Path("{days}")
    public Response getDaysEvent(
            @PathParam("days") long days) {

        LocalDate currentDate = LocalDate.now();
        LocalDate eventDate = currentDate.minusDays(days);
        GenericDao dao = new GenericDao();

        dao.getByPropertyEqual()

        return Response.status(200)
                .entity("getDate is called, year/month/day : " + date)
                .build();
    }

    @GET
    @Path("{type}")
    public Response getDaysEvent(
            @PathParam("type") String type) {

        String date = year + "/" + month + "/" + day;

        return Response.status(200)
                .entity("getDate is called, year/month/day : " + date)
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
