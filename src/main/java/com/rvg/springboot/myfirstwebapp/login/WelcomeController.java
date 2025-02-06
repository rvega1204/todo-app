package com.rvg.springboot.myfirstwebapp.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

// WelcomeController class is a Spring MVC controller that handles requests to the root URL ("/").
// It retrieves the username of the currently authenticated user and passes it to the welcome view.
// The class is annotated with @Controller to indicate that it is a Spring MVC controller.
// The @SessionAttributes annotation is used to store the "name" attribute in the session.
// The "name" attribute is used to pass the username to the view.
// The getLoggedInUserName() method retrieves the username of the currently authenticated user.
// The goToWelcomePage() method handles GET requests to the root URL ("/") and directs the user to the welcome page.
// The method uses the ModelMap object to pass attributes to the view.
@Controller
@SessionAttributes("name")
public class WelcomeController {

    /**
     * Handles GET requests to the root URL ("/") and directs the user to the welcome page.
     * 
     * @param model the ModelMap object used to pass attributes to the view
     * @return the name of the view to be rendered, in this case "welcome"
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goToWelcomePage(ModelMap model) {
        model.put("name", getLoggedInUserName());
        return "welcome";
    }

    /**
     * Retrieves the username of the currently authenticated user.
     *
     * @return the username of the authenticated user
     */
    private String getLoggedInUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
    
}
