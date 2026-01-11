package org.isep.financialproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class TransferViewController {

    @FXML
    private TextField beneficiaryNameField;

    @FXML
    private TextField accountNoField;

    @FXML
    private TextField amountField;

    @FXML
    private Label dateLabel;

    @FXML
    private TextField purposeField;

    @FXML
    public void initialize() {
        dateLabel.setText(LocalDate.now().toString());
    }


}
