package mk.ukim.finki.vpaud1.web.servlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="hello", urlPatterns = "/hello")
public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        if(name==null)
        {
            name="";
        }

        if(surname==null)
        {
            surname="";
        }


        PrintWriter writer = resp.getWriter();
        writer.format("<html><head></head><body></body><h1>Hi %s %s!</h1></html", name, surname);
        writer.flush();
    }
}
