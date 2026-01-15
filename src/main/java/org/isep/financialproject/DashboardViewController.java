package org.isep.financialproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DashboardViewController {
    @FXML
    private Button APButton;
    @FXML
    private Button goToInvestment;

    @FXML
    private Button goToBank;

    public void openAPV() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPortfolio.fxml"));
        Parent root = fxmlLoader.load();

        AddPortofolioController controller = fxmlLoader.getController();
        controller.setInvestment(LoggedInUser.investment);

        Stage stage = new Stage();
        stage.setTitle("Add Portfolio");
        stage.setScene(new Scene(root));
        stage.show();


    }
    @FXML
    private void goToInvestmentPage() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Investment-view.fxml"));
        Parent root = loader.load();
        goToInvestment.getScene().setRoot(root);

    }

    @FXML
    private void goToBankPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Bank-view.fxml"));
        Parent root = loader.load();
        goToInvestment.getScene().setRoot(root);

    }


}




