package org.isep.financialproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class TransactionViewController {
    @FXML
    Button ATButton;

    public void openAHT() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddTransaction.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Add Transaction");
        stage.setScene(new Scene(root));
        stage.show();


    }
}
