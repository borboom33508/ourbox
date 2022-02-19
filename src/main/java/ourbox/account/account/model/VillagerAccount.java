package ourbox.account.account.model;

public class VillagerAccount  {
    private String idRoom;
    private String villagerName;
    private String floor;
    private String typeOfRoom;
    private String villagerUser;
    private String villagerPass;
    private String roomStatus;

    public VillagerAccount(String villagerName,String idRoom, String floor,String typeOfRoom
            , String villagerUser, String villagerPass,String roomStatus){
        this.villagerName = villagerName;
        this.idRoom = idRoom;
        this.floor = floor;
        this.typeOfRoom = typeOfRoom;
        this.roomStatus = roomStatus;
        this.villagerUser = villagerUser;
        this.villagerPass = villagerPass;
    }

    public String getVillagerName() {
        return villagerName;
    }

    public String getIdRoom(){ return  idRoom;}

    public String getFloor() {
        return floor;
    }

    public String getTypeOfRoom() {
        return typeOfRoom;
    }

    public String getVillagerUser() {
        return villagerUser;
    }

    public String getVillagerPass() {
        return villagerPass;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setVillagerName(String villagerName) {
        this.villagerName = villagerName;
    }

    public void setIdRoom(String idRoom){ this.idRoom = idRoom;}

    public void setFloor(String  floor) {
        this.floor = floor;
    }

    public void setTypeOfRoom(String typeOfRoom) {
        this.typeOfRoom = typeOfRoom;
    }

    public void setVillagerUser(String villagerUser) {
        this.villagerUser = villagerUser;
    }

    public void setVillagerPass(String villagerPass) {
        this.villagerPass = villagerPass;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }
}
