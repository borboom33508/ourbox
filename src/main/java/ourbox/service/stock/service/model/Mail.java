package ourbox.service.stock.service.model;


public class Mail{

    private String receiverName;
    private String idRoom;
    private String senderName;
    private String size;
    private String image;
    private String addTime;

    public Mail(String receiverName, String idRoom, String senderName, String size, String image,String addTime) {
        this.receiverName = receiverName;
        this.idRoom = idRoom;
        this.senderName = senderName;
        this.size = size;
        this.image = image;
        this.addTime = addTime;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getIdRoom() {
        return idRoom;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getSize() {
        return size;
    }

    public String getImage() {
        return image;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
}
