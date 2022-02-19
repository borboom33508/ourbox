package ourbox.myfile.service;

import ourbox.service.ServiceList;

public interface ParcelDataSource {
    ServiceList getAccountsData();
    void setAccountData(ServiceList parcels);
}
