package controller;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;


public class AnimationWindowController {

    private Stage animationWindowStage;
    private int animationNo;

    @FXML
    private Rectangle rect;
    @FXML
    private Rectangle rect1;
    @FXML
    private Circle circle;
    @FXML
    private Ellipse fishBody;
    @FXML
    private QuadCurve fishTail;
    @FXML
    private Circle fishEye;

    Animation animation;
    

    public void setStage(Stage stage) {
        this.animationWindowStage = stage;
    }

    public void setAnimationNo(int animationNo) {
        this.animationNo = animationNo;

        if (animationNo==1){
            RotateTransition transition = new RotateTransition();
            transition.setNode(rect);
            transition.setDuration(Duration.seconds(10));
            transition.setFromAngle(0);
            transition.setToAngle(360);
            transition.setCycleCount(Animation.INDEFINITE); //animacja bedzie sie powtarzac w nieskonczonosc
            transition.setAutoReverse(true); //odwrocenie animacji
            animation=transition;
        }
        else if (animationNo==2){
            TranslateTransition transition = new TranslateTransition();
            transition.setNode(rect);
            transition.setDuration(Duration.seconds(5));
            transition.setFromX(0);
            transition.setFromY(0);
            transition.setByX(200);
            transition.setByY(100);
            transition.setCycleCount((Animation.INDEFINITE));
            transition.setAutoReverse(true);
            animation = transition;
        }
        else if(animationNo == 3){
            Path animationPath = new Path();
            MoveTo moveTo = new MoveTo(0, 50);
            CubicCurveTo sineCurve = new CubicCurveTo(200, -250, 200, 250, 400, 0);
            animationPath.getElements().addAll(moveTo, sineCurve);

            PathTransition pathTransition = new PathTransition();
            pathTransition.setNode(rect);
            pathTransition.setDuration(Duration.seconds(5));
            pathTransition.setPath(animationPath);
            pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition.setInterpolator(Interpolator.LINEAR); //linia nie ma efektu hamowania
            animation = pathTransition;
        }
        else if (animationNo == 4){
            TranslateTransition t1 = new TranslateTransition();
            t1.setDuration(Duration.seconds(2));
            t1.setByX(400);

            ScaleTransition s1 = new ScaleTransition();
            s1.setDuration(Duration.millis(300));
            s1.setFromX(0.95);
            s1.setFromY(1.05);
            s1.setToX(1.05);
            s1.setToY(0.95);

            ScaleTransition s2 = new ScaleTransition();
            s2.setDuration(Duration.millis(300));
            s2.setFromX(1.05);
            s2.setFromY(0.95);
            s2.setToX(0.95);
            s2.setToY(1.05);

            PauseTransition p1 = new PauseTransition();
            p1.setDuration(Duration.millis(1400));

            SequentialTransition seq1 = new SequentialTransition(s1, p1, s2);
            ParallelTransition par1 = new ParallelTransition(rect, t1, seq1); //wezel, transition i sekwencja
            par1.setCycleCount(Animation.INDEFINITE);
            par1.setAutoReverse(true);

            animation = par1;
        }
        else if (animationNo == 5){
            KeyFrame kf0 = new KeyFrame(Duration.millis(0), new KeyValue(rect.arcWidthProperty(), 0),
                    new KeyValue(rect.arcHeightProperty(), 0));

            KeyFrame kf1 = new KeyFrame(Duration.millis(600), new KeyValue(rect.arcWidthProperty(), 20),
                    new KeyValue(rect.arcHeightProperty(), 20));

            KeyFrame kf2 = new KeyFrame(Duration.millis(800), new KeyValue(rect.arcWidthProperty(), 60),
                    new KeyValue(rect.arcHeightProperty(), 60));

            Timeline tl = new Timeline(kf0, kf1, kf2);
            animation = tl;
        }
        else if (animationNo == 6){
            TranslateTransition t1 = new TranslateTransition();
            t1.setDuration(Duration.seconds(1));
            t1.setNode(rect);
            t1.setByX(50); //poruszanie sie po osiX

            TranslateTransition t2 = new TranslateTransition();
            t2.setDuration(Duration.seconds(1));
            t2.setNode(rect1);
            t2.setByX(50); //poruszanie sie po osiX

            PauseTransition p1 = new PauseTransition();
            p1.setDuration(Duration.millis(400));

            SequentialTransition s1 = new SequentialTransition(t1, p1, t2); //zbieranie oddzielnych animacji razem

            animation = s1;
        }
        else if (animationNo == 8){
            TranslateTransition t1 = new TranslateTransition();
            t1.setDuration(Duration.seconds(2));
            t1.setNode(fishBody);
            t1.setByX(400);

            TranslateTransition t2 = new TranslateTransition();
            t2.setDuration(Duration.seconds(2));
            t2.setNode(fishTail);
            t2.setByX(400);

            TranslateTransition t3 = new TranslateTransition();
            t3.setDuration(Duration.seconds(2));
            t3.setNode(fishEye);
            t3.setByX(400);

            ParallelTransition s1 = new ParallelTransition(t1, t2, t3);
            animation = s1;

        }

    }

    public void playAnimation(ActionEvent actionEvent) {
        animation.play();
    }

    public void pauseAnimation(ActionEvent actionEvent) {
        animation.pause();
    }

    public void stopAnimation(ActionEvent actionEvent) {
        animation.stop();
    }

    public void closeAnimation(ActionEvent actionEvent) {
        animationWindowStage.close();
    }

    public void paneMouseClicked(MouseEvent evt) {
        if (animationNo == 7){
            animateCircle(evt.getX(), evt.getY());
        }
    }

    public void animateCircle(double x, double y){

        circle.setVisible(true);
        circle.setLayoutX(x);
        circle.setLayoutY(y);

        KeyFrame kf0 = new KeyFrame(Duration.millis(0),
                new KeyValue(circle.radiusProperty(), 0));
        KeyFrame kf1 = new KeyFrame(Duration.millis(400),
                new KeyValue(circle.radiusProperty(), 30),
                new KeyValue(circle.visibleProperty(), false));

        Timeline tl = new Timeline(kf0, kf1);
        tl.play();

    }

  /*
    FadeTransition - znimowanie przezroczystosci
    FillTransition - kolor i wypelnianie
    StrokeTransition - kolor krawedzi
    PathTransition - ruch wdloz sciezki
    RotateTransition - obroty
    ScaleTransition - skalowanie
    TranslateTransition - przesuniecie

    ParallerTransition - składanie równoległe animacji
    SequentialTransition - składanie sekwencyjne

    Timeline, KeyFrame, KeyValue - animacja dowolnych wlasciwosci*/

}
