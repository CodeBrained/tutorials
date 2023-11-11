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

@WebServlet(name = "RemoveServlet", value = "/Remove")
public class RemoveServlet extends HttpServlet {

    // Creating objects to persist to the database
    public ToDoListEntity toDoListEntity = new ToDoListEntity();
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("The POST request has been made to /Hello");

        // get ID to remove from the user form
        String idToRemove = request.getParameter("removeTask");

        // FIXME used for testing
        System.out.println(idToRemove);

        transaction.begin();

        //convert idToRemove to int if needed
        toDoListEntity = entityManager.find(ToDoListEntity.class, idToRemove);
        entityManager.persist(toDoListEntity);
        entityManager.remove(toDoListEntity);

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
