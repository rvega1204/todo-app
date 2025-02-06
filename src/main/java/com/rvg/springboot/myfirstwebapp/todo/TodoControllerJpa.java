package com.rvg.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

/**
 * This controller handles CRUD operations for Todo items using JPA.
 * It manages the creation, updating, deletion, and listing of todos for the logged-in user.
 * 
 * Annotations:
 * @Controller - Indicates that this class serves as a controller in the Spring MVC framework.
 * @SessionAttributes - Specifies the session attributes that this controller will use.
 * 
 * Dependencies:
 * - TodoRepository: A repository interface for CRUD operations on Todo entities.
 * 
 * Methods:
 * - showNewTodoPage(ModelMap model): Handles the request to show the page for creating a new todo.
 * - addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result): Handles the submission of a new todo item.
 * - deleteTodo(@RequestParam int id): Handles the deletion of a todo item by its ID.
 * - showUpdateTodoPage(@RequestParam int id, ModelMap model): Handles the request to show the page for updating a todo item.
 * - updateTodo(ModelMap model, @Valid Todo todo, BindingResult result): Handles the submission of an updated todo item.
 * - getLoggedInUserName(ModelMap model): Retrieves the username of the currently authenticated user.
 * @author rvega
 */
@Controller
@SessionAttributes("name")
public class TodoControllerJpa {

    public TodoControllerJpa(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    private TodoRepository todoRepository;

    /**
     * Handles the request to list all todos for the logged-in user.
     * 
     * @param model the ModelMap object to pass attributes to the view
     * @return the name of the view to be rendered, in this case "listTodos"
     */
    @RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		String username = getLoggedInUserName(model);
				
		List<Todo> todos = todoRepository.findByUsername(username);
		model.addAttribute("todos", todos);
		
		return "listTodos";
	}

    
    /**
     * Displays the page for creating a new Todo item.
     *
     * @param model the ModelMap object to pass attributes to the view
     * @return the name of the view to be rendered, in this case "todo"
     */
    @RequestMapping(value="add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model) {
        String username = getLoggedInUserName(model);
        model.put("todo", new Todo(0, username, "", LocalDate.now().plusYears(1), false));
        return "todo";
    }

    /**
     * Handles the submission of a new Todo item.
     * 
     * @param model the ModelMap object to hold attributes for the view
     * @param todo the Todo object to be added
     * @param result the BindingResult object to hold validation errors
     * @return the name of the view to be rendered
     */
    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }

        String username = getLoggedInUserName(model);
        todo.setUsername(username);
        todoRepository.save(todo);
        
        return "redirect:list-todos";
    }

    /**
     * Handles the deletion of a todo item.
     *
     * @param id the ID of the todo item to be deleted
     * @return a redirect to the list of todos
     */
    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id) {
        todoRepository.deleteById(id);
        return "redirect:list-todos";
    }

    /**
     * Handles GET requests for updating a todo item.
     *
     * @param id the ID of the todo item to be updated
     * @param model the model map to hold attributes for the view
     * @return the name of the view to be rendered
     */
    @RequestMapping(value="update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        // Retrieve the todo item by its ID
        Todo todo = todoRepository.findById(id).get();
        
        // If the todo item exists, add it to the model
        if (todo != null) {
            model.put("todo", todo);
        }
        
        // Return the view name
        return "todo";
    }

    /**
     * Handles the POST request to update an existing todo item.
     *
     * @param model the ModelMap object to hold model attributes
     * @param todo the Todo object containing the updated todo details
     * @param result the BindingResult object to hold validation errors
     * @return the view name to be rendered
     */
    @RequestMapping(value="update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		
		String username = getLoggedInUserName(model);
		todo.setUsername(username);
        todoRepository.save(todo);
		return "redirect:list-todos";
	}

    /**
     * Retrieves the username of the currently authenticated user from the security context.
     *
     * @param model the ModelMap object that holds the model attributes for the view
     * @return the username of the currently authenticated user
     */
    private String getLoggedInUserName(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
