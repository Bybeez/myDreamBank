package controllers;

import managers.UserManager;
import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if(req.getParameter("logout") == "1")
            req.getSession().invalidate();
        RequestDispatcher loginDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/classes/web/login.jsp");
        loginDispatcher.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher loginDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/classes/web/login.jsp");
        String login = req.getParameter("login");
        String password = req.getParameter("password");


        if(login == null || login.length() == 0){
            req.setAttribute("errorMsg", "errLog");
            loginDispatcher.forward(req, res);
        }
        else if(password == null || password.length() == 0){
            req.setAttribute("errorMsg", "errPass");
            loginDispatcher.forward(req, res);
        }
        else{
            User user = UserManager.loadUserByLoginAndPassword(login, password);
            if(user == null){
                req.setAttribute("errorMsg", "errNoCred");
                loginDispatcher.forward(req, res);
            }
            else{
                req.getSession().setAttribute("user", user);
                res.sendRedirect(req.getContextPath()+"/account");
            }
        }
    }
}
