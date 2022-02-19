package ourbox.account;

import ourbox.account.account.model.SystemAdminAccount;

import java.util.ArrayList;

public class SystemAdminLoginLegit {

    private ArrayList<SystemAdminAccount> systemAdminAccounts;
    private SystemAdminAccount currentSystemAdminAccount;

    public SystemAdminLoginLegit(){
        systemAdminAccounts = new ArrayList<>();
    }

    public boolean checkCurrentPassword(String systemAdminPass){
        for (SystemAdminAccount saa : systemAdminAccounts) {
            if (saa.getSystemAdminPass().equals(systemAdminPass)) {
                currentSystemAdminAccount = saa;
                return true;
            }
        }
        currentSystemAdminAccount = null;
        return false;
    }
    public boolean checkDuplicateSystemAdmin(String systemAdminUser){
        for (SystemAdminAccount saa : systemAdminAccounts) {
            if (saa.getSystemAdminUser().equals(systemAdminUser)) {
                currentSystemAdminAccount = saa;
                return false;
            }
        }
        currentSystemAdminAccount = null;
        return true;
    }

    public void addSystemAdminAccount(SystemAdminAccount saa){
        systemAdminAccounts.add(saa);
    }
    public void setPassword(String systemPass){
        for (int i =0 ; i < systemAdminAccounts.size() ; i++){
            systemAdminAccounts.get(i).setSystemAdminPass(systemPass);
        }
    }
    public boolean checkFile(){
        if (systemAdminAccounts.size() == 0){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkSystemAdminUser(String systemAdminUser,String systemAdminPass){
        for (SystemAdminAccount saa : systemAdminAccounts) {
            if (saa.getSystemAdminUser().equals(systemAdminUser) && saa.getSystemAdminPass().equals(systemAdminPass)) {
                currentSystemAdminAccount = saa;
                return true;
            }
        }
        currentSystemAdminAccount = null;
        return false;
    }
    public SystemAdminAccount getCurrentSystemAdminAccount(){
        return currentSystemAdminAccount;
    }
    public ArrayList<SystemAdminAccount> toList(){
        return systemAdminAccounts;
    }
}
