package ourbox.service;


import ourbox.service.stock.service.model.Mail;

import java.util.ArrayList;

public class ServiceList {

    private ArrayList<Mail> mails;
    private Mail currentMail;

    public ServiceList(){
        mails = new ArrayList<>();
    }

    public void addService(Mail mll){
        mails.add(mll);
    }

    public boolean checkIdRoom(String idRoom){
        for (Mail mm : mails) {
            if (mm.getIdRoom().equals(idRoom)) {
                currentMail = mm;
                return false;
            }
        }
        currentMail = null;
        return true;
    }

    public void removeAccount(String roomId){
        for (int i = 0; i < mails.size(); i++) {
            if (mails.get(i).getIdRoom().equals(roomId)) {
                mails.remove(i);
            }
        }
    }

    public ArrayList<Mail> toList(){
        return mails;
    }
}
