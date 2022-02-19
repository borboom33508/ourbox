package ourbox.myfile.account;

import ourbox.account.VillagerLoginLegit;

public interface VillagerDataSource {
    VillagerLoginLegit getAccountsData();
    void setAccountData(VillagerLoginLegit accounts);
}
