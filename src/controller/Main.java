package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private  Stage primaryStage;

    public void start(Stage primaryStage)  {
        this.primaryStage = primaryStage;
        mainWindow();
    }
    public void mainWindow() {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/MainWindowView.fxml"));

        try {
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            MainWindowController mainController = loader.getController();
            mainController.setMain(this);
            mainController.setStage(primaryStage);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}