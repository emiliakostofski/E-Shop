package mk.ukim.finki.vpaud1.web.servlet.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.vpaud1.model.User;
import org.springframework.context.annotation.Profile;

import java.io.IOException;

//sho i da e ova vo webfolter pojma nemam
@WebFilter(filterName = "auth-filter", urlPatterns = "/*",
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD},
        initParams = {
                @WebInitParam(name = "ignore-path", value = "/login"),
                @WebInitParam(name = "register-path", value = "/register"
                )})
@Profile("servlet")
public class LoginFilter implements Filter {
    private String ignorePath;
    private String registerPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        ignorePath = filterConfig.getInitParameter("ignore-path");
        registerPath = filterConfig.getInitParameter("register-path");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User) request.getSession().getAttribute("user");

        String path = request.getServletPath();

        //tuka smenav 'home' dodadov
        if(!"/login".equals(path) && !"/h2".equals(path) && !"/register".equals(path) && !"/main.css".equals(path) && user == null) {
            response.sendRedirect("/login");
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
