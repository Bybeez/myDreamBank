import Managers.TransactionManager;
import Managers.AccountTypeManager;
import Managers.BaseManager;
import Managers.UserManager;
import models.Account;
import models.AccountType;
import models.Transaction;
import models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

public class App {
    public static void main(String[] args) {

        Logger logger = LogManager.getLogger(App.class);

        UserManager.purgeTable();
        User esteban = new User("Lhote", "Esteban", "esteban.lhote@metapolis.fr", "tripiode", "patate", "06 666 666 66", new Date(), "89 Quai des chartrons");
        User manu = new User("Macron", "Emmanuel", "manulebgdu75@minecraft.gouv.fr", "manulebg", "argent", "99 999 999 99", new Date(), "A l'Elysée");

        Account livretY = new Account(new Date());
        Account banqueDeFrance = new Account(new Date());

        AccountType livretJeune = new AccountType();
        AccountType livretFrance = new AccountType();

        livretJeune.setRate(10);
        livretJeune.setDescription("Un petit livret pour mon pote Esteban, laissez-le mdrr");


        livretY.setBalance(1000000);
        livretY.setAccountType(livretJeune);

        livretFrance.setRate(0.000001f);
        livretFrance.setDescription("LA THUNE DE LA FRANCE");

        banqueDeFrance.setBalance(0.1f);
        banqueDeFrance.setAccountType(livretFrance);

        esteban.addAccount(livretY);
        manu.addAccount(banqueDeFrance);

        Transaction unPetitDon = new Transaction(10, banqueDeFrance, livretY, "Un petit don pour mon poto de bordeaux, esteban");

        AccountTypeManager.saveAccountType(livretJeune);
        AccountTypeManager.saveAccountType(livretFrance);
        UserManager.saveUser(esteban);
        UserManager.saveUser(manu);
        TransactionManager.saveTransaction(unPetitDon);
        User estebOne = UserManager.loadUserByLogin("tripiode");



        for (Account acc : estebOne.getAccounts())
            logger.debug(acc.toString());

        BaseManager.shutdown();

    }
}
