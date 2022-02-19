package ourbox.account;

import ourbox.account.account.model.VillagerAccount;

import java.util.ArrayList;

public class VillagerLoginLegit implements Check {
    private ArrayList<VillagerAccount> villagerAccounts;
    private VillagerAccount currentVillagerAccount;

    public VillagerLoginLegit(){
        villagerAccounts = new ArrayList<>();
    }

    public void addVillagerAccount(VillagerAccount vac){
        villagerAccounts.add(vac);
    }

    @Override
    public boolean checkSth(Object o) {
        for (VillagerAccount vcc : villagerAccounts){
            if (vcc.getVillagerUser().equals(o)){
                currentVillagerAccount = vcc;
                return false;
            }
        }
        currentVillagerAccount = null;
        return true;
    }

    public boolean checkVillagerUser(String villagerUser, String villagerPass) {
        for (VillagerAccount vac : villagerAccounts) {
            if (vac.getVillagerUser().equals(villagerUser) && vac.getVillagerPass().equals(villagerPass)) {
                currentVillagerAccount = vac;
                return true;
            }
        }
        currentVillagerAccount = null;
        return false;
    }

    public void registerVillager(String villagerName,String villagerUser,String villagerPass,String roomId,String floor){
        for (VillagerAccount vac : villagerAccounts) {
            if (vac.getIdRoom().equals(roomId) && vac.getFloor().equals(floor)) {
                vac.setVillagerName(villagerName);
                vac.setVillagerPass(villagerPass);
                vac.setVillagerUser(villagerUser);
                vac.setRoomStatus("Used");
            }
        }
    }


    public boolean checkStatusRoom(String room){
        for (VillagerAccount vac : villagerAccounts){
            if (vac.getRoomStatus().equals("Empty") && vac.getIdRoom().equals(room) ){
                return true;
            }
        }
        return false;
    }



    public boolean checkArrayList(){
        if(villagerAccounts.size() ==0){
            return true;
        }
        return false;
    }

    public boolean checkCheck(String idRoom,String floor){
        int a = 0;
        int id = Integer.parseInt(idRoom);
        int intFloor = Integer.parseInt(floor);
        for (VillagerAccount vac : villagerAccounts) {
            if ((vac.getIdRoom().equals(idRoom) && vac.getRoomStatus().equals("Empty"))) {
                currentVillagerAccount = vac;
                return true;
            }
//            else if((!vac.getIdRoom().equals(idRoom))){
//                a+=1;
//            }
        }
        if(id>0 && id<=(intFloor*100+10)){
            return true;
        }
        currentVillagerAccount = null;
        return false;
    }

    public boolean checkRoomId(String floor,String idRoom){
        for (VillagerAccount vac : villagerAccounts) {
            if (vac.getFloor().equals(floor) && vac.getIdRoom().equals(idRoom)) {
                currentVillagerAccount = vac;
                return false;
            }
        }
        currentVillagerAccount = null;
        return true;
    }

    public void searchRoom(String idRoom){
        for (VillagerAccount vac : villagerAccounts) {
            if (vac.getIdRoom().equals(idRoom)) {
                currentVillagerAccount = vac;
            }
        }
    }

    public boolean checkOnlyRoom(String idRoom){
        for (VillagerAccount vac : villagerAccounts) {
            if (vac.getIdRoom().equals(idRoom)) {
                currentVillagerAccount = vac;
                return true;
            }
        }
        currentVillagerAccount = null;
        return false;
    }


    public VillagerAccount getCurrentVillagerAccount(){
        return currentVillagerAccount;
    }

    public ArrayList<VillagerAccount> toList(){
        return villagerAccounts;
    }

    public boolean checkDuplicate(String villagerUser){
        for (VillagerAccount vcc : villagerAccounts){
            if (vcc.getVillagerUser().equals(villagerUser)){
                currentVillagerAccount = vcc;
                return false;
            }
        }
        currentVillagerAccount = null;
        return true;
    }


}
