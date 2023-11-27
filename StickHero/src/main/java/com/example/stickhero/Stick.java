package com.example.stickhero;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;
import static javafx.scene.transform.Rotate.Z_AXIS;

public class Stick extends GameObjects{
    @FXML
    private Label myBonusLabel;
    private Hero hero;
    private float rotation = 0;
    private AnimationTimer timer;
    private double delta = 2   ; // Increment in height
    private Rectangle rectangle;
    private float height = 0;
    private boolean growing = true; // Flag to track if the stick is growing or shrinking
    private Platform platform;
    final private double MAX_HEIGHT = 250.0; // Maximum height, adjust as needed
    final private double MIN_HEIGHT = 0.0; // Minimum height

    final private float WIDTH= 10.0F;

    private Player player;

    private Background background;
    private Platform currentPlatform;

    public Stick(Rectangle rectangle, Hero hero, Platform platform, Background background, Player player, Platform currentPlatform) {
        this.rectangle = rectangle;
        this.hero = hero;
        this.platform = platform;
        this.currentPlatform = currentPlatform;
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                extendStick();
            }
        };
        this.setCoordinateY(this.rectangle.getLayoutY());
        this.setCoordinateX(this.rectangle.getLayoutX());
        this.background = background;
        this.player = player;
    }


    public void extendStick() { // REPLACE WITH GETTER SETTER
        if (growing) {
            if (height < MAX_HEIGHT) {
                height += delta;
            } else {
                growing = false; // Start shrinking
            }
            rectangle.setY(rectangle.getY() - delta); // Move up as the rectangle grows
        } else {
            if (height > MIN_HEIGHT) {
                height -= delta;
            } else {
                growing = true; // Start growing
            }
            rectangle.setY(rectangle.getY() + delta); // Move down as the rectangle shrinks
        }

        // Now set the height
        rectangle.setHeight(height);
        this.setHeight(height);

    }

    public void startExtending() {
        timer.start();
    }

    public void stopExtending() {
        timer.stop();
        rotateStick(hero);
        // Call rotateStick when the button is released
    }

    public float getHeight() {
        return (float) rectangle.getHeight();
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public float getWidth() {
        return (float) rectangle.getWidth();
    }


    public void rotateStick(Hero hero) {
        double bottomRightX = rectangle.getX() + this.getWidth();
        double bottomRightY = rectangle.getY() + rectangle.getHeight();

        Rotate rotate = new Rotate(0, bottomRightX, bottomRightY); // Initial angle is 0 degrees
        rectangle.getTransforms().add(rotate);

        Duration duration = Duration.seconds(0.5); // Total animation duration (2 seconds in this example)
        int totalFrames = 90; // Total number of frames for smooth animation

        // Create a Timeline for animation
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1); // Play the animation once

        // Add key frames to gradually increase the angle over the animation duration
        for (int i = 0; i <= totalFrames; i++) {
            Duration frameTime = Duration.millis(duration.toMillis() * i / totalFrames);
            double frameAngle = 90 * i / totalFrames; // Rotate by 90 degrees over the animation
            timeline.getKeyFrames().add(
                    new KeyFrame(frameTime,
                            event -> rotate.setAngle(frameAngle)
                    )
            );
        }


        // Add an event handler for when the animation completes
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Call hero.moveForward after the animation is complete
                if (checkPlatformFall() == true){
                    checkFallOnPlatform();
                    hero.moveForward(platform.getCoordinateX() - hero.getCoordinateX() + platform.getWidth() - hero.getImageView().getFitWidth()*0.5);
                } else {
                    hero.moveForward(rectangle.getHeight(), Stick.this, background);
                }



            }
        });

        // Play the animation
        timeline.play();
    }

    public void fallStick() {
        double bottomRightX = rectangle.getX() + this.getWidth();
        double bottomRightY = rectangle.getY() + rectangle.getHeight();

        Rotate rotate = new Rotate(0, bottomRightX, bottomRightY); // Initial angle is 0 degrees
        rectangle.getTransforms().add(rotate);

        Duration duration = Duration.seconds(0.5); // Total animation duration (2 seconds in this example)
        int totalFrames = 90; // Total number of frames for smooth animation

        // Create a Timeline for animation
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1); // Play the animation once

        // Add key frames to gradually increase the angle over the animation duration
        for (int i = 0; i <= totalFrames; i++) {
            Duration frameTime = Duration.millis(duration.toMillis() * i / totalFrames);
            double frameAngle = 90 * i / totalFrames; // Rotate by 90 degrees over the animation
            timeline.getKeyFrames().add(
                    new KeyFrame(frameTime,
                            event -> rotate.setAngle(frameAngle)
                    )
            );
        }


        // Add an event handler for when the animation completes

        // Play the animation
        timeline.play();
    }

    public boolean checkPlatformFall(){
        double distanceToCover = platform.getCoordinateX() - this.getCoordinateX();
        if (this.height < distanceToCover || this.height > distanceToCover + platform.getWidth()){ // checks fo fall
            return false;}
        return true;
    }

    public void moveOut(){
        double targetX = -1000;
        double distanceToMove = targetX - rectangle.getLayoutX();
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), rectangle);
        transition.setByX(distanceToMove); // Move by the calculated distance
        transition.play();
    }

    public void checkFallOnPlatform(){
        double distanceToCover = platform.getCoordinateX() - this.getCoordinateX();
        if (this.height < distanceToCover || this.height > distanceToCover + platform.getWidth()){ // checks fo fall
            fallStick();
            hero.fallDown();


        } else if ((this.height < distanceToCover + platform.getMiddle() + 4 ) && this.height > distanceToCover + platform.getMiddle() - 5){ //checks for bonus
            player.setCurrentScore(player.getCurrentScore() + 2);
            player.animateLabelBonus(player.getLabelBonus(), platform.getCoordinateX() + platform.getMiddle() - 10, 1);
            player.animateLabelBonus(player.getPerfectLabel(), -1, 1);
            player.getScoreLabel().setText(player.getCurrentScore()+"");



        } else {
            player.setCurrentScore(player.getCurrentScore() + 1); // levelup
            player.getScoreLabel().setText(player.getCurrentScore()+"");
        }
    }

}
