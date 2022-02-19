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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ourbox.account.account.model.VillagerAccount;
import ourbox.account.VillagerLoginLegit;
import ourbox.myfile.account.StringConfiguration;
import ourbox.myfile.account.VillagerDataSource;
import ourbox.myfile.account.VillagerFileDataSource;

import java.io.IOException;
import java.util.ArrayList;

public class VillagerList {
    @FXML Button backBtn,searchBtn;
    @FXML TableView<VillagerAccount> villagerTable;
    @FXML Button addVillagerAccountBtn;
    @FXML TextField idRoomText;

    private VillagerDataSource vds;
    private VillagerLoginLegit vlg;
    private ObservableList<VillagerAccount> villagerListObservableList;
    String user;
    public void setUser(String user){
        this.user = user;
    }


    @FXML public void initialize(){
        vds = new VillagerFileDataSource("myData","villagerAccountList.csv");
        vlg = vds.getAccountsData();

        showVillagerData();
    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException {
        Button bt = (Button) event.getSource();
        Stage stage = (Stage) bt.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/chooseService.fxml"));
        stage.setScene(new Scene(loader.load(),800,600));

        ChooseService cs = loader.getController();
        cs.setUser(user);
        stage.show();
    }
    @FXML public void handleAddVillagerAccBtnOnAction(ActionEvent event) throws IOException {
        Button ava = (Button) event.getSource();
        Stage stage = (Stage) ava.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/addVillager.fxml"));
        stage.setScene(new Scene(loader.load(),800,600));

        stage.show();
    }
    @FXML public void showVillagerData(){
        villagerListObservableList = FXCollections.observableArrayList(vlg.toList());
        villagerTable.setItems(villagerListObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Villager name", "field:villagerName"));
        configs.add(new StringConfiguration("title:Id-Room", "field:idRoom"));
        configs.add(new StringConfiguration("title:Floor", "field:floor"));
        configs.add(new StringConfiguration("title:Type", "field:typeOfRoom"));
        configs.add(new StringConfiguration("title:Username", "field:villagerUser"));
        configs.add(new StringConfiguration("title:Password", "field:villagerPass"));
        configs.add(new StringConfiguration("title:Status", "field:roomStatus"));


        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            villagerTable.getColumns().add(col);
        }
    }

    @FXML public void handleSearchBtnOnAction(ActionEvent event) throws IOException {
        if(vlg.checkOnlyRoom(idRoomText.getText())){

            villagerTable.getItems().clear();

            villagerListObservableList = FXCollections.observableArrayList(vlg.getCurrentVillagerAccount());
            villagerTable.setItems(villagerListObservableList);

            ArrayList<StringConfiguration> configs = new ArrayList<>();
            configs.add(new StringConfiguration("title:Villager name", "field:villagerName"));
            configs.add(new StringConfiguration("title:Id-Room", "field:idRoom"));
            configs.add(new StringConfiguration("title:Floor", "field:floor"));
            configs.add(new StringConfiguration("title:Type", "field:typeOfRoom"));
            configs.add(new StringConfiguration("title:Username", "field:villagerUser"));
            configs.add(new StringConfiguration("title:Password", "field:villagerPass"));

        }
    }
}
