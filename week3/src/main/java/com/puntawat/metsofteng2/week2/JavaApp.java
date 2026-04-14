package com.puntawat.metsofteng2.week2;

import com.puntawat.metsofteng2.week2.controller.HomeController;
import com.puntawat.metsofteng2.week2.jacoco.ExcludeFromJacocoGeneratedReport;
import com.puntawat.metsofteng2.week2.model.DbResourceBundle;
import com.puntawat.metsofteng2.week2.model.HibernateUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

import static java.util.Locale.US;

public class JavaApp extends Application {
    @ExcludeFromJacocoGeneratedReport
    @Override
    public void start(Stage stage) throws Exception {
        HibernateUtil.setEntityManagerFactory(HibernateUtil.buildEntityManagerFactory());
        ResourceBundle rb = new DbResourceBundle(Locale.forLanguageTag("en-GB"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"), rb);
        Scene scene = new Scene(loader.load());

        ((HomeController) loader.getController()).setRb(rb);

        stage.setTitle("Puntawat | Shopping Cart App");

        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            Platform.exit();
        });
    }
}
