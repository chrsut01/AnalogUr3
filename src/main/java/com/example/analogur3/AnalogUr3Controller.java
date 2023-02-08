package com.example.analogur3;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.util.Duration;

import java.time.LocalTime;

public class AnalogUr3Controller {
    @FXML
    private Pane pane;

    public void initialize() {



      //  Draw Outer Circle
        Circle circle = new Circle(250);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        circle.setCenterX(300);
        circle.setCenterY(300);
        circle.setStrokeWidth(.8);
        pane.getChildren().add(circle);

        // Draw Inner Circle
        Circle circle2 = new Circle(9);
        circle2.setFill(Color.BLACK);
        circle2.setStroke(Color.BLACK);
        circle2.setCenterX(300);
        circle2.setCenterY(300);
        circle2.setStrokeWidth(1);
        pane.getChildren().add(circle2);

            // Draw Second Line Ticks
      Double[] points = {542.0,299.0, 542.0,301.0, 545.0,301.0, 545.0,299.0 };
             for (int count = 0; count < 60; ++count) {
                 // create a new Second Tick and copy existing points into it
                 Polygon secondTick = new Polygon();
                 secondTick.getPoints().addAll(points);
                 secondTick.getTransforms().add(Transform.rotate(count * 6, 300, 300));
                 pane.getChildren().add(secondTick);

             }
                 // Draw Five-minute Line Ticks
                 Double[] points5 = {525.0,298.0, 525.0,302.0, 545.0,302.0, 545.0,298.0 };
                 for (int count5 = 0; count5 < 60; ++count5) {
                     // create a new Second Tick and copy existing points into it
                     Polygon fiveMinTick = new Polygon();
                     fiveMinTick.getPoints().addAll(points5);
                     fiveMinTick.getTransforms().add(Transform.rotate(count5 * 30, 300, 300));
                     pane.getChildren().add(fiveMinTick);
                 }


        // Draw Second Hand
        Line secondHand = new Line();
        secondHand.setStartX(300);
        secondHand.setStartY(300);
        secondHand.setEndX(300);
        secondHand.setEndY(78);
        pane.getChildren().add(secondHand);

        // Attempt to start second hand at a position based on current time
        Rotate startSH = new Rotate();
        startSH.setAngle(LocalTime.now().getSecond() * 6);

        Rotate rotateSH = new Rotate();
        rotateSH.setAngle(rotateSH.getAngle()+.06);
        rotateSH.setPivotX(300);
        rotateSH.setPivotY(300);


        //Rotate Second Hand
        Timeline timelineAnimationS = new Timeline(
                new KeyFrame(Duration.millis(10),
                        e -> {
                            secondHand.getTransforms().addAll(rotateSH);
                            //startSecondHand.setAngle(startSecondHand.getAngle()+6);
                        }
                )
        );

        // Draw Minute Hand
        Line minuteHand = new Line();
        minuteHand.setStartX(300);
        minuteHand.setStartY(300);
        minuteHand.setEndX(300);
        minuteHand.setEndY(110);
        minuteHand.setStrokeWidth(10);
        pane.getChildren().add(minuteHand);

        // Attempt to start minute hand at a position based on current time
        Rotate startMH = new Rotate();
        startMH.setAngle(LocalTime.now().getMinute() * 6);

        Rotate rotateMH = new Rotate();
        rotateMH.setAngle(rotateMH.getAngle()+.01);
        rotateMH.setPivotX(300);
        rotateMH.setPivotY(300);

        //Rotate Minute Hand
        Timeline timelineAnimationM = new Timeline(
                new KeyFrame(Duration.millis(100),
                        e -> {
                            minuteHand.getTransforms().addAll(rotateMH);
                            //startSecondHand.setAngle(startSecondHand.getAngle()+6);
                        }
                )
        );
        // Draw Hour Hand
        Line hourHand = new Line();
        hourHand.setStartX(300);
        hourHand.setStartY(300);
        hourHand.setEndX(300);
        hourHand.setEndY(180);
        hourHand.setStrokeWidth(10);
        pane.getChildren().add(hourHand);

        // Attempt to start hour hand at a position based on current time
        Rotate startHH = new Rotate();
        startHH.setAngle(LocalTime.now().getHour() * 30);

        Rotate rotateHH = new Rotate();
        rotateHH.setAngle(rotateHH.getAngle()+.05);
        rotateHH.setPivotX(300);
        rotateHH.setPivotY(300);

        //Rotate Minute Hand
        Timeline timelineAnimationH = new Timeline(
                new KeyFrame(Duration.seconds(6),
                        e -> {
                            hourHand.getTransforms().addAll(rotateHH);
                            //startSecondHand.setAngle(startSecondHand.getAngle()+6);
                        }
                )
        );



        timelineAnimationS.setCycleCount(Timeline.INDEFINITE);
        timelineAnimationS.play();
        timelineAnimationM.setCycleCount(Timeline.INDEFINITE);
        timelineAnimationM.play();
        timelineAnimationH.setCycleCount(Timeline.INDEFINITE);
        timelineAnimationH.play();
    }
}