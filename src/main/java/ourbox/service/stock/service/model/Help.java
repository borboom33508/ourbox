package ourbox.service.stock.service.model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Help {
    @FXML Button backBtn,nextBtn;

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException {
        Button bth = (Button) event.getSource();
        Stage stage = (Stage) bth.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/home.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        stage.show();
    }
}
