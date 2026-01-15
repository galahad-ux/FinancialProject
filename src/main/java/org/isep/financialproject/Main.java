package org.isep.financialproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{

    @Override
    public void start(Stage stage) throws IOException {
        User.allUsers = User.loadAllUsers("users.csv");

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("buttonDesign.css").toExternalForm());
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main (String[] args) { launch(); }
}
