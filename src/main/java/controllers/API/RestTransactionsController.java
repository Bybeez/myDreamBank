package controllers.API;

import managers.AccountManager;
import managers.TransactionManager;
import models.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Set;

@WebServlet("/api/transactions")
public class RestTransactionsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String accountId = req.getParameter("id");
        String transactionType = req.getParameter("transactionType");
        String payload = "{\n";
        List<Transaction> fromBase= null;

        if(accountId == null || transactionType == null){
            if(accountId == null){
                payload += "\"errorMsg\": \"accoundId missing\",\n";
            } else if(transactionType == null){
                payload += "\"errorMsg\": \"transactionTypemissing\",\n";
            }
        } else if(transactionType == "out"){
            fromBase = TransactionManager.loadTransactionsOutFromId(accountId);
        } else if(transactionType == "in"){
            fromBase = TransactionManager.loadTransactionsInFromId(accountId);
        } else {
            payload += "\"errorMsg\": \"transactionTypeWrong\",\n";
        }

        if(fromBase != null){
            for (Transaction transaction : fromBase) {
                payload += "\""+transaction.getId()+"\": {\n" +
                        "\"amount\": \""+transaction.getAmount()+"\", \n"+
                        "\"description\":\""+transaction.getDescription()+"\",\n"+
                        "},";
            }
        }

        payload += "}";

        res.setContentType("application/json");
        PrintWriter printWriter =  res.getWriter();
        printWriter.print(payload);
        printWriter.flush();



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String amount = req.getParameter("amount");
        String source = req.getParameter("source");
        String destination = req.getParameter("dest");
        String description = req.getParameter("desc");

        if(amount == null || source == null || destination == null || description == null){
            res.sendError(500);
        } else {
            Transaction transac = new Transaction();
            transac.setAmount(Float.parseFloat(amount));
            transac.setSource(AccountManager.loadAccountFromId(source));
            transac.setDestination(AccountManager.loadAccountFromId(destination));
            transac.setDescription(description);
            transac.setDate(new Date());
            TransactionManager.saveTransaction(transac);
            res.setStatus(200);
            return;
        }
    }
}
