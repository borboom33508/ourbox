package ourbox.account.account.model;

public class SystemAdminAccount {
    private String systemAdminUser;
    private String systemAdminPass;

    public SystemAdminAccount(String systemAdminUser,String systemAdminPass){
        this.systemAdminUser = systemAdminUser;
        this.systemAdminPass = systemAdminPass;
    }

    public String getSystemAdminUser() {
        return systemAdminUser;
    }

    public String getSystemAdminPass() {
        return systemAdminPass;
    }

    public void setSystemAdminPass(String systemAdminPass) {
        this.systemAdminPass = systemAdminPass;
    }

    public void changePass(String systemAdminPass){
    }
}
