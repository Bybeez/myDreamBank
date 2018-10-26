package helpers;

import java.util.regex.Pattern;

public class Validator {
    public static boolean isPasswordGood(String password){
        if(password.length() < 8){
            return false;
        } else if(!Pattern.matches(".*[A-Z].*", password)){
            return false;
        } else if(!Pattern.matches(".*[a-z].*", password)){
            return false;
        } else if(!Pattern.matches(".*[1-9].*", password)){
            return false;
        } else if(!Pattern.matches(".*[!@#$&()\\-`.+,/\"].*", password)){
            return false;
        } else if(Pattern.matches(".*[à-ü].*", password)){
            return false;
        }
        return true;
    }
}
