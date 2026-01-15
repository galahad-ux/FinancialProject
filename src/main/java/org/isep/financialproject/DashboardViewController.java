package org.isep.financialproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class DashboardViewController {
    @FXML
    private Button APButton;


    public void openAPV() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPortofolio.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Add Portfolio");
        stage.setScene(new Scene(root));
        stage.show();


    }


}




