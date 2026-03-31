package com.puntawat.metsofteng2.week2.controller;

import com.puntawat.metsofteng2.week2.model.PurchaseItem;
import com.puntawat.metsofteng2.week2.model.ShoppingCart;
import com.puntawat.metsofteng2.week2.service.ResourceBundleService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.text.ParseException;

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
            NumberFormat nf = NumberFormat.getNumberInstance(ResourceBundleService.getInstance().getResourceBundle().getLocale());
            ShoppingCart.getInstance().addItem(new PurchaseItem(nameField.getText(), nf.parse(priceField.getText()).doubleValue(), Integer.parseInt(quantityField.getText())));
            ((Stage) saveButton.getScene().getWindow()).close();
        } catch (ParseException | NumberFormatException ignored) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(ResourceBundleService.getInstance().getResourceBundle().getString("number.format.error.title"));
            alert.setHeaderText(ResourceBundleService.getInstance().getResourceBundle().getString("number.format.error.header"));
            alert.setContentText(ResourceBundleService.getInstance().getResourceBundle().getString("number.format.error.content"));
            alert.getDialogPane().setNodeOrientation(
                    ResourceBundleService.getInstance().isRTL() ?
                            NodeOrientation.RIGHT_TO_LEFT:
                            NodeOrientation.LEFT_TO_RIGHT
            );
            alert.show();
        }
    }
}
