package com.rvg.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

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

//@Controller
@SessionAttributes("name")
public class TodoController {

    private TodoService todoService;
    
    Logger logger = Logger.getLogger(TodoController.class.getName());

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model) {
        String username = getLoggedInUserName(model);
        List<Todo> todos = todoService.findByUsername(username);
        model.addAttribute("todos", todos);
        return "listTodos";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model) {
        String username = getLoggedInUserName(model);
        model.put("todo", new Todo(0, username, "", LocalDate.now().plusYears(1), false));
        return "todo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }

        String username = getLoggedInUserName(model);
        todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);
        return "redirect:list-todos";
    }

    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id) {
        todoService.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value="update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = todoService.findById(id);
        if (todo != null) {
            model.put("todo", todo);
        }
        
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
		todoService.updateTodo(todo);
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
