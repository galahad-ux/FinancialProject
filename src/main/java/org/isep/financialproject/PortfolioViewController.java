package org.isep.financialproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class PortfolioViewController {
    @FXML
    private Label welcomeMsg;

    @FXML
    private Label date;

    @FXML
    private User currentUserName;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private ListView<String> notificationsList;

    @FXML
    private VBox notifications;

    @FXML
    private Label notifCount;

    public void setCurrentUserName(User userFullName) {
        this.currentUserName = userFullName;
        welcomeMsg.setText("Welcome " + userFullName.getUserFullName());
    }

    @FXML
    public void initialize() {
        date.setText(LocalDate.now().toString());
        showDashboard();        //default view
        notifCount();
    }

    //logout button
    @FXML
    public void BackToLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login-view.fxml"));
        Parent root = fxmlLoader.load();

        LoginViewController controller = fxmlLoader.getController();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("buttonDesign.css").toExternalForm());
        Stage stage = (Stage) welcomeMsg.getScene().getWindow();
        stage.setTitle("Login");
        stage.setScene(scene);

    }

    @FXML
    public void showSettings() {
        loadView("Settings-view.fxml");
    }

    @FXML
    public void showTransfer() {
        loadView("Transfer-view.fxml");
    }

    @FXML
    public void showTransactions() {
        loadView("Transaction-view.fxml");
    }

    @FXML
    public void showAccount() {
        loadView("Bank-view.fxml");
    }

    @FXML
    public void showInvestments() {
        loadView("Investment-view.fxml");
    }

    @FXML
    public void showDashboard() {
        loadView("Dashboard-view.fxml");
    }

    @FXML
    public void showAnalytics() {
        loadView("Analytics-view.fxml");
    }

    //switching view in main window
    private void loadView(String fxmlFile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = fxmlLoader.load();

            contentPane.getChildren().clear();
            contentPane.getChildren().add(root);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Notifications panel
    @FXML
    private void showNotifications() {
        if (!notifications.isVisible()) {
            notificationsList.getItems().clear();
            notificationsList.getItems().addAll(StoreNotifications.notifs);
        }
        notifications.setVisible(!notifications.isVisible());
        notifCount();
    }

    private void addNotification(String message) {
        notificationsList.getItems().add(0, message);
    }

    private void notifCount() {
        int count = StoreNotifications.notifs.size();
        if (count > 0) {
            notifCount.setText("[" + String.valueOf(count) + "]");
            notifCount.setVisible(true);
        } else {
            notifCount.setVisible(false);
        }
    }
}
