package ourbox;


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
import ourbox.account.account.model.AdminAccount;
import ourbox.account.AdminLoginLegit;
import ourbox.myfile.account.AccountDataSource;
import ourbox.myfile.account.AccountFileDataSource;
import ourbox.myfile.account.StringConfiguration;


import java.io.IOException;
import java.util.ArrayList;

public class AdminList {
    @FXML Button backBtn;
    @FXML TableView<AdminAccount> adminTable;
    @FXML Button addAdminAccountBtn;
    @FXML Button changePasswordBtn;

    private AccountDataSource ads;
    private AdminLoginLegit lgt;
    private ObservableList<AdminAccount> adminAccountObservableList;
//    public void setLoginLegit(LoginLegit lgt){
//        this.lgt = lgt;
//    }

    @FXML public void initialize(){
        ads = new AccountFileDataSource("myData","adminAccountList.csv");
        lgt = ads.getAccountsData();
        showAdminData();
    }
    @FXML public void handleAddAdminAccountBtnOnAction(ActionEvent event) throws IOException {
        Button aaa = (Button) event.getSource();
        Stage stage = (Stage) aaa.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/addAdmin.fxml"));
        stage.setScene(new Scene(loader.load(),800,600));

        stage.show();
    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException {
        Button bbt = (Button) event.getSource();
        Stage stage = (Stage) bbt.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/home.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        stage.show();
    }
    @FXML public void handleChangePasswordBtnOnAction(ActionEvent event) throws IOException {
        Button cpw = (Button) event.getSource();
        Stage stage = (Stage) cpw.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/changePassword.fxml"));
        stage.setScene(new Scene(loader.load(),800,600));

        stage.show();
    }

    private void showAdminData() {
        adminAccountObservableList = FXCollections.observableArrayList(lgt.toList());
        adminTable.setItems(adminAccountObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Admin name", "field:firstName"));
        configs.add(new StringConfiguration("title:Username", "field:user"));
        configs.add(new StringConfiguration("title:Password", "field:pass"));
        configs.add(new StringConfiguration("title:Recent login", "field:time"));

        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            adminTable.getColumns().add(col);
        }
    }

}
