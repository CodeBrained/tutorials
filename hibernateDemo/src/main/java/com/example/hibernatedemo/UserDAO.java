package com.example.hibernatedemo;

import entity.ToDoListEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class UserDAO {
    @PersistenceContext
    private EntityManager entityManager;


    public void addTask(String task) {
        ToDoListEntity taskEntity = new ToDoListEntity();
        taskEntity.setTask(task);
        entityManager.persist(taskEntity);
        //return taskEntity;
    }
}
