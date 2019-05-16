package com.qtcteam.loginmvc.model.scheme.in;

public class UserInRO extends BaseInRO {

    private String names;
    private String email;
    private String username;
    private String password;

    public UserInRO (String applicationName, String names, String email, String username,
                     String password) {
        super(applicationName);
        this.names = names;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getNames() {
        return names;
    }
    public String getEmail() {
        return email;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

}
