package br.com.widsl.todolist.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "todos")
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Integer priority;
    private boolean realized;

    public TodoEntity(Long id, String name, String description, Integer priority, boolean realized) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.realized = realized;
    }

    public TodoEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public boolean getRealized() {
        return this.realized;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setRealized(boolean realized) {
        this.realized = realized;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TodoEntity that = (TodoEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TodoEntity;
    }

}
