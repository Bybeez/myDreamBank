import helpers.Validator;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

public class testValidator {
    @Test
    public void testTooShort(){
        boolean result = Validator.isPasswordGood("patate");
        Assert.assertEquals(false, result);
    }

    @Test
    public void testNoMaj(){
        boolean result = Validator.isPasswordGood("patate1#");
        Assert.assertEquals(false, result);
    }

    @Test
    public void testNoDigit(){
        boolean result = Validator.isPasswordGood("PatateP#");
        Assert.assertEquals(false, result);
    }

    @Test
    public void testNoSpecChar(){
        boolean result = Validator.isPasswordGood("Patate1P");
        Assert.assertEquals(false, result);
    }

    @Test
    public void testHasAccent(){
        boolean result = Validator.isPasswordGood("Patâte1#");
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsGood(){
        boolean result = Validator.isPasswordGood("Patate1#");
        Assert.assertEquals(true, result);
    }
}
