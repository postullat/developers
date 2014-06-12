package com.epam.lab.developers.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/" + AboutController.VIEW_NAME)
public class AboutController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    protected static final String VIEW_NAME = "about";

    public AboutController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = "/WEB-INF/view/" + VIEW_NAME + ".jsp";
        request.getRequestDispatcher(url).forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }

}
