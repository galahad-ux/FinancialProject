package org.isep.financialproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ChooseBankAccountController {
    @FXML
    private void handleChecking(ActionEvent event) throws IOException {
        goTo(event, "CreateChecking.fxml");
    }

    @FXML
    private void handleSavings(ActionEvent event) throws IOException {
        goTo(event, "CreateSavings.fxml");
    }

    private void goTo(ActionEvent event, String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
