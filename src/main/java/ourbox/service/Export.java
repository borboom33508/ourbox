package ourbox.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ourbox.ChooseService;
import ourbox.myfile.account.StringConfiguration;
import ourbox.myfile.service.*;
import ourbox.service.stock.service.model.Mail;

import java.io.IOException;
import java.util.ArrayList;

public class Export {
    private MailDataSource mds,dds,pds;
    private ServiceList mll,dll,pcl;
    private ObservableList<Mail> mailObservableList,documentObservableList,parcelObservableList;

//    private DocumentDataSource dds;
//    private DocumentList dll;
//    private ObservableList<Document> documentObservableList;

//    private ParcelDataSource pds;
//    private ParcelList pcl;
//    private ObservableList<Parcel> parcelObservableList;

    String user;
    public void setUser(String user){
        this.user = user;
    }
    @FXML Button backBtn,exportBtn;
    @FXML TextField idRoomCheck;
    @FXML ComboBox comboService;
    @FXML Label showStatus,exportedText;
    @FXML TableView<Mail> mailTable,documentTable,parcelTable;
//    @FXML TableView<Document> documentTable;
//    @FXML TableView<Parcel> parcelTable;


    @FXML public void initialize(){
        comboService.getItems().addAll("Mail","Document","Parcel");

        mds = new MailFileDataSource("myData","mailData.csv");
        mll = mds.getAccountsData();

        dds = new DocumentFileDataSource("myData","documentData.csv");
        dll = dds.getAccountsData();

        pds = new ParcelFileDataSource("myData", "parcelData.csv");
        pcl = pds.getAccountsData();

        showMailData();
        showDocumentData();
        showParcelData();
    }

    @FXML public void showMailData(){
        mailObservableList= FXCollections.observableArrayList(mll.toList());
        mailTable.setItems(mailObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:ID-room","field:idRoom"));
        configs.add(new StringConfiguration("title:Name","field:receiverName"));
        configs.add(new StringConfiguration("title:Sender name","field:senderName"));
        configs.add(new StringConfiguration("title:Size","field:size"));
        configs.add(new StringConfiguration("title:Image","field:image"));
        configs.add(new StringConfiguration("title:Collect time ","field:addTime"));


        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            mailTable.getColumns().add(col);
        }
    }

    @FXML public void showDocumentData(){
        documentObservableList = FXCollections.observableArrayList(dll.toList());
        documentTable.setItems(documentObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:ID-room","field:idRoom"));
        configs.add(new StringConfiguration("title:Name","field:receiverName"));
        configs.add(new StringConfiguration("title:Sender name","field:senderName"));
        configs.add(new StringConfiguration("title:Size","field:size"));
        configs.add(new StringConfiguration("title:Image","field:image"));
        configs.add(new StringConfiguration("title:Prioritize","field:prioritize"));
        configs.add(new StringConfiguration("title:Collect time","field:addTime"));


        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            documentTable.getColumns().add(col);
        }
    }

    @FXML public void showParcelData(){
        parcelObservableList= FXCollections.observableArrayList(pcl.toList());
        parcelTable.setItems(parcelObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:ID-room","field:idRoom"));
        configs.add(new StringConfiguration("title:Name","field:receiverName"));
        configs.add(new StringConfiguration("title:Service","field:senderName"));
        configs.add(new StringConfiguration("title:Tracking No","field:trackNo"));
        configs.add(new StringConfiguration("title:Size","field:size"));
        configs.add(new StringConfiguration("title:Image","field:image"));
        configs.add(new StringConfiguration("title:Collect time","field:addTime"));

        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            parcelTable.getColumns().add(col);
        }
    }

    @FXML public void handleExportBtnOnAction(ActionEvent event) throws IOException {
        try {
            if(idRoomCheck.getText().length() > 0) {

                switch (comboService.getValue().toString()) {
                    case "Mail": {
                        mll.removeAccount(idRoomCheck.getText());
                        mds.setAccountData(mll);

                        Button eb = (Button) event.getSource();
                        Stage stage = (Stage) eb.getScene().getWindow();

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/exportFace.fxml"));
                        stage.setScene(new Scene(loader.load(), 800, 600));

                        Export cs = loader.getController();
                        cs.setUser(user);

                        stage.show();
                        break;
                    }
                    case "Document": {
                        dll.removeAccount(idRoomCheck.getText());
                        dds.setAccountData(dll);

                        Button eb = (Button) event.getSource();
                        Stage stage = (Stage) eb.getScene().getWindow();

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/exportFace.fxml"));
                        stage.setScene(new Scene(loader.load(), 800, 600));

                        Export cs = loader.getController();
                        cs.setUser(user);                        stage.show();
                        break;
                    }
                    case "Parcel": {
                        pcl.removeAccount(idRoomCheck.getText());
                        pds.setAccountData(pcl);

                        Button eb = (Button) event.getSource();
                        Stage stage = (Stage) eb.getScene().getWindow();

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/exportFace.fxml"));
                        stage.setScene(new Scene(loader.load(), 800, 600));

                        Export cs = loader.getController();
                        cs.setUser(user);
                        stage.show();
                        break;
                    }
                }
            }
        }catch (NullPointerException e){
            System.out.println("NullPointerException");
        }
    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException {
        Button bts = (Button) event.getSource();
        Stage stage = (Stage) bts.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/chooseService.fxml"));
        stage.setScene(new Scene(loader.load(),800,600));

        ChooseService cs = loader.getController();
        cs.setUser(user);
        stage.show();
    }
}
