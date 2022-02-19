package ourbox.account.account.model;

import javafx.fxml.FXML;

public class AdminAccount {
    private String user;
    private String pass;
    private String firstName;
    private String time;

    public AdminAccount(String firstName, String user, String pass){
        this.user = user;
        this.pass = pass;
        this.firstName = firstName;
    }

    public String getUser() {
        return user;
    }

    public String getTime() {
        return time;
    }

    public String getPass() {
        return pass;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
