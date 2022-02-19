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
import ourbox.myfile.service.DocumentFileDataSource;
import ourbox.myfile.service.MailDataSource;
import ourbox.service.stock.StockDocument;
import ourbox.service.stock.service.model.Document;
import ourbox.service.stock.service.model.Mail;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddDocument {

    private Mail dc;
    private ServiceList dcl;
    private MailDataSource dds;
    String user;
    public void setUser(String user){
        this.user = user;
    }

    @FXML Button backToServiceBtn;
    @FXML Button documentSubmitBtn,addFileBtn;
    @FXML Label sendToStatus,roomStatus,fromStatus,sizeStatus,imageStatus,priorityStatus,showStatus2;
    @FXML TextField receiverNameText, roomNumberText, senderNameText, sizeText, prioritizeText,imageText,pathText;

    @FXML public void initialize(){
        dds = new DocumentFileDataSource("myData","documentData.csv");
        dcl = dds.getAccountsData();
    }

    @FXML public void handleBackToServiceBtn(ActionEvent event) throws IOException {
        Button bts = (Button) event.getSource();
        Stage stage = (Stage) bts.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/chooseService.fxml"));
        stage.setScene(new Scene(loader.load(),800,600));

        ChooseService cs = loader.getController();
        cs.setUser(user);
        stage.show();
    }
    @FXML public void handleAddImageBtnOnAction(ActionEvent event){
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
        if(receiverNameText.getText().length() >0 && roomNumberText.getText().length() >0 && senderNameText.getText().length() >0
        && sizeText.getText().length() > 0 && imageText.getText().length() > 0 && prioritizeText.getText().equals("1")
        || prioritizeText.getText().equals("2") || prioritizeText.getText().equals("3") || prioritizeText.getText().equals("4")
        || prioritizeText.getText().equals("5") || !dcl.checkIdRoom(roomNumberText.getText())) {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            Mail dc = new Document(receiverNameText.getText(),roomNumberText.getText(),senderNameText.getText(),sizeText.getText()
            ,pathText.getText(),prioritizeText.getText(),dtf.format(now));

            dcl.addService(dc);
            dds.setAccountData(dcl);

            Button sb2 = (Button) event.getSource();
            Stage stage = (Stage) sb2.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/stockDocument.fxml"));
            stage.setScene(new Scene(loader.load(), 800, 600));

            StockDocument cs = loader.getController();
            cs.setUser(user);
            stage.show();
        }

        if(receiverNameText.getText().length() == 0){
            sendToStatus.setText("*");
        }
        else {
            sendToStatus.setText("");
        }
        if(roomNumberText.getText().length() ==0){
            roomStatus.setText("*");
        }
        else {
            roomStatus.setText("");
        }

        if(senderNameText.getText().length() ==0){
            fromStatus.setText("*");
        }
        else {
            fromStatus.setText("");
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

        if(prioritizeText.getText().length() ==0){
            priorityStatus.setText("*");
        }
        else if(!prioritizeText.getText().equals("1") || !prioritizeText.getText().equals("2") || !prioritizeText.getText().equals("3")
        || !prioritizeText.getText().equals("4") || !prioritizeText.getText().equals("5")){
            priorityStatus.setText("Only 1 - 5");
        }
        else
        {
            priorityStatus.setText("");
        }
    }
}
