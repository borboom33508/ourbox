package ourbox.account; //not used

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
import ourbox.account.account.model.AdminAccount;
import ourbox.myfile.account.AccountDataSource;
import ourbox.myfile.account.AccountFileDataSource;
import ourbox.myfile.account.StringConfiguration;

import java.io.IOException;
import java.util.ArrayList;

public class AdminLogin {
    @FXML Button adminLoginBtn;
    @FXML Button backBtn;
    @FXML Label showStatus;
    @FXML TextField usernameLogin;
    @FXML PasswordField passwordLogin;
    @FXML TableView<AdminAccount> adminTable;
    private ObservableList<AdminAccount> accountsObservableList;

    private AccountDataSource adc;
    private AdminLoginLegit lgt;
    public void setLoginLegit(AdminLoginLegit lgt){
        this.lgt = lgt;
    }

    @FXML public void initialize(){
        adc = new AccountFileDataSource("myData","adminAccountList.csv");
        lgt = adc.getAccountsData();
        showAccountData();
    }
    private void showAccountData() {
        accountsObservableList = FXCollections.observableArrayList(lgt.toList());
        adminTable.setItems(accountsObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Admin name", "field:firstName"));

        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            adminTable.getColumns().add(col);
        }
    }

    @FXML public void handleAdminLoginBtnOnAction(ActionEvent event) throws IOException {
    if (lgt.checkUser(usernameLogin.getText(),passwordLogin.getText())) {
        Button alg = (Button) event.getSource();
        Stage stage = (Stage) alg.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/chooseService.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        ChooseService cs = loader.getController();
        stage.show();
    }
    else {
        showStatus.setText("Login failed");
    }
    }
    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException {
        Button c = (Button) event.getSource();
        Stage stage = (Stage) c.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/home.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        stage.show();
    }
}
