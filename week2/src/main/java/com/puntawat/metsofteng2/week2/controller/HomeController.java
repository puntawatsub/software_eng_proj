package com.puntawat.metsofteng2.week2.controller;

import com.puntawat.metsofteng2.week2.model.PurchaseItem;
import com.puntawat.metsofteng2.week2.model.SelectedLocale;
import com.puntawat.metsofteng2.week2.model.ShoppingCart;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jdk.jfr.EventType;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class HomeController {
    @FXML
    private TableColumn<PurchaseItem, Integer> quantityColumn;

    @FXML
    private TableColumn<PurchaseItem, String> itemColumn;

    @FXML
    private TableColumn<PurchaseItem, String> priceColumn;

    @FXML
    private Button addItemButton;

    @FXML
    private Text costLabel;

    @FXML
    private TableView<PurchaseItem> tableView;

    @FXML
    private ToggleGroup languageToggleGroup;

    @FXML
    private RadioMenuItem arabicToggle;

    @FXML
    private RadioMenuItem englishToggle;

    @FXML
    private RadioMenuItem finnishToggle;

    @FXML
    private RadioMenuItem japaneseToggle;

    @FXML
    private RadioMenuItem swedishToggle;

    private ResourceBundle rb;


    @FXML
    private void initialize() {

        if (SelectedLocale.getInstance().getSelected() == null) {
            englishToggle.setSelected(true);
        } else {
            RadioMenuItem radio = switch (SelectedLocale.getInstance().getSelected()) {
                case "sv_SE" -> swedishToggle;
                case "ar_AE" -> arabicToggle;
                case "fi_FI" -> finnishToggle;
                case "ja_JP" -> japaneseToggle;
                default -> englishToggle;
            };
            radio.setSelected(true);
        }


        // set related functions
        addItemButton.setOnAction(this::onAddItemPressed);
        ShoppingCart.getInstance().getPurchaseItems().addListener((ListChangeListener<PurchaseItem>) change -> {
            while (change.next()) {
                costLabel.setText(String.format("%.2f", ShoppingCart.getInstance().getTotalCost()));
            }
        });
        languageToggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle == null) {
                oldToggle.setSelected(true);
            }
        });




        quantityColumn.setCellValueFactory(data -> new ReadOnlyIntegerWrapper(data.getValue().getQuantity()).asObject());
        itemColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getName()));
        priceColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(String.format("%.2f", data.getValue().getPrice())));

        tableView.setItems(ShoppingCart.getInstance().getPurchaseItems());
        costLabel.setText(String.format("%.2f", ShoppingCart.getInstance().getTotalCost()));
    }

    public void setRb(ResourceBundle rb) {
        this.rb = rb;
        tableView.setPlaceholder(new Text(rb.getString("no_content")));
        System.out.println(rb.getLocale().getLanguage());
        if (rb.getLocale().getLanguage().equals("ar_ae")) {
            costLabel.getScene().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        }
    }

    private void onAddItemPressed(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddItem.fxml"), rb);
            Scene scene = new Scene(loader.load());

            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onLanguageSelected(ActionEvent event) throws IOException {
        String id = ((RadioMenuItem) languageToggleGroup.getSelectedToggle()).getId();
        Locale locale = Locale.of(id);
        SelectedLocale.getInstance().setSelected(id);
        Stage stage = (Stage) costLabel.getScene().getWindow();
        var rb =  ResourceBundle.getBundle("Bundles", locale);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"), rb);
        Scene scene = new Scene(loader.load());
        ((HomeController) loader.getController()).setRb(rb);
        stage.setScene(scene);
    }


}
