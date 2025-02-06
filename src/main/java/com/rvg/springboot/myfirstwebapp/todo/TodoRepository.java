package com.rvg.springboot.myfirstwebapp.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TodoRepository interface extends JpaRepository to provide CRUD operations for Todo entities.
 * It includes a custom method to find todos by username.
 */
public interface TodoRepository extends JpaRepository<Todo, Integer> {

    public List<Todo> findByUsername(String username);
}
