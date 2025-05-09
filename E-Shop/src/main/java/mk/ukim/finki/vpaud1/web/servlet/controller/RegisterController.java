package mk.ukim.finki.vpaud1.web.servlet.controller;

import mk.ukim.finki.vpaud1.model.enumeration.Role;
import mk.ukim.finki.vpaud1.model.exeptions.InvalidArgumentException;
import mk.ukim.finki.vpaud1.model.exeptions.PasswordDoNotMatchException;
import mk.ukim.finki.vpaud1.service.AuthService;
import mk.ukim.finki.vpaud1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final AuthService authService;
    private final UserService userService;

    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model)
    {
        if(error != null && !error.isEmpty())
        {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent", "register");
        return "master-template";
    }



    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam Role role) {


        try{
            this.userService.register(username, password, repeatPassword, name, surname, role);
            //System.out.println("Registration successful. Redirecting to login.");
            return "redirect:/login";


        }catch (RuntimeException exception)
        {
            return "redirect:/register?error=" + exception.getMessage();

        }

    }
}
