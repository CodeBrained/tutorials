package entity;

import jakarta.persistence.*;

// POJO CLASS
// ENTITY CLASS

@Entity
@Table(name = "to_do_list", schema = "todo")
@NamedQuery(name="ToDoListEntity.viewList", query="SELECT e FROM ToDoListEntity e")
public class ToDoListEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "task")
    private String task;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToDoListEntity that = (ToDoListEntity) o;

        if (id != that.id) return false;
        if (task != null ? !task.equals(that.task) : that.task != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (task != null ? task.hashCode() : 0);
        return result;
    }
}
