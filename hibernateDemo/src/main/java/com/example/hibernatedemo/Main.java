package com.example.hibernatedemo;

import entity.ToDoListEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {

            Methods.intro();
            Methods.continueMessage();
            Scanner scan = new Scanner(System.in);
            ArrayList<String> items = new ArrayList<String>();

            //ToDoListEntity toDo = new ToDoListEntity();
            TypedQuery<ToDoListEntity> viewToDo = entityManager.createNamedQuery("ToDoListEntity.viewList", ToDoListEntity.class);

            int userChoice = 0;
            while (userChoice != -1) {
                userChoice = Integer.parseInt(scan.nextLine());
                switch (userChoice) {
                    case 1:
                        transaction.begin();
                        ToDoListEntity toDo = new ToDoListEntity();

                        // User enters their to-do note
                        System.out.println("\nWrite your to-do note.");
                        String toDoNote = scan.nextLine();

                        // adds to-do note to the DB
                        toDo.setTask(toDoNote);
                        entityManager.persist(toDo);

                        // Continue messages
                        System.out.println("\nYour note has been added.");
                        Methods.continueMessage();

                        transaction.commit();
                        break;
                    case 2:
                        transaction.begin();
                        ToDoListEntity toDo2 = new ToDoListEntity();

                        // Introduction messages
                        System.out.println("\nEnter the index of the item you wish to delete ");
                        System.out.print("If you don't know the index, enter -1 to cancel, and ");
                        System.out.println("then enter 3 to view the to-do list.");

                        // User enters primary key
                        int num = Integer.parseInt(scan.nextLine());

                        // Creates new EM to manage deletion of to-do list items
                        toDo2 = entityManager.find(ToDoListEntity.class, num);

                        // Removes items from the DB
                        entityManager.persist(toDo2);
                        entityManager.remove(toDo2);

                        // Continue Messages
                        System.out.println("\nYour note has been deleted.");
                        Methods.continueMessage();

                        transaction.commit();
                        break;
                    case 3:
                        transaction.begin();

                        System.out.println("\nTo-Do List");

                        // Gets the contents of the DB
                        for (ToDoListEntity toDoListEntity : viewToDo.getResultList()) {
                            System.out.println("ID: " + toDoListEntity.getId() + " - Task: " + toDoListEntity.getTask());
                        }

                        // Continue messages
                        System.out.println();
                        Methods.continueMessage();

                        transaction.commit();
                        break;
                    default:
                        System.out.println("Closing program.");
                }
            }
            scan.close();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}