package ourbox.account;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ourbox.account.account.model.VillagerAccount;
import ourbox.myfile.account.VillagerDataSource;
import ourbox.myfile.account.VillagerFileDataSource;

import java.io.IOException;

public class AddVillager {
    private VillagerAccount va;
    private VillagerLoginLegit vll;
    private VillagerDataSource vds;

    @FXML Button backBtn,submitBtn;
    @FXML TextField addVillagerName,addIdRoom,addVillagerUser;
    @FXML PasswordField addVillagerPass,addRepeatPass;
    @FXML Label showStatus,nameStatus,floorStatus,roomStatus,userStatus,passStatus,rePassStatus,typeStatus,showStatus2;
    @FXML ComboBox comboType,comboFloor;

    @FXML public void initialize(){
        comboFloor.getItems().removeAll();
        comboFloor.getItems().addAll("1","2","3","4","5","6","7","8","9","10");
        comboType.getItems().removeAll();
        comboType.getItems().addAll("Single","Double");

        vds = new VillagerFileDataSource("myData","villagerAccountList.csv");
        vll = vds.getAccountsData();
    }
    

    @FXML public void handleSubmitBtnOnAction(ActionEvent event) throws IOException {
        if(comboFloor.getValue()!=null && addIdRoom.getText().length() >0){
            int floor = Integer.parseInt(comboFloor.getValue().toString());
            int roomId = Integer.parseInt(addIdRoom.getText());

            if(addVillagerUser.getText().length() > 0 && vll.checkDuplicate(addVillagerUser.getText())
                    && addVillagerName.getText().length() >0 && comboFloor.getValue().toString()!=null
                    && addVillagerPass.getText().length() > 0 && addVillagerPass.getText().equals(addRepeatPass.getText())
                    && addVillagerName.getText().length() > 0 && addIdRoom.getText().length() > 0 && comboType.getValue()!=null
                    && vll.checkCheck(addIdRoom.getText(),comboFloor.getValue().toString())){
                System.out.println("3");

                if (!vll.checkRoomId(comboFloor.getValue().toString(),addIdRoom.getText()) && vll.checkStatusRoom(addIdRoom.getText())){
                    vll.registerVillager(addVillagerName.getText(),addVillagerUser.getText(),addVillagerPass.getText(),addIdRoom.getText(),comboFloor.getValue().toString());
                    vds.setAccountData(vll);

                    System.out.println("1");

                    Button sb = (Button) event.getSource();
                    Stage stage = (Stage) sb.getScene().getWindow();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/showVillagerList.fxml"));
                    stage.setScene(new Scene(loader.load(), 800, 600));

                    stage.show();
                }

                else if(vll.checkRoomId(comboFloor.getValue().toString(),addIdRoom.getText()) && roomId>100 && roomId<=(floor*100+10)){
                    va = new VillagerAccount(addVillagerName.getText(), addIdRoom.getText(), comboFloor.getValue().toString(), comboType.getValue().toString()
                        , addVillagerUser.getText(), addVillagerPass.getText(),"Used");
                    vll.addVillagerAccount(va);

                    vds.setAccountData(vll);

                    System.out.println("2");

                    Button sb = (Button) event.getSource();
                    Stage stage = (Stage) sb.getScene().getWindow();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/showVillagerList.fxml"));
                    stage.setScene(new Scene(loader.load(), 800, 600));

                    stage.show();
                }
            }
            if(addVillagerUser.getText().length() > 0 && vll.checkDuplicate(addVillagerUser.getText())
                    && addVillagerName.getText().length() >0 && comboFloor.getValue().toString()!=null
                    && addVillagerPass.getText().length() > 0 && addVillagerPass.getText().equals(addRepeatPass.getText())
                    && addVillagerName.getText().length() > 0 && addIdRoom.getText().length() > 0 && comboType.getValue()!=null)
            {

                showStatus.setText("Id-room already used. or Out of range.");
            }
            if(!(vll.checkDuplicate(addVillagerUser.getText()))){
                showStatus.setText("Username already used.");
            }
        }
        if (addVillagerName.getText().length()==0){
            nameStatus.setText("*");
        }
        else {
            nameStatus.setText("");
        }
        if(comboFloor.getValue()==null){
            floorStatus.setText("*");
        }
        else {
            floorStatus.setText("");
        }
        if(addIdRoom.getText().length()==0){
            roomStatus.setText("*");
        }
        else {
            roomStatus.setText("");
        }
        if(addVillagerUser.getText().length()==0){
            userStatus.setText("*");
        }
        else {
            userStatus.setText("");
        }
        if(addVillagerPass.getText().length()==0){
            passStatus.setText("*");
        }
        else {
            passStatus.setText("");
        }
        if(addRepeatPass.getText().length()==0){
            rePassStatus.setText("*");
        }
        else {
            rePassStatus.setText("");
        }
        if(comboType.getValue()==null){
            typeStatus.setText("*");
        }
        else {
            typeStatus.setText("");
        }
        if(!addVillagerPass.getText().equals(addRepeatPass.getText())){
            showStatus2.setText("Wrong repeat password.");
        }
        else if(addVillagerPass.getText().equals(addRepeatPass.getText())){
            showStatus2.setText("");
        }
        else if(!vll.checkDuplicate(addVillagerUser.getText())){
            showStatus.setText("Username already used.");
        }
        else if (vll.checkRoomId(comboFloor.getValue().toString(),addIdRoom.getText())){
            showStatus.setText("Id-room already used.");
        }
        else if (!vll.checkRoomId(comboFloor.getValue().toString(),addIdRoom.getText())){
            showStatus.setText("");
        }
        else {
            showStatus2.setText("");
        }

    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException {
        Button bt = (Button) event.getSource();
        Stage stage = (Stage) bt.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/showVillagerList.fxml"));
        stage.setScene(new Scene(loader.load(),800,600));

        stage.show();
    }

}
