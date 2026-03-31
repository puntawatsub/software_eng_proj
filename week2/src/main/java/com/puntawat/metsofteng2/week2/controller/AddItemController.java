package com.puntawat.metsofteng2.week2.controller;

import com.puntawat.metsofteng2.week2.model.PurchaseItem;
import com.puntawat.metsofteng2.week2.model.ShoppingCart;
import com.puntawat.metsofteng2.week2.service.LanguageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class AddItemController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField quantityField;

    @FXML
    private Button saveButton;

    @FXML
    private void initialize() {
        saveButton.setOnAction(this::onSave);
    }

    private void onSave(ActionEvent event) {
        try {
            NumberFormat nf = NumberFormat.getNumberInstance(LanguageService.getInstance().getResourceBundle().getLocale());
            ShoppingCart.getInstance().addItem(new PurchaseItem(nameField.getText(), nf.parse(priceField.getText()).doubleValue(), Integer.parseInt(quantityField.getText())));
            ((Stage) saveButton.getScene().getWindow()).close();
        } catch (ParseException | NumberFormatException ignored) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Number format error");
            alert.setHeaderText("Number format error!");
            alert.setContentText("Check your number again. Enter it correctly according to your selected language format");
            alert.show();
        }
    }
}
