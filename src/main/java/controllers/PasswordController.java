package controllers;

import helpers.Validator;
import managers.UserManager;
import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/password")
public class PasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher passwordDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/classes/web/password.jsp");
        passwordDispatcher.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher passwordDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/classes/web/password.jsp");
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");
        String confPassword = req.getParameter("confPassword");


        if(oldPassword == null || oldPassword.length() == 0){
            req.setAttribute("errorMsg", "errOld");
            passwordDispatcher.forward(req, res);
        }
        else if(newPassword == null || newPassword.length() == 0){
            req.setAttribute("errorMsg", "errPass");
            passwordDispatcher.forward(req, res);
        }
        else if(confPassword == null || confPassword.length() == 0 || !confPassword.equals(newPassword)){
            req.setAttribute("errorMsg", "errConf");
            passwordDispatcher.forward(req, res);
        }
        else if(!Validator.isPasswordGood(newPassword)){
            req.setAttribute("errorMsg", "errVal");
            passwordDispatcher.forward(req, res);
        }
        else{
            User fromSession = (User) req.getSession().getAttribute("user");
            oldPassword = org.apache.commons.codec.digest.DigestUtils.sha256Hex(oldPassword);
            if(!fromSession.getPassword().equals(oldPassword)){
                req.setAttribute("errorMsg", "errWrongPass");
                passwordDispatcher.forward(req, res);
            }
            else{
                newPassword = org.apache.commons.codec.digest.DigestUtils.sha256Hex(newPassword);

                UserManager.updatePassword(fromSession.getLogin(), newPassword);
                User fromBase = UserManager.loadUserByLoginAndPassword(fromSession.getLogin(), newPassword);

                req.getSession().setAttribute("user", fromBase);
                res.sendRedirect(req.getContextPath()+"/account");
            }
        }
    }
}
