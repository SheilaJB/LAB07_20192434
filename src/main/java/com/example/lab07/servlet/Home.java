package com.example.lab07.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Home", value = "/Home")
public class Home extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        request.getRequestDispatcher("home.jsp").forward(request,response);


    }
}

