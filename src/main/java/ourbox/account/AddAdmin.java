package ourbox.account;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ourbox.account.account.model.AdminAccount;
import ourbox.account.account.model.SystemAdminAccount;
import ourbox.myfile.account.AccountDataSource;
import ourbox.myfile.account.AccountFileDataSource;
import ourbox.myfile.account.SystemAdminDataSource;
import ourbox.myfile.account.SystemAdminFileDataSource;

import java.io.IOException;

public class AddAdmin {
    private AdminAccount aa;
    private AdminLoginLegit all;
    private AccountDataSource ads;
    private SystemAdminAccount saa;
    private SystemAdminLoginLegit sal;
    private SystemAdminDataSource sad;

    @FXML Button backBtn,submitBtn;
    @FXML TextField addFirstName;
    @FXML TextField addUsername;
    @FXML PasswordField addPassword,repeatPassword;
    @FXML Label showStatus,firstNameStatus,usernameStatus,passStatus,repassStatus;

    @FXML public void initialize(){
        ads = new AccountFileDataSource("myData","adminAccountList.csv");
        all = ads.getAccountsData();

        sad = new SystemAdminFileDataSource("myData","systemAdminAccountList.csv");
        sal = sad.getAccountsData();

    }
    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException {
        Button bt = (Button) event.getSource();
        Stage stage = (Stage) bt.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/showAdminList.fxml"));
        stage.setScene(new Scene(loader.load(),800,600));

        stage.show();
    }

    @FXML public void handleSubmitBtnOnAction(ActionEvent event) throws IOException {
        if (all.checkSth(addUsername.getText().toLowerCase()) && addUsername.getText().length() >0 && addPassword.getText().length() >0
                && addFirstName.getText().length() >0 && addPassword.getText().equals(repeatPassword.getText())
        && repeatPassword.getText().length() > 0 && sal.checkDuplicateSystemAdmin(addUsername.getText())){

            aa = new AdminAccount(addFirstName.getText(),addUsername.getText().toLowerCase(),addPassword.getText());
            all.addAccount(aa);
            ads.setAccountData(all);

            Button sb = (Button) event.getSource();
            Stage stage = (Stage) sb.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/showAdminList.fxml"));
            stage.setScene(new Scene(loader.load(),800,600));

            stage.show();
        }
        else if(!addPassword.getText().equals(repeatPassword.getText())){
            showStatus.setText("Wrong repeat-password.");
        }
        else if (!all.checkSth(addUsername.getText()))
        {
            showStatus.setText("Username already used.");
        }
        else {
            showStatus.setText("Cant add account.");
        }
        if(addFirstName.getText().length()==0){
            firstNameStatus.setText("*");
        }
        else {
            firstNameStatus.setText("");
        }
        if(addUsername.getText().length()==0){
            usernameStatus.setText("*");
        }
        else {
            usernameStatus.setText("");
        }
        if(addPassword.getText().length()==0){
            passStatus.setText("*");
        }
        else {
            passStatus.setText("");
        }
        if(repeatPassword.getText().length()==0){
            repassStatus.setText("*");
        }
        else {
            repassStatus.setText("");
        }
    }
}
