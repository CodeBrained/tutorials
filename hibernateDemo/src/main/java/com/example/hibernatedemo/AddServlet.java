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

@WebServlet(name = "AddServlet", value = "/Add")
public class AddServlet extends HttpServlet {

    // Creating objects to persist to the database
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("The POST request has been made to AddServlet /Add");
        ToDoListEntity toDoListEntity = new ToDoListEntity();

        // Creates EntityManager object to handle persistence
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        // Get task to add and print to console for testing purposes
        String task = request.getParameter("addTask");
        System.out.println(task);

        // begins a transaction to persist to the DB
        transaction.begin();
        toDoListEntity.setTask(task);
        entityManager.persist(toDoListEntity);
        transaction.commit();

        //closing the entityManager object prevents a resource leak
        entityManager.close();

        // Goes back to the todolist page
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("The GET request has been made to /Hello");

        response.setContentType("text/plain");

        response.getWriter().println("<h1>Hello!<h1/>");
        response.getWriter().println("<p>welcome to my website</p>");
    }
}
