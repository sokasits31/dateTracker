package com.dateTracker.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A simple servlet to welcome the user.
 */



@WebServlet(
        urlPatterns = {"/searchbyName"}

)

public class SearchEvent extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //forward to results.jsp page
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        req.getParameter("userName");
        resp.sendRedirect(" http://localhost:8080/dateTracker/services/events/searchbyName/steveSokasits");
                //+ req.getParameter("userName"));
        //dispatcher.forward(req, resp);
    }
}