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
    private Button AssetButton;

    private Investment investment;

    public void setInvestment(Investment investment) {
        this.investment = investment;
    }

    @FXML
    public void openAssetT() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddAssetTransaction.fxml"));
        Parent root = fxmlLoader.load();

        AddAssetTransactionController controller = fxmlLoader.getController();
        controller.setInvestment(investment);

        Stage stage = new Stage();
        stage.setTitle("Add Asset Transaction");
        stage.setScene(new Scene(root));
        stage.show();




    }


}
