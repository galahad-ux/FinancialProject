package org.isep.financialproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
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
    private PieChart bankPieChart;

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

        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
        for (Portfolio p: LoggedInUser.currentUser.getPortfolios()){
            if (p instanceof BankAccount account){
                bankTable.getItems().add(account);

                String pieName = account.getClass().getSimpleName() + " - " + account.getAccNum();
                pieData.add(new PieChart.Data(pieName, account.getBalance()));
            }
        }
        bankPieChart.setData(pieData);
        bankPieChart.setTitle("Accounts Balance");
    }
}
