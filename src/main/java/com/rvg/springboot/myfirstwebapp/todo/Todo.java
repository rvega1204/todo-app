package com.rvg.springboot.myfirstwebapp.todo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;


/**
 * Represents a Todo item with details such as id, username, description, target date, and completion status.
 * This class is annotated with @Entity to indicate that it is a JPA entity.
 */
@Entity
public class Todo {

    // Default constructor
    public Todo() {}
    
    // Fields of the Todo class with annotations for JPA
    @Id
    @GeneratedValue
    private int id;
    private String username;

    @Size(min = 10, message = "Enter at least 10 characters.")
    private String description;
    private LocalDate targetDate;
    private boolean done;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate date) {
        this.targetDate = date;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Constructs a new Todo object with the specified details.
     *
     * @param id the unique identifier for the todo item
     * @param username the username associated with the todo item
     * @param description a brief description of the todo item
     * @param targetDate the target date for completing the todo item
     * @param done the status indicating whether the todo item is completed
     */
    public Todo(int id, String username, String description, LocalDate targetDate, boolean done) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.done = done;
    }

    /**
     * Returns a string representation of the Todo object.
     * The string representation includes the id, username, description,
     * target date, and done status of the Todo.
     *
     * @return a string representation of the Todo object
     */
    @Override
    public String toString() {
        return "Todo{" +
                "id= " + id +
                ", username= '" + username + '\'' +
                ", description= '" + description + '\'' +
                ", targetDate= " + targetDate +
                ", done= " + done +
                '}';
    }
}
