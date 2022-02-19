package ourbox.myfile.account;

import ourbox.account.SystemAdminLoginLegit;

public interface SystemAdminDataSource {
    SystemAdminLoginLegit getAccountsData();
    void setAccountData(SystemAdminLoginLegit accounts);
}
