package ourbox.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ourbox.ChooseService;
import ourbox.myfile.account.StringConfiguration;
import ourbox.myfile.service.*;
import ourbox.service.stock.service.model.Mail;

import java.io.IOException;
import java.util.ArrayList;

public class History {
    private MailDataSource mds,dds,pds;
    private ServiceList mll,dll,pcl;
    private ObservableList<Mail> mailObservableList,documentObservableList,parcelObservableList;

//    private DocumentDataSource dds;
//    private DocumentList dll;
//    private ObservableList<Document> documentObservableList;
//
//    private ParcelDataSource pds;
//    private ParcelList pcl;
//    private ObservableList<Parcel> parcelObservableList;

    String user;
    public void setUser(String user){
        this.user = user;
    }

    @FXML Button backBtn;
    @FXML TableView<Mail> addTable,addTable2,addTable3;
//    @FXML TableView<Document> addTable2;
//    @FXML TableView<Parcel> addTable3;

    @FXML public void initialize(){
        mds = new MailFileDataSource("myData","mailData.csv");
        mll = mds.getAccountsData();

        dds = new DocumentFileDataSource("myData","documentData.csv");
        dll = dds.getAccountsData();

        pds = new ParcelFileDataSource("myData", "parcelData.csv");
        pcl = pds.getAccountsData();

        showAddMailTimeData();
        showAddDocTimeData();
        showAddParcelTimeData();
    }

    @FXML public void showAddMailTimeData(){
        mailObservableList= FXCollections.observableArrayList(mll.toList());
        addTable.setItems(mailObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:ID-room","field:idRoom"));
        configs.add(new StringConfiguration("title:Collect time ","field:addTime"));


        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            addTable.getColumns().add(col);
        }
    }
    @FXML public void showAddDocTimeData(){
        documentObservableList= FXCollections.observableArrayList(dll.toList());
        addTable2.setItems(documentObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:ID-room","field:idRoom"));
        configs.add(new StringConfiguration("title:Collect time ","field:addTime"));


        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            addTable2.getColumns().add(col);
        }
    }

    @FXML public void showAddParcelTimeData(){
        parcelObservableList= FXCollections.observableArrayList(pcl.toList());
        addTable3.setItems(parcelObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:ID-room","field:idRoom"));
        configs.add(new StringConfiguration("title:Collect time ","field:addTime"));


        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            addTable3.getColumns().add(col);
        }
    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException {
        Button bth = (Button) event.getSource();
        Stage stage = (Stage) bth.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/chooseService.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        ChooseService cs = loader.getController();
        cs.setUser(user);

        stage.show();
    }


}
