package mk.ukim.finki.vpaud1.web.servlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.vpaud1.model.User;
import mk.ukim.finki.vpaud1.model.exeptions.InvalidUserCredentialsExeption;
import mk.ukim.finki.vpaud1.service.AuthService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.Optional;


@WebServlet(name="LoginServlet", urlPatterns = "/servlet/login")
public class LoginServlet extends HttpServlet {

    private SpringTemplateEngine springTemplateEngine;
    private final AuthService authService;

    public LoginServlet(AuthService authService, SpringTemplateEngine springTemplateEngine) {
        this.authService = authService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        springTemplateEngine.process("login.html", context, resp.getWriter()); //za renderiranje, sozdavanje na stranata

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");


        Optional<User> user = Optional.empty();
        try{
           user = Optional.ofNullable(authService.login(username, password));
        }catch (InvalidUserCredentialsExeption ex)
        {
            IWebExchange webExchange = JakartaServletWebApplication
                    .buildApplication(getServletContext())
                    .buildExchange(req, resp);

            WebContext context = new WebContext(webExchange);
            context.setVariable("hasError", true);
            context.setVariable("error", ex.getMessage());
            springTemplateEngine.process("login.html", context, resp.getWriter());
        }
        req.getSession().setAttribute("user", user);
        resp.sendRedirect("/servlet/thymeleaf/category");


    }
}
