package ourbox.service.stock;

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
import ourbox.myfile.account.StringConfiguration;
import ourbox.myfile.service.DocumentFileDataSource;
import ourbox.myfile.service.MailDataSource;
import ourbox.service.ServiceList;
import ourbox.service.stock.service.model.Mail;

import java.io.IOException;
import java.util.ArrayList;

public class StockDocument {

    private MailDataSource dds;
    private ServiceList dll;
    private ObservableList<Mail> documentObservableList;
    String user;
    public void setUser(String user){
        this.user = user;
    }

    @FXML TableView<Mail> documentTable;
    @FXML Button backBtn, nextBtn;

    @FXML public void initialize(){
        dds = new DocumentFileDataSource("myData","documentData.csv");
        dll = dds.getAccountsData();
        showDocumentData();
    }

    @FXML private void showDocumentData(){
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
    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException {
        Button bts = (Button) event.getSource();
        Stage stage = (Stage) bts.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/stockMail.fxml"));
        stage.setScene(new Scene(loader.load(),800,600));

        StockMail cs = loader.getController();
        cs.setUser(user);
        stage.show();
    }

    @FXML public void handleNextBtnOnAction(ActionEvent event) throws IOException {
        Button bts = (Button) event.getSource();
        Stage stage = (Stage) bts.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/stockParcel.fxml"));
        stage.setScene(new Scene(loader.load(),800,600));

        StockParcel cs = loader.getController();
        cs.setUser(user);
        stage.show();
    }
}
