package ourbox.service.stock.service.model;

public class Document extends Mail {
    private String prioritize;

    public Document(String receiverName, String idRoom, String senderName, String size, String image,String prioritize,String addTime) {
        super(receiverName, idRoom, senderName, size, image, addTime);
        this.prioritize = prioritize;
    }
    public String getPrioritize() {
        return prioritize;
    }

    public void setPrioritize(String prioritize) {
        this.prioritize = prioritize;
    }
}
