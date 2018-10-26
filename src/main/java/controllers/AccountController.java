package controllers;

import managers.AccountManager;
import managers.AccountTypeManager;
import managers.UserManager;
import models.Account;
import models.AccountType;
import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/newAccount")
public class AccountController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<AccountType> accountTypes = AccountTypeManager.getAccountTypes();
        req.getSession().setAttribute("accountTypes", accountTypes);
        RequestDispatcher loginDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/classes/web/createAccount.jsp");
        loginDispatcher.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher loginDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/classes/web/createAccount.jsp");
        User fromSession = (User) req.getSession().getAttribute("user");
        String typeId = req.getParameter("accountType");


        if(typeId == null || typeId.length() == 0){
            req.setAttribute("errorMsg", "errType");
            loginDispatcher.forward(req, res);
        }
        else{
            AccountType accountType = AccountTypeManager.getAccountTypeFromId(typeId);
            if(accountType == null){
                req.setAttribute("errorMsg", "errUnkn");
                loginDispatcher.forward(req, res);
            }
            else{
                Account account = new Account(new Date());
                account.setBalance(75f);
                account.setAccountType(accountType);
                account.setOwner(fromSession);
                AccountManager.saveAccount(account);

                User fromBase = UserManager.loadUserByLoginAndPassword(fromSession.getLogin(), fromSession.getPassword());
                req.getSession().setAttribute("user", fromBase);
                res.sendRedirect(req.getContextPath()+"/account");
            }
        }
    }
}
