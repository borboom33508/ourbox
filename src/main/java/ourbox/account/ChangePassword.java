package ourbox.account;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import ourbox.account.account.model.SystemAdminAccount;
import ourbox.myfile.account.SystemAdminDataSource;
import ourbox.myfile.account.SystemAdminFileDataSource;

import java.io.IOException;

public class ChangePassword {
    private SystemAdminLoginLegit sal;
    private SystemAdminAccount saa;
    private SystemAdminDataSource sad;

    @FXML Button backBtn;
    @FXML PasswordField currentPassword;
    @FXML PasswordField newPassword;
    @FXML PasswordField repeatNewPassword;
    @FXML Button submitBtn;
    @FXML Label showStatus;

    @FXML public void initialize(){
        sad = new SystemAdminFileDataSource("myData","systemAdminAccountList.csv");
        sal = sad.getAccountsData();
    }
    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException {
        Button bb = (Button) event.getSource();
        Stage stage = (Stage) bb.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/showAdminList.fxml"));
        stage.setScene(new Scene(loader.load(),800,600));

        stage.show();
    }
    @FXML public void handleSubmitBtnOnAction(ActionEvent event) throws IOException {
        if (newPassword.getText().equals(repeatNewPassword.getText()) && newPassword.getText().length() > 0 && sal.checkCurrentPassword(currentPassword.getText())){

            sal.setPassword(newPassword.getText());
            sad.setAccountData(sal);
            Button sb = (Button) event.getSource();
            Stage stage = (Stage) sb.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/showAdminList.fxml"));
            stage.setScene(new Scene(loader.load(),800,600));


            stage.show();
        }
        else {
            showStatus.setText("Please try agian.");
        }
    }
}
