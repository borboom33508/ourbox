package ourbox.myfile.service;

import ourbox.service.ServiceList;

public interface MailDataSource {
    ServiceList getAccountsData();
    void setAccountData(ServiceList mails);
}
