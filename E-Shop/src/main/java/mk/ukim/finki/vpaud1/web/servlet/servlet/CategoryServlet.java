package mk.ukim.finki.vpaud1.web.servlet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.vpaud1.model.Category;
import mk.ukim.finki.vpaud1.service.CategoryService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="category-servlet", urlPatterns = "/servlet/category")
public class CategoryServlet extends HttpServlet {

    public static final String TYPE = "type";

    private List<Category> categoryList = null;

    private final CategoryService categoryService;
    public CategoryServlet(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ipadress = req.getRemoteAddr();
        String clientAgent = req.getHeader("User-Agent");
        PrintWriter out = resp.getWriter();
        out.println("<html><head></head><body>");
        out.println("<h3>User Info<br/></h3>");
        out.format("IP Adress: %s<br/>", ipadress);
        out.format("Client Agent: %s<br/>", clientAgent);
        out.println("<h1>Category List</h1></body></html");
        out.println("<ul>");
        categoryService.listCategories().forEach(r->
                out.format("<li>%s (%s)</li>",r.getName(), r.getDescription()));
        out.println("</ul><br/>");


        out.println("<h3>Add Category<br/></h3>");


        out.println("<form method='POST' action='/servlet/category'>");
        out.println("<label for='name'>Name:</label>");
        out.println("<input id='name' type='text' name='name'/>");
        out.println("<label for='description'>Description:</label>");
        out.println("<input id='description' type='text' name='description'/>");
        out.println("<input type='submit' value='Submit'/>");
        out.println("</form>");

        out.println("</body></html");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName = req.getParameter("name");
        String categoryDescription = req.getParameter("description");
        categoryService.create(categoryName, categoryDescription);
        resp.sendRedirect("/servlet/category");
    }


}
