package ourbox.account;

import ourbox.account.account.model.AdminAccount;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AdminLoginLegit implements Check {
    private ArrayList<AdminAccount> accounts;
    private AdminAccount currentAccount;

    public AdminLoginLegit(){
        accounts = new ArrayList<>();
    }

    public void addAccount(AdminAccount acc)
    {
        accounts.add(acc);
    }

    public String name(String username){
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUser().equals(username)) {
                return accounts.get(i).getUser();
            }
        }
        return "";
    }
    @Override
    public boolean checkSth(Object o) {
        for (AdminAccount acc : accounts){
            if (acc.getUser().equals(o)){
                currentAccount = acc;
                return false;
            }
        }
        currentAccount = null;
        return true;
    }
//    public boolean checkDuplicate(String user){
//        for (AdminAccount acc : accounts){
//            if (acc.getUser().equals(user)){
//                currentAccount = acc;
//                return false;
//            }
//        }
//        currentAccount = null;
//        return true;
//    }


    public boolean checkUser(String user,String pass){
        for (AdminAccount acc : accounts){
            if (acc.getUser().equals(user) && acc.getPass().equals(pass)){
                currentAccount = acc;
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                currentAccount.setTime(dtf.format(now));
                return true;
            }
        }
        currentAccount = null;
        return false;
    }
    public AdminAccount getCurrentAccount(){
        return currentAccount;
    }
    public ArrayList<AdminAccount> toList(){
        return accounts;
    }


}
