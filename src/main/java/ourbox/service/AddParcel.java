package ourbox.service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ourbox.ChooseService;
import ourbox.myfile.service.MailDataSource;
import ourbox.myfile.service.ParcelFileDataSource;
import ourbox.service.stock.StockParcel;
import ourbox.service.stock.service.model.Mail;
import ourbox.service.stock.service.model.Parcel;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddParcel {

    private Mail pc;
    private ServiceList pcl;
    private MailDataSource pds;
    String user;
    public void setUser(String user){
        this.user = user;
    }

    @FXML Button backToServiceBtn;
    @FXML Button parcelSubmitBtn,addFileBtn;
    @FXML Label sendToStatus,roomStatus,serviceStatus,trackStatus,sizeStatus,imageStatus;
    @FXML TextField receiverNameText, roomNumberText, serviceNameText, trackNumberText, sizeText,imageText,pathText;

    @FXML public void initialize(){
        pds = new ParcelFileDataSource("myData","parcelData.csv");
        pcl = pds.getAccountsData();
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
    @FXML public void handleAddFileBtnOnAction(ActionEvent event){
        final FileChooser chooser = new FileChooser();
//        Stage stage = (Stage) put.getScene().getWindow();

        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = chooser.showOpenDialog(addFileBtn.getScene().getWindow());

        if (file != null){
            imageText.setText(file.getPath());
            try {
                // CREATE FOLDER IF NOT EXIST
                File destDir = new File("images");
                destDir.mkdirs();

                // RENAME FILE
                String[] fileSplit = file.getName().split("\\.");
                String filename = LocalDate.now()+"_"+System.currentTimeMillis()+"."+fileSplit[fileSplit.length - 1];
                Path target = FileSystems.getDefault().getPath(destDir.getAbsolutePath()+System.getProperty("file.separator")+filename);
                // COPY WITH FLAG REPLACE FILE IF FILE IS EXIST
                File f =target.toFile();
                Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING );
                // SET NEW FILE PATH TO IMAGE
                pathText.setText(f.toURI().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML public void handleSubmitBtnOnAction(ActionEvent event) throws IOException {
        if(receiverNameText.getText().length() > 0 && roomNumberText.getText().length() > 0 && serviceNameText.getText().length() > 0
        && trackNumberText.getText().length() > 0 && sizeText.getText().length() > 0 && imageText.getText().length() > 0) {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            pc = new Parcel(receiverNameText.getText(),roomNumberText.getText(),serviceNameText.getText(),trackNumberText.getText()
            ,sizeText.getText(),pathText.getText(),dtf.format(now));

            pcl.addService(pc);
            pds.setAccountData(pcl);

            Button sb3 = (Button) event.getSource();
            Stage stage = (Stage) sb3.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/stockParcel.fxml"));
            stage.setScene(new Scene(loader.load(), 800, 600));

            StockParcel cs = loader.getController();
            cs.setUser(user);

            stage.show();
        }
        if(receiverNameText.getText().length() ==0){
            sendToStatus.setText("*");
        }
        else{
            sendToStatus.setText("");
        }
        if(roomNumberText.getText().length()==0){
            roomStatus.setText("*");
        }
        else {
            roomStatus.setText("");
        }
        if(serviceNameText.getText().length()==0){
            serviceStatus.setText("*");
        }
        else {
            serviceStatus.setText("");
        }
        if(trackNumberText.getText().length()==0){
            trackStatus.setText("*");
        }
        else {
            trackStatus.setText("");
        }
        if(sizeText.getText().length()==0){
            sizeStatus.setText("*");
        }
        else {
            sizeStatus.setText("");
        }
        if(imageText.getText().length()==0){
            imageStatus.setText("*");
        }
        else {
            imageStatus.setText("");
        }
    }
}
