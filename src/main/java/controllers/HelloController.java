package controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloController extends HttpServlet {
    Logger logger = LogManager.getLogger(HelloController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String data = "Hello from Bybeez";
        res.setContentType("text/plain");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(data);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        super.doPost(req, res);
    }
}
