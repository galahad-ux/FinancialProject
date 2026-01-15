package org.isep.financialproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BankViewController {
    @FXML
    private TableView<BankAccount> bankTable;
    @FXML
    private TableColumn<BankAccount,String> typeCol;

    @FXML
    private TableColumn <BankAccount, Number> balanceCol;

    @FXML
    public void initialize() {
        typeCol.setCellValueFactory(cd ->
                new javafx.beans.property.SimpleStringProperty(cd.getValue().getClass().getSimpleName()));
        balanceCol.setCellValueFactory(cd ->
                new javafx.beans.property.SimpleDoubleProperty(cd.getValue().getBalance()));

        refresh();
    }

    public void refresh(){
        bankTable.getItems().clear();
        for (Portfolio p: LoggedInUser.currentUser.getPortfolios()){
            if (p instanceof BankAccount account){
                bankTable.getItems().add(account);
            }
        }
    }
}
