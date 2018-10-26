package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/*")
public class ConnectionFilter implements Filter {
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        boolean loggedIn = (session !=null && session.getAttribute("user") != null);
        boolean loginPage = req.getServletPath().equals("/login");

        if((loggedIn && !loginPage)|| (!loggedIn && loginPage)){
            filterChain.doFilter(req, res);
        }
        else if(loggedIn && loginPage){
            res.sendRedirect("/TheBankProject-1.0-SNAPSHOT/account");
        }
        else {
            res.sendRedirect("/TheBankProject-1.0-SNAPSHOT/login");
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void destroy() {

    }
}
