package com.example.stickhero;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Background {
    private ImageView mybackground;
    public Background(ImageView imageView){
        this.mybackground = imageView;
    }
    private int ImageNumber;
    private float coordinateX;
    private float coordinateY;

    public int getImageNumber() {
        return ImageNumber;
    }

    public void setImageNumber(int imageNumber) {
        ImageNumber = imageNumber;
    }

    public float getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(float coordinateX) {
        this.coordinateX = coordinateX;
    }

    public float getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(float coordinateY) {
        this.coordinateY = coordinateY;
    }

    public void generateImage(){}


    public void moveForward(double shiftAmount) {
        // Assuming imageView is the background image view
        double speed = 1;

        // Calculate the new position
        double newX = mybackground.getTranslateX() - shiftAmount; // Move left

        // Check if the background has moved its complete width
        if (newX <= - mybackground.getImage().getWidth()) {
            newX = 0; // Reset to start position
        }
        // Create a TranslateTransition for smooth movement
        TranslateTransition transition = new TranslateTransition(Duration.seconds(speed), mybackground);
        transition.setToX(newX);

        // Play the transition
        transition.play();
    }
}
