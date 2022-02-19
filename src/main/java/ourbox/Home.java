package ourbox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import ourbox.account.*;
import ourbox.account.account.model.SystemAdminAccount;
import ourbox.account.account.model.VillagerAccount;
import ourbox.myfile.account.*;

import java.io.IOException;


public class Home {

    @FXML Button aboutDev,helpBtn;
    @FXML TextField usernameLogin;
    @FXML PasswordField passwordLogin;
    @FXML Label showStatus;

    private AccountDataSource adc;
    private AdminLoginLegit lgt;

    private VillagerLoginLegit vlg;
    private VillagerAccount va;
    private VillagerDataSource vds;

    private SystemAdminLoginLegit sal;
    private SystemAdminAccount saa;
    private SystemAdminDataSource sda;


    @FXML public void initialize() {
        lgt = new AdminLoginLegit();
        vlg = new VillagerLoginLegit();

        vds = new VillagerFileDataSource("myData","villagerAccountList.csv");
        sda = new SystemAdminFileDataSource("myData","systemAdminAccountList.csv");
        adc = new AccountFileDataSource("myData","adminAccountList.csv");

        vlg = vds.getAccountsData();
        lgt = adc.getAccountsData();
        sal = sda.getAccountsData();

        if (sal.checkFile()){
            saa = new SystemAdminAccount("system","1234");
            sal.addSystemAdminAccount(saa);
            sda.setAccountData(sal);
        }
        if (vlg.checkArrayList()){
            for(int i =100; i<=500 ;i+=100){
                for(int j=1; j<=10 ; j++){
                    String a = Integer.toString(i+j);
                    va = new VillagerAccount(null,a,Integer.toString(i/100),"Single",null,null,"Empty");
                    vlg.addVillagerAccount(va);
                }
            }

            for(int i =500; i<=1000 ;i+=100){
                for(int j=1; j<=10 ; j++){
                    String a = Integer.toString(i+j);
                    va = new VillagerAccount(null,a,Integer.toString(i/100),"Double",null,null,"Empty");
                    vlg.addVillagerAccount(va);

                }
            }
            vds.setAccountData(vlg);
        }


        adc.setAccountData(lgt);
    }
    @FXML public void handleLoginBtnOnAction (ActionEvent event) throws IOException {
        try {
            if (sal.checkSystemAdminUser(usernameLogin.getText(), passwordLogin.getText())) { //usernameLogin.getText().equals("1234") && passwordLogin.getText().equals("1234")
                Button lg = (Button) event.getSource();
                Stage stage = (Stage) lg.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/showAdminList.fxml"));
                stage.setScene(new Scene(loader.load(), 800, 600));

                stage.show();
            } else if (lgt.checkUser(usernameLogin.getText().toLowerCase(), passwordLogin.getText())) {
                adc.setAccountData(lgt);
                Button lg = (Button) event.getSource();
                Stage stage = (Stage) lg.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/chooseService.fxml"));
                stage.setScene(new Scene(loader.load(), 800, 600));

                ChooseService cs = loader.getController();
                cs.setUser(usernameLogin.getText());
//            cs.showAdminName.setText(usernameLogin.getText());

                System.out.println(usernameLogin.getText());
                stage.show();
            } else if (vlg.checkVillagerUser(usernameLogin.getText(), passwordLogin.getText())) {
                Button lg = (Button) event.getSource();
                Stage stage = (Stage) lg.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/villagerInbox.fxml"));
                stage.setScene(new Scene(loader.load(), 800, 600));

                stage.show();
            } else {
                showStatus.setText("LOGIN FAILED !");
            }
        }catch (NullPointerException e){
            System.out.println("LOGIN");
        }
    }
//
//    @FXML public void handleAmAdminOnAction(ActionEvent event) throws IOException {
//        Button a = (Button) event.getSource();
//        Stage stage = (Stage) a.getScene().getWindow();
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminLogin.fxml"));
//        stage.setScene(new Scene(loader.load(),800,600));
//
//        AdminLogin al = loader.getController();
//        stage.show();
//    }
//    @FXML public void handleAmVillagerOnAction(ActionEvent event) throws IOException {
//        Button b = (Button) event.getSource();
//        Stage stage = (Stage) b.getScene().getWindow();
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/villagerLogin.fxml"));
//        stage.setScene(new Scene(loader.load(),800,600));
//
//        VillagerLogin vl = loader.getController();
//        stage.show();
//    }

    @FXML public void handleAboutDevOnAction(ActionEvent event) throws IOException {
        Button md =(Button) event.getSource();
        Stage stage = (Stage) md.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/aboutMeInfo.fxml"));
        stage.setScene(new Scene(loader.load(),800,600));

        stage.show();
    }
    @FXML public void handleHelpBtnOnAction(ActionEvent event) throws IOException {
        Button hp =(Button) event.getSource();
        Stage stage = (Stage) hp.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/help.fxml"));
        stage.setScene(new Scene(loader.load(),800,600));

        stage.show();
    }
}
