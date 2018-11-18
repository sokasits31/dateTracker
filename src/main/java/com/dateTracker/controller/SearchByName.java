package com.dateTracker.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 *
 */

@WebServlet(
        urlPatterns = {"/searchByName"}
)

public class SearchByName extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("userName");
        logger.info("Search By Name - doGet block" + name);

        Client client = ClientBuilder.newClient();

        WebTarget target = client.target("http://localhost:8080/dateTracker/services/events/searchbyName/" + name);
        logger.info("After WebTarget " + target);

        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);


        //forward to results.jsp page
        //RequestDispatcher dispatcher = req.getRequestDispatcher("http://localhost:8080/dateTracker/services/events/searchbyName/" + event);
        //dispatcher.forward(req, resp);
    }
}