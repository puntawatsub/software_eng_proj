package com.puntawat.metsofteng2.week2.controller;

import com.puntawat.metsofteng2.week2.model.DbResourceBundle;
import com.puntawat.metsofteng2.week2.model.PurchaseItem;
import com.puntawat.metsofteng2.week2.model.SelectedLanguage;
import com.puntawat.metsofteng2.week2.model.ShoppingCart;
import com.puntawat.metsofteng2.week2.service.Language;
import com.puntawat.metsofteng2.week2.service.ResourceBundleService;
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

import java.io.IOException;
import java.text.NumberFormat;
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
    private TableColumn<PurchaseItem, String> totalColumn;

    @FXML
    private Button addItemButton;

    @FXML
    private Text costLabel;

    @FXML
    private TableView<PurchaseItem> tableView;

    @FXML
    private ComboBox<Language> languageCombo;

    private NumberFormat nf;

    private PurchaseItem selected;


    @FXML
    private void initialize() {

        languageCombo.getItems().addAll(Language.en_GB, Language.fi_FI, Language.sv_FI, Language.ja_JP, Language.ar_AE);

        if (SelectedLanguage.getInstance().getSelected() == null) {
            languageCombo.getSelectionModel().select(Language.en_GB);
        } else {
            languageCombo.getSelectionModel().select(SelectedLanguage.getInstance().getSelected());
        }


        // set related functions
        addItemButton.setOnAction(this::onAddItemPressed);
        ShoppingCart.getInstance().getPurchaseItems().addListener((ListChangeListener<PurchaseItem>) change -> {
            while (change.next()) {
                updateCostLabel();
            }
        });
        tableView.getSelectionModel().selectedItemProperty().addListener(((obv, oldSelect, newSelect) -> {
            selected = newSelect;
        }));
        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction(event -> {
            ShoppingCart.getInstance().removeItem(selected);
        });
        ContextMenu contextMenu = new ContextMenu(deleteItem);
        tableView.setOnContextMenuRequested(contextMenuEvent -> {
            contextMenu.show(costLabel.getScene().getWindow(), contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY());
        });
        languageCombo.setOnAction(event -> {
            onLanguageSelected(languageCombo.getValue());
        });




        quantityColumn.setCellValueFactory(data -> new ReadOnlyIntegerWrapper(data.getValue().getQuantity()).asObject());
        itemColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getName()));
        priceColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(formatCurrency(data.getValue().getPrice())));
        totalColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(formatCurrency(data.getValue().getTotalCost())));
        tableView.setItems(ShoppingCart.getInstance().getPurchaseItems());
    }

    public void setRb(ResourceBundle rb) {
        ResourceBundleService.getInstance().setResourceBundle(rb);
        nf = NumberFormat.getInstance(rb.getLocale());
        updateCostLabel();
//        updateFont(rb);
        tableView.setPlaceholder(new Text(rb.getString("no_content")));
        if (ResourceBundleService.getInstance().isRTL()) {
            costLabel.getScene().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        }
    }

    private void updateCostLabel() {
        costLabel.setText(formatCurrency(ShoppingCart.getInstance().getTotalCost()));
    }

    private String formatCurrency(double num) {
//        String filtered = nf.format(num).replaceAll("[^0-9,\\.\\s]", "");
        String filtered = nf.format(num);
//        int firstDot = filtered.indexOf('.');
//        if (firstDot != -1) {
//            filtered = filtered.substring(0, firstDot + 1) +
//                    filtered.substring(firstDot + 1).replace(".", "");
//        }
        return filtered;
    }

    private void onAddItemPressed(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddItem.fxml"), ResourceBundleService.getInstance().getResourceBundle());
            Scene scene = new Scene(loader.load());
            if (ResourceBundleService.getInstance().isRTL()) {
                scene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            }
//            scene.getStylesheets().add(getClass().getResource(font).toExternalForm());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    void onLanguageSelected(Language language) {
        // TODO: Edit here
        Locale locale = Locale.forLanguageTag(language.getLang());
        SelectedLanguage.getInstance().setSelected(language);
        Stage stage = (Stage) costLabel.getScene().getWindow();
        var rb =  new DbResourceBundle(locale);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"), rb);
        try {
            Scene scene = new Scene(loader.load());
            ((HomeController) loader.getController()).setRb(rb);
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    private void updateFont(ResourceBundle rb) {
//        String font = switch (rb.getLocale().getLanguage()) {
//            case "ja" -> "/css/japanese.css";
//            case "ar" -> "/css/arabic.css";
//            default -> "/css/latin.css";
//        };
//        costLabel.getScene().getStylesheets().add(getClass().getResource(font).toExternalForm());
//    }


}
