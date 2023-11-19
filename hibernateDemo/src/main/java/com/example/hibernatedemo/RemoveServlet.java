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
        System.out.println("The POST request has been made to RemoveServlet /Remove");

        // get ID to remove from the user form
        String idToRemove = request.getParameter("removeTask");

        // begins a transaction to remove an element from DB based on the task ID
        transaction.begin();
        toDoListEntity = entityManager.find(ToDoListEntity.class, idToRemove);
        entityManager.persist(toDoListEntity);
        entityManager.remove(toDoListEntity);
        transaction.commit();

        // Goes back to the todolist page
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
