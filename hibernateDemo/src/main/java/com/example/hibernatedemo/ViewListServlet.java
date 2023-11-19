package com.example.hibernatedemo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;
import entity.ToDoListEntity;

@WebServlet(name = "ViewListServlet", value = "/ViewList")
public class ViewListServlet extends HttpServlet {

    // Creating objects to persist to the database
    public ToDoListEntity toDoListEntity = new ToDoListEntity();
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    TypedQuery<ToDoListEntity> viewToDo = entityManager.createNamedQuery("ToDoListEntity.viewList", ToDoListEntity.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("The POST request has been made to /Hello");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain");
        String text;
        ArrayList<String> tasks = new ArrayList<>();

        for (ToDoListEntity toDoListEntity : viewToDo.getResultList()) {
            System.out.println("Adding to array ID: " + toDoListEntity.getId() + " - Task: " + toDoListEntity.getTask());
            text = ("ID: " + toDoListEntity.getId() + " - Task: " + toDoListEntity.getTask());
            tasks.add(text);

        }

        System.out.println();

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(tasks.get(i));
        }

        request.setAttribute("tasks", tasks);

        request.getRequestDispatcher("index.jsp").forward(request,response);

    }
}