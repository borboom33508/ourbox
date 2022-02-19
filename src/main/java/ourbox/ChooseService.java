package ourbox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ourbox.account.AdminLoginLegit;
import ourbox.myfile.account.AccountDataSource;
import ourbox.myfile.account.AccountFileDataSource;
import ourbox.service.*;
import ourbox.service.stock.StockMail;


import java.io.IOException;

public class ChooseService {
    String user;
    public void setUser(String user){
        this.user = user;
        showName();
    }
    private AccountDataSource adc;
    private AdminLoginLegit all;

   @FXML Label showAdminName;
    @FXML Button mailBtn,documentBtn,parcelBtn,backToHomeBtn,showVillagerBtn,exportBtn,stockBtn,historyBtn;

    @FXML public void initialize(){
        adc = new AccountFileDataSource("myData","adminAccountList.csv");
        all = adc.getAccountsData();

    }

    @FXML public void handleBackToHomeBtnOnAction(ActionEvent event) throws IOException {
        Button bth = (Button) event.getSource();
        Stage stage = (Stage) bth.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/home.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        stage.show();
    }

    @FXML public void handleMailBtnOnAction(ActionEvent event) throws IOException {
        Button mbt = (Button) event.getSource();
        Stage stage = (Stage) mbt.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/mailFace.fxml"));
        stage.setScene(new Scene(loader.load(),800,600));

        AddMail cs = loader.getController();
        cs.setUser(user);

        stage.show();
    }
    @FXML public void handleHistoryBtnOnAction(ActionEvent event) throws IOException {
        Button mbt = (Button) event.getSource();
        Stage stage = (Stage) mbt.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/history.fxml"));
        stage.setScene(new Scene(loader.load(),800,600));

        History cs = loader.getController();
        cs.setUser(user);

        stage.show();
    }
    @FXML public void handleDocumentBtnOnAction(ActionEvent event) throws IOException {
        Button dbt = (Button) event.getSource();
        Stage stage = (Stage) dbt.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/documentFace.fxml"));
        stage.setScene(new Scene(loader.load(),800,600));

        AddDocument cs = loader.getController();
        cs.setUser(user);
        stage.show();
    }
    @FXML public void handleParcelBtnOnAction(ActionEvent event) throws IOException {
        Button pbt = (Button) event.getSource();
        Stage stage = (Stage) pbt.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/parcelFace.fxml"));
        stage.setScene(new Scene(loader.load(),800,600));

        AddParcel cs = loader.getController();
        cs.setUser(user);
        stage.show();
    }
    @FXML public void handleShowVillagerBtnOnAction(ActionEvent event) throws IOException {
        Button svb = (Button) event.getSource();
        Stage stage = (Stage) svb.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/showVillagerList.fxml"));
        stage.setScene(new Scene(loader.load(),800,600));

        VillagerList cs = loader.getController();
        cs.setUser(user);
        stage.show();
    }
    @FXML public void handleExportBtnOnAction(ActionEvent event) throws IOException {
        Button ep = (Button) event.getSource();
        Stage stage = (Stage) ep.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/exportFace.fxml"));
        stage.setScene(new Scene(loader.load(),800,600));

        Export cs = loader.getController();
        cs.setUser(user);
        stage.show();
    }
    @FXML public void handleStockBtnOnAction(ActionEvent event) throws IOException {
        Button sbt = (Button) event.getSource();
        Stage stage = (Stage) sbt.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/stockMail.fxml"));
        stage.setScene(new Scene(loader.load(),800,600));

        StockMail cs = loader.getController();
        cs.setUser(user);
        stage.show();
    }
    public void showName(){
        showAdminName.setText(user);
    }
}
