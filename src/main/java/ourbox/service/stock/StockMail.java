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
import ourbox.ChooseService;
import ourbox.myfile.service.*;
import ourbox.myfile.account.StringConfiguration;
import ourbox.service.*;
import ourbox.service.stock.service.model.Mail;

import java.io.IOException;
import java.util.ArrayList;

public class StockMail {

    private MailDataSource mds;
    private ServiceList mll;
    private ObservableList<Mail> mailObservableList;
    String user;
    public void setUser(String user){
        this.user = user;
    }

    @FXML Button backToServiceBtn,nextBtn;
    @FXML TableView<Mail> mailTable;

    @FXML public void initialize(){
        mds = new MailFileDataSource("myData","mailData.csv");
        mll = mds.getAccountsData();
        showMailData();
    }

    @FXML private void showMailData(){
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


    @FXML public void handleBackToServiceOnAction(ActionEvent event) throws IOException {
        Button bts = (Button) event.getSource();
        Stage stage = (Stage) bts.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/chooseService.fxml"));
        stage.setScene(new Scene(loader.load(),800,600));

        ChooseService cs = loader.getController();
        cs.setUser(user);
        stage.show();
    }
    @FXML public void handleNextBtnOnAction(ActionEvent event) throws IOException {
        Button dn = (Button) event.getSource();
        Stage stage = (Stage) dn.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/stockDocument.fxml"));
        stage.setScene(new Scene(loader.load(),800,600));

        StockDocument cs = loader.getController();
        cs.setUser(user);
        stage.show();

    }
}
