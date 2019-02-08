package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class MainWindowController {

    private Main main;
    private Stage primaryStage;


    public void setMain(Main main) {
        this.main = main;
    }

    public void setStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }
    @FXML
    public void closeEvent(){
        primaryStage.close();
    }

    @FXML public void imageViewClicked(MouseEvent evt){
        double x, y;
        int num;
        int col, row;

        x = evt.getX();
        y = evt.getY();

        col = (int)(x/150.0);
        row = (int)(y/150.0);

        num=3*row+col+1;

        System.out.println("Wybrana cyfra " + num);

        if(num >=1 && num <=8)
        animationWindow(num); //wywolanie funkcji animationWindow
    }

    public void animationWindow(int animationNo){
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/AnimationWindowView.fxml"));
        try {
            AnchorPane pane = loader.load();
            Stage animationWindowStage = new Stage();
            animationWindowStage.setTitle("Animacja");
            animationWindowStage.initModality(Modality.WINDOW_MODAL);//ustalanie wlasciciela nowego okna
            animationWindowStage.initOwner(primaryStage);
            Scene scene = new Scene(pane);

            AnimationWindowController animationWindowController = loader.getController();
            animationWindowController.setStage(animationWindowStage);
            animationWindowController.setAnimationNo(animationNo);
            animationWindowStage.setScene(scene);
            animationWindowStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}