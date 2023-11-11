package com.example.hibernatedemo;

import java.io.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;
import entity.ToDoListEntity;

@WebServlet(name = "HelloServlet", value = "/Hello")
public class HelloServlet extends HttpServlet {

    // Creating objects to persist to the database
    public ToDoListEntity toDoListEntity = new ToDoListEntity();
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("The POST request has been made to /Hello");

        String task = request.getParameter("addTask");
        System.out.println(task);

        transaction.begin();
        toDoListEntity.setTask(task);
        entityManager.persist(toDoListEntity);
        transaction.commit();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("The GET request has been made to /Hello");

        response.setContentType("text/plain");

        response.getWriter().println("<h1>Hello!<h1/>");
        response.getWriter().println("<p>welcome to my website</p>");
    }
}
