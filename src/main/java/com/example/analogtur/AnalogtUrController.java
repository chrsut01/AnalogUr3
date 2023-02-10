package com.example.analogtur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.util.Duration;

import java.time.LocalTime;
import java.util.Objects;

public class AnalogtUrController{
    @FXML
    private Pane pane;

    @FXML
    private TextField alarmHour;

    @FXML
    private TextField alarmMinute;

    @FXML
    private TextField alarmSecond;


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
            Double[] points5 = {515.0,298.0, 515.0,302.0, 545.0,302.0, 545.0,298.0 };
            for (int count5 = 0; count5 < 60; ++count5) {
                     // create a new Second Tick and copy existing points into it
                 Polygon fiveMinTick = new Polygon();
                 fiveMinTick.getPoints().addAll(points5);
                 fiveMinTick.getTransforms().add(Transform.rotate(count5 * 30, 300, 300));
                 pane.getChildren().add(fiveMinTick);
            }


        // Creates Second Hand
        Line secondHand = new Line();
        secondHand.setStartX(300);
        secondHand.setStartY(300);
        secondHand.setEndX(300);
        secondHand.setEndY(78);
        pane.getChildren().add(secondHand);

        // Positions second hand at an angle based on current time
        Rotate secHandAngle = new Rotate();
        secHandAngle.setAngle(LocalTime.now().getSecond() * 6);
        secHandAngle.setPivotX(300);
        secHandAngle.setPivotY(300);
        secondHand.getTransforms().addAll(secHandAngle);

        // Defines speed and pivot of second hand rotation
        Rotate rotateSH = new Rotate();
        rotateSH.setAngle(rotateSH.getAngle()+ .06);
        rotateSH.setPivotX(300);
        rotateSH.setPivotY(300);

        //Rotates second hand smoothly and with accurate time
        Timeline timelineAnimationS = new Timeline(
                new KeyFrame(Duration.millis(10),
                        e -> {
                            secondHand.getTransforms().addAll(rotateSH);
                        }
                )
        );

        // Creates Minute Hand
        Line minuteHand = new Line();
        minuteHand.setStartX(300);
        minuteHand.setStartY(300);
        minuteHand.setEndX(300);
        minuteHand.setEndY(110);
        minuteHand.setStrokeWidth(10);
        pane.getChildren().add(minuteHand);

        // Positions hand at an angle based on current time
        Rotate minHandAngle = new Rotate();
        minHandAngle.setAngle(LocalTime.now().getMinute() * 6);
        minHandAngle.setPivotX(300);
        minHandAngle.setPivotY(300);
        minuteHand.getTransforms().addAll(minHandAngle);

        // Defines speed and pivot of minute hand rotation
        Rotate rotateMH = new Rotate();
        rotateMH.setAngle(rotateMH.getAngle()+.01);
        rotateMH.setPivotX(300);
        rotateMH.setPivotY(300);
        System.out.println("time is: " + LocalTime.now().getHour()+":"+ LocalTime.now().getMinute()+":"+ LocalTime.now().getSecond());

        // Sets minute hand in motion
        Timeline timelineAnimationM = new Timeline(
                new KeyFrame(Duration.millis(100),
                        e -> {
                            minuteHand.getTransforms().addAll(rotateMH);
                        }
                )
        );

        // Creates Hour Hand
        Line hourHand = new Line();
        hourHand.setStartX(300);
        hourHand.setStartY(300);
        hourHand.setEndX(300);
        hourHand.setEndY(180);
        hourHand.setStrokeWidth(10);
        pane.getChildren().add(hourHand);

        // Positions hour hand at an angle based on current time
        Rotate hourHandAngle = new Rotate();
        hourHandAngle.setAngle(LocalTime.now().getHour() * 30);
        hourHandAngle.setPivotX(300);
        hourHandAngle.setPivotY(300);
        hourHand.getTransforms().addAll(hourHandAngle);

        Rotate rotateHH = new Rotate();
        rotateHH.setAngle(rotateHH.getAngle()+.05);
        rotateHH.setPivotX(300);
        rotateHH.setPivotY(300);

        //Rotate Minute Hand
        Timeline timelineAnimationH = new Timeline(
                new KeyFrame(Duration.seconds(6),
                        e -> {
                            hourHand.getTransforms().addAll(rotateHH);
                        }
                )
        );

        // Prints out time every 10 seconds
        Timeline printTime = new Timeline(
                new KeyFrame(Duration.seconds(10),
                        e -> {
                            System.out.println("time is: " + LocalTime.now().getHour()+":"+ LocalTime.now().getMinute()+":"+ LocalTime.now().getSecond());
                             }
                )
        );

        // Checks alarm setting with current time once every second and calls alarm (both audio file and print-out)
        Timeline soundAlarm = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        e -> {
                            //System.out.println("time is: " + LocalTime.now().getHour()+":"+ LocalTime.now().getMinute()+":"+ LocalTime.now().getSecond());
                            //System.out.println(alarmHour.getText() + ":" + alarmMinute.getText() + ":" + alarmSecond.getText());
                            if (Objects.equals(alarmHour.getText(), String.valueOf(LocalTime.now().getHour())) &&
                                    Objects.equals(alarmMinute.getText(), String.valueOf(LocalTime.now().getMinute())) &&
                                    Objects.equals(alarmSecond.getText(), String.valueOf(LocalTime.now().getSecond())))
                            {
                                play();
                                System.out.println("Alarm!!!");
                            }

                        }
                )
        );

        // Sets off alarm
        soundAlarm.setCycleCount(Timeline.INDEFINITE);
        soundAlarm.play();
        // Sets off printing of time
        printTime.setCycleCount(Timeline.INDEFINITE);
        printTime.play();
        // Sets off movement of second-, minute-, and hour-hands
        timelineAnimationS.setCycleCount(Timeline.INDEFINITE);
        timelineAnimationS.play();
        timelineAnimationM.setCycleCount(Timeline.INDEFINITE);
        timelineAnimationM.play();
        timelineAnimationH.setCycleCount(Timeline.INDEFINITE);
        timelineAnimationH.play();
        }


    // Plays alarm audio file
    Media media;
    MediaPlayer mediaPlayer;
    @FXML
    void play() {
        System.out.println("It should be sounding now");
        media = new Media(String.valueOf(getClass().getResource("beep-alarm.mp3")));
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.seek(mediaPlayer.getStartTime());
        mediaPlayer.play();
        }

   // Turns off alarm and clears alarm TextArea fields
    @FXML
    void stopAlarm(ActionEvent event) {
        alarmSecond.clear();
        alarmMinute.clear();
        alarmHour.clear();
        mediaPlayer.stop();
    }
}
