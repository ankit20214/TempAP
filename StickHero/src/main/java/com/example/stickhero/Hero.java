package com.example.stickhero;

import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Hero extends GameObjects {

    private ImageView imageView;
    private Button flipButton;

    private boolean isFlipped = false;

    public Hero(ImageView imageView, Button flipButton) {
        this.imageView = imageView;
        this.setCoordinateX((int) imageView.getLayoutX());
        this.setCoordinateY((int) imageView.getLayoutY());
        this.flipButton = flipButton;
    }
    private int physicalLook;
    private int playerCherries;
    private String state;
    private boolean isAlive;

    public int getPhysicalLook() {
        return physicalLook;
    }

    public void setPhysicalLook(int physicalLook) {
        this.physicalLook = physicalLook;
    }

    public int getPlayerCherries() {
        return playerCherries;
    }

    public void setPlayerCherries(int playerCherries) {
        this.playerCherries = playerCherries;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void playGame(){}
    public void changeLook(){}
    public void revive(){}

    public ImageView getImageView(){
        return  imageView;
    }

    public void update() {
        double targetX = 42;
        // Calculate the distance to move
        double distanceToMove = targetX - imageView.getLayoutX();
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), imageView);
        transition.setByX(distanceToMove); // Move by the calculated distance
        transition.play();
    }

    //@Override
    public void moveForward(double Location){ // moves till a specific location.
        // Calculate the target X position
        Image newImage = new Image("C:\\Users\\Adya A\\Downloads\\StickHeroForAdya\\StickHero\\src\\main\\resources\\com\\example\\stickhero\\myHeroMoving.gif");
        // Update the imageView with the new image
        imageView.setImage(newImage);
        double targetX = Location - imageView.getFitWidth() * 0.5;

        // Create a TranslateTransition
        TranslateTransition transition = new TranslateTransition(Duration.seconds(Location * 0.005), imageView);

        // Set the target X coordinate for the transition
        transition.setToX(targetX);


        // Set the onFinished event handler for the transition
        transition.setOnFinished(event -> {
            // Load the new image to be displayed after the transition
            Image resetImage = new Image("C:\\Users\\Adya A\\Downloads\\StickHeroForAdya\\StickHero\\src\\main\\resources\\com\\example\\stickhero\\myHero.png");
            imageView.setImage(resetImage);

            // Update the hero's X coordinate
            this.setCoordinateX(targetX);





        });

        // Play the transition animation
        transition.play();
    }

    public void moveForward(double Speed, Stick stick, Background background) { // moves till the end of the stick
        // Calculate the target X position
        Image newImage = new Image("C:\\Users\\Adya A\\Downloads\\StickHeroForAdya\\StickHero\\src\\main\\resources\\com\\example\\stickhero\\myHeroMoving.gif");
        // Update the imageView with the new image
        imageView.setImage(newImage);
        double targetX = imageView.getLayoutX() + Speed + imageView.getFitWidth() * 0.5;

        // Create a TranslateTransition
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), imageView);

        // Set the target X coordinate for the transition
        transition.setToX(targetX);
        background.moveForward(15);

        // Set the onFinished event handler for the transition
        transition.setOnFinished(event -> {
            // Load the new image to be displayed after the transition
            Image resetImage = new Image("C:\\Users\\Adya A\\Downloads\\StickHeroForAdya\\StickHero\\src\\main\\resources\\com\\example\\stickhero\\myHero.png");
            imageView.setImage(resetImage);

            // Update the hero's X coordinate
            this.setCoordinateX(targetX);

            // Call checkFallOnPlatform after the animation completes
            stick.checkFallOnPlatform();
        });

        // Play the transition animation
        transition.play();
    }

    //@Override
    public void fallDown() {
        double screenHeight = 662; // The height of your game screen, set appropriately
        double fallDurationSeconds = 1.0; // Duration for the hero to fall, adjust as needed

        // Calculate the Y-coordinate at the bottom of the screen, accounting for the hero's height
        double translateY = screenHeight - imageView.getY() - imageView.getFitHeight();

        // Create a TranslateTransition for the imageView of the hero
        TranslateTransition fallTransition = new TranslateTransition(Duration.seconds(fallDurationSeconds), imageView);

        // Set the amount to translate in the Y direction
        fallTransition.setByY(translateY);

        // Optional: Add any additional settings or event handlers if needed
        // For example, you might want to trigger some action after the fall is complete
        // fallTransition.setOnFinished(event -> { /* actions to perform after falling */ });

        // Play the animation
        fallTransition.play();
    }

    //@Override
    public void waitForInput() {

    }

    //@Override
    public void flipDown() {
        // Duration for the flipping animation
        imageView.setScaleY(-1); // Negative scale for flipping
        // Move the hero downwards
        imageView.setTranslateY(imageView.getTranslateY() + imageView.getFitHeight());
    }

    //@Override
    public void flipUp() {
        imageView.setScaleY(1); // Reset scale to normal
        // Move the hero back to original position
        imageView.setTranslateY(imageView.getTranslateY() - imageView.getFitHeight());
    }

    public void toggleFlip() {
        if (isFlipped) {
            flipUp();
        } else {
            flipDown();
        }
        isFlipped = !isFlipped; // Toggle the state
    }
}
