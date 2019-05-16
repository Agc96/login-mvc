package com.qtcteam.loginmvc.model.scheme.in;

public class LoginInRO extends BaseInRO {

    private String username;
    private String password;

    public LoginInRO(String applicationName, String username, String password) {
        super(applicationName);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

}
