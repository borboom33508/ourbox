package ourbox.myfile.account;

import ourbox.account.AdminLoginLegit;
import ourbox.account.VillagerLoginLegit;

public interface AccountDataSource {
    AdminLoginLegit getAccountsData();
    void setAccountData(AdminLoginLegit accounts);
}
