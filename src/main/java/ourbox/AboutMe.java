package ourbox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AboutMe {

    @FXML Button backBtn;

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException {
        Button bt = (Button)event.getSource();
        Stage stage = (Stage)bt.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/home.fxml"));
        stage.setScene(new Scene(loader.load(),800,600));

        stage.show();
    }
}
