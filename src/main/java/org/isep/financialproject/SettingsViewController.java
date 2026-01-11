package org.isep.financialproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;

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
}
