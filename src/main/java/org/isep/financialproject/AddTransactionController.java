package org.isep.financialproject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class AddTransactionController {
    @FXML
    Button AssestButton;

    public void openAssetT() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddAssetTransaction.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Add Asset Transaction");
        stage.setScene(new Scene(root));
        stage.show();


    }


}
