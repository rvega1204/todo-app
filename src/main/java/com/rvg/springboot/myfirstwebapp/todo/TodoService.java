package com.rvg.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

/**
 * Service class for managing Todo items.
 * Provides methods to add, find, update, and delete Todo items.
 * 
 * This class uses an in-memory list to store Todo items and is not thread-safe.
 * 
 * Methods:
 * - findByUsername(String username): Finds and returns a list of Todo objects that match the given username.
 * - addTodo(String username, String description, LocalDate targetDate, boolean done): Adds a new Todo item.
 * - deleteById(int id): Deletes a Todo item by its ID.
 * - findById(int id): Finds a Todo item by its ID.
 * - updateTodo(@Valid Todo todo): Updates an existing Todo item.
 * 
 * Fields:
 * - todos: A static list of Todo items.
 * - todosCount: A static counter for the number of Todo items.
 * - logger: A logger for logging messages.
 * 
 * Note: This class is annotated with @Service to indicate that it's a Spring service component.
 */
@Service
public class TodoService {
    // A static list of Todo items
    private static List<Todo> todos = new ArrayList<>();

    // A static counter for the number of Todo items
    private static int todosCount = 0;

    // A logger for logging messages
    Logger logger = Logger.getLogger(TodoService.class.getName());

    // Static block to initialize the list of Todo items
    static {
        todos.add(new Todo(++todosCount, "rvg", "Learn AWS1", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosCount, "rvg", "Learn Docker1", LocalDate.now().plusMonths(6), false));
        todos.add(new Todo(++todosCount, "rvg", "Learn Kubernetes1", LocalDate.now().plusMonths(9), false));
        todos.add(new Todo(++todosCount, "rvg", "Learn Spring Boot1", LocalDate.now().plusMonths(3), false));
    }

    /**
     * Finds and returns a list of Todo objects that match the given username.
     *
     * @param username the username to filter the Todo objects by
     * @return a list of Todo objects that belong to the specified username, or an empty list if an exception occurs
     */
    public List<Todo> findByUsername(String username) {
        try {
            return todos.stream()
                        .filter(todo -> todo.getUsername().equalsIgnoreCase(username))
                        .toList();
        } catch (Exception e) {
            logger.info(e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Adds a new Todo item.
     *
     * @param username the username of the Todo item
     * @param description the description of the Todo item
     * @param targetDate the target date of the Todo item
     * @param done the status of the Todo item
     */
    public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
        try {
            Todo todo = new Todo(++todosCount, username, description, targetDate, done);
            todos.add(todo);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    /**
     * Deletes a Todo item by its ID.
     *
     * @param id the ID of the Todo item to delete
     */
    public void deleteById(int id) {
        try {
            todos.removeIf(todo -> todo.getId() == id);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    /**
     * Finds a Todo item by its ID.
     *
     * @param id the ID of the Todo item to find
     * @return the Todo item with the given ID, or null if not found
     */
    public Todo findById(int id) {
        try {
            return todos.stream()
                        .filter(todo -> todo.getId() == id)
                        .findFirst()
                        .orElse(null);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    /**
     * Updates an existing Todo item. The method first deletes the Todo item with the same ID
     * and then adds the updated Todo item to the list. If an exception occurs during the process,
     * it logs the exception message.
     *
     * @param todo the Todo item to be updated
     */
	public void updateTodo(@Valid Todo todo) {
		try {
			deleteById(todo.getId());
			todos.add(todo);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}
}
