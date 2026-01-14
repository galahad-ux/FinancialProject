package org.isep.financialproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsViewController {

    @FXML
    private TextField fullName;

    @FXML
    private TextField email;

    @FXML
    private MenuButton changeCurrency;
    private Currency selectedCurrency;

    @FXML
    private TextField changedPassword;

    @FXML
    private Button saveChanges;

    @FXML
    public void initialize(){
        changeCurrency.getItems().clear();
        for (Currency c: Currency.values()){
            MenuItem option = new MenuItem(c.name());

            option.setOnAction(actionEvent -> {
                changeCurrency.setText(c.name());
                this.selectedCurrency = c;
            });

            changeCurrency.getItems().add(option);
        }
    }

    @FXML
    public void showError(String msg) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginError.fxml"));
        Parent root = fxmlLoader.load();

        LoginErrorController controller = fxmlLoader.getController();
        controller.fill(msg);

        Stage stage = new Stage();
        stage.setTitle("Error");
        stage.setScene(new Scene(root));

        stage.show();
    }

    @FXML
    public void saveChange(){
        if (selectedCurrency == null || fullName.getText().isEmpty() || email.getText().isEmpty() || changedPassword.getText().isEmpty()){
            try{
                showError("Fill all fields");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        User.newUserDetails(LoggedInUser.currentUserEmail, fullName.getText(),email.getText(),changedPassword.getText(),selectedCurrency.name() );
        LoggedInUser.currentUserEmail = email.getText();

        StoreNotifications.add("Details changed in User Setting");

        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Successful");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Success! Changes saved:)");
        successAlert.show();
    }
}
