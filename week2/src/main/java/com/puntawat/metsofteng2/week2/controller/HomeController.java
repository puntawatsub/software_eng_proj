package com.puntawat.metsofteng2.week2.controller;

import com.puntawat.metsofteng2.week2.model.PurchaseItem;
import com.puntawat.metsofteng2.week2.model.SelectedLocale;
import com.puntawat.metsofteng2.week2.model.ShoppingCart;
import com.puntawat.metsofteng2.week2.service.LanguageService;
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

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;
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
    private ToggleGroup languageToggleGroup;

    @FXML
    private RadioMenuItem englishToggle;

    private NumberFormat nf;

    private PurchaseItem selected;


    @FXML
    private void initialize() {

        if (SelectedLocale.getInstance().getSelected() == null) {
            languageToggleGroup.selectToggle(englishToggle);
        } else {
            Optional<Toggle> toggleOptional = languageToggleGroup.getToggles().stream().filter(toggle -> toggle instanceof RadioMenuItem).filter(toggle -> ((RadioMenuItem) toggle).getId().equals(SelectedLocale.getInstance().getSelected())).findFirst();
            if (toggleOptional.isPresent()) {
                languageToggleGroup.selectToggle(toggleOptional.get());
            } else {
                languageToggleGroup.selectToggle(englishToggle);
            }
        }


        // set related functions
        addItemButton.setOnAction(this::onAddItemPressed);
        ShoppingCart.getInstance().getPurchaseItems().addListener((ListChangeListener<PurchaseItem>) change -> {
            while (change.next()) {
                updateCostLabel();
            }
        });
        languageToggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle == null) {
                oldToggle.setSelected(true);
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




        quantityColumn.setCellValueFactory(data -> new ReadOnlyIntegerWrapper(data.getValue().getQuantity()).asObject());
        itemColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getName()));
        priceColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(nf.format(data.getValue().getPrice())));
        totalColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(nf.format(data.getValue().getTotalCost())));
        tableView.setItems(ShoppingCart.getInstance().getPurchaseItems());
    }

    public void setRb(ResourceBundle rb) {
        LanguageService.getInstance().setResourceBundle(rb);
        nf = NumberFormat.getCurrencyInstance(rb.getLocale());
        updateCostLabel();
//        updateFont(rb);
        tableView.setPlaceholder(new Text(rb.getString("no_content")));
        if (LanguageService.getInstance().isRTL()) {
            costLabel.getScene().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        }
    }

    private void updateCostLabel() {
        costLabel.setText(nf.format(ShoppingCart.getInstance().getTotalCost()));
    }

    private void onAddItemPressed(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddItem.fxml"), LanguageService.getInstance().getResourceBundle());
            Scene scene = new Scene(loader.load());
            if (LanguageService.getInstance().isRTL()) {
                scene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            }
            String font = switch (LanguageService.getInstance().getResourceBundle().getLocale().getLanguage()) {
                case "ja" -> "/css/japanese.css";
                case "ar" -> "/css/arabic.css";
                default -> "/css/latin.css";
            };
//            scene.getStylesheets().add(getClass().getResource(font).toExternalForm());
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
        Locale locale = Locale.forLanguageTag(id);
        SelectedLocale.getInstance().setSelected(id);
        Stage stage = (Stage) costLabel.getScene().getWindow();
        var rb =  ResourceBundle.getBundle("Bundles", locale);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"), rb);
        Scene scene = new Scene(loader.load());
        ((HomeController) loader.getController()).setRb(rb);
        stage.setScene(scene);
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
