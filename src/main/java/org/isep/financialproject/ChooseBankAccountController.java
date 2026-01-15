package org.isep.financialproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class ChooseBankAccountController {

   @FXML
    private Button CheckingsButton;

   @FXML
    private Button SavingsButton;

    private User user;

    public void setUser(User user){
        this.user = user;
    }


    @FXML
    private void openCV() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateChecking.fxml"));
            Parent root = fxmlLoader.load();

            CreateCheckingController controller = fxmlLoader.getController();
            controller.setUser(user);

            Stage stage = new Stage();
            stage.setTitle("Create Checking Account");
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void openSV() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateSavings.fxml"));
            Parent root = fxmlLoader.load();

            CreateSavingsController controller = fxmlLoader.getController();
            controller.setUser(user);

            Stage stage = new Stage();
            stage.setTitle("Create savings Account");
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
