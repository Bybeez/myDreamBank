import Managers.UserManager;
import models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class testsUserManager {
    private static User esteban;

    @BeforeAll
    public static void purgeAndCreateBasicTestUser(){
        UserManager.purgeTable();
        esteban = new User("Lhote", "Esteban", "esteban.lhote@metapolis.fr", "tripiode", "patate", "06 666 666 66", new Date(), "89 Quai des chartrons");
        if(UserManager.loadUserByLogin(esteban.getLogin()) == null)
            UserManager.saveUser(esteban);
    }

    @Test
    public void testSaveUser() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("UCT+2"));
        Date date = sdf.parse("24-07-1997");
        User greg = new User("Pessiot", "Gregoire", "greg.pess@wanadoo.com", "EryoGreg", "patate", "06 666 666 66", date, "89 Quai des chartrons");
        UserManager.saveUser(greg);
        assertNotNull(greg.getId());
    }

    @Test
    public void testLoadUserById() {
        User estebanFromBase = UserManager.loadUserByLogin(esteban.getLogin());
        assertEquals(esteban.getFirstname(), estebanFromBase.getFirstname());
    }

    @Test
    public void testLoadUserByLoginAndPassword() {
        User estebanFromBase = UserManager.loadUserByLoginAndPassword("tripiode", "patate");
        assertEquals(esteban.getFirstname(), estebanFromBase.getFirstname());
    }
}
