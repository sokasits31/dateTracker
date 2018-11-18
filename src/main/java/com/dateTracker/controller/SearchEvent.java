package com.dateTracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.RequestDispatcher;
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
        urlPatterns = {"/searchEvent"}
)

public class SearchEvent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("userName");
        String event = req.getParameter("eventName");

        Client client = ClientBuilder.newClient();

        WebTarget target = client.target("http://localhost:8080/dateTracker/services/events/searchbyName/" + name);

        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        //forward to results.jsp page
        //RequestDispatcher dispatcher = req.getRequestDispatcher("http://localhost:8080/dateTracker/services/events/searchbyName/" + event);
        //dispatcher.forward(req, resp);
    }
}