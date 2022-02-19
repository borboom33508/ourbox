package ourbox.myfile.service;

import ourbox.service.ServiceList;

public interface DocumentDataSource {
    ServiceList getAccountsData();
    void setAccountData(ServiceList documents);
}
