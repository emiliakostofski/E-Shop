package mk.ukim.finki.vpaud1.web.servlet.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.vpaud1.service.CategoryService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="thymeleaf-category-servlet",
        urlPatterns="/servlet/thymeleaf/category")
public class ThymeleafCategoryServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final CategoryService categoryService;

    public ThymeleafCategoryServlet(SpringTemplateEngine springTemplateEngine,
                                    CategoryService categoryService) {
        this.springTemplateEngine = springTemplateEngine;
        this.categoryService = categoryService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //WebContext context = new WebContext(req,resp,req.getServletContext());

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        context.setVariable("ipAdress", req.getRemoteAddr());
        context.setVariable("clientAgent", req.getHeader("User-Agent"));
        context.setVariable("categories", this.categoryService.listCategories());

        this.springTemplateEngine.process("categories.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName = (String) req.getParameter("name");
        String categoryDesc = (String) req.getParameter("description");
        categoryService.create(categoryName,categoryDesc);
        resp.sendRedirect("/servlet/thymeleaf/category");
    }
}
