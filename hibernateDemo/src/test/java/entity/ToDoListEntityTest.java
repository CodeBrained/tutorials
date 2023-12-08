package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListEntityTest {

    @Test
    void getIdTest() throws Exception {
        ToDoListEntity todo = new ToDoListEntity();
        todo.setId(100);
        assertEquals(100, todo.getId());
    }

    @Test
    void setIdTest() {
        ToDoListEntity todo = new ToDoListEntity();
        todo.setId(100);
        assertEquals(100, todo.getId());
    }

    @Test
    void getTaskTest() {
        ToDoListEntity todo = new ToDoListEntity();
        todo.setTask("test");
        assertEquals("test", todo.getTask());
    }

    @Test
    void setTaskTest() {
        ToDoListEntity todo = new ToDoListEntity();
        todo.setTask("test");
        assertEquals("test", todo.getTask());
    }
}