package com.dateTracker.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This servlet will redirect the page request to an absolute url address
 * 
 */

@WebServlet(
        urlPatterns = {"/searchbyName"}

)

public class SearchEvent extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("starting the doGet method in SearchEvent");

        String userName = req.getParameter("userName");
        String url = "http://localhost:8080/dateTracker/services/events/searchbyName/" + userName;
        resp.sendRedirect(url);

    }
}