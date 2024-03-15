package seatech.ibv.data;

import com.univocity.parsers.annotations.Parsed;

public class LoginData extends BaseData {
    @Parsed(field = "Username checker", defaultNullRead = "")
    private String usernameChecker;
    @Parsed(field = "Password checker", defaultNullRead = "")
    private String passwordChecker;
    @Parsed(field = "Username maker", defaultNullRead = "")
    private String usernameMaker;
    @Parsed(field = "Password maker", defaultNullRead = "")
    private String passwordMaker;

    @Parsed(field = "capcha", defaultNullRead = "")
    private String capcha;

    public String getCapcha() {
        return capcha;
    }
    public String getUsernameChecker() {
        return usernameChecker;
    }

    public String getPasswordChecker() {
        return passwordChecker;
    }

    public String getUsernameMaker() {
        return usernameMaker;
    }

    public String getPasswordMaker() {
        return passwordMaker;
    }
}
