package org.isep.financialproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class AddPortofolioController {
    @FXML
    private Button BankButton;

    @FXML
    private Button AssestButton;

    private Investment investment;

    public void setInvestment(Investment investment) {
        this.investment = investment;
    }
    @FXML
    public void openBankChoice() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/isep/financialproject/ChooseBankAccount.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Choose Bank Account");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void openAAV() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddAsset-view.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Add Asset");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void openBPV() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChooseBankAccount.fxml"));
            Parent root = fxmlLoader.load();

            ChooseBankAccountController controller = fxmlLoader.getController();

            Stage stage = new Stage();
            stage.setTitle("Create bank Account");
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
