package ourbox.service.stock.service.model;

public class Parcel extends Mail {

    private String trackNo;

    public Parcel(String receiverName, String idRoom, String senderName, String trackNo,String size, String image,String addTime) {
        super(receiverName, idRoom, senderName, size, image, addTime);
        this.trackNo = trackNo;
    }

    public String getTrackNo() {
        return trackNo;
    }

    public void setTrackNo(String trackNo) {
        this.trackNo = trackNo;
    }

}
