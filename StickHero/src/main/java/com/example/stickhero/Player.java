package com.example.stickhero;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.io.Serializable;

public class Player implements Serializable {
    private Hero hero;
    private int currentScore = 0;
    private int highScore;
    private int background;
    private Label bonusLabel;
    private Label scoreLabel;
    private Label messageLabel;
    private Label perfectLabel;
    public Label getLabelBonus(){
     return this.bonusLabel;
    }

    public Label getScoreLabel(){
        return this.scoreLabel;
    }

    public Label getPerfectLabel(){
        return this.perfectLabel;
    }

    public Label getMessageLabel(){
        return this.messageLabel;
    }
    public Player(Hero hero, Label bonusLabel, Label scoreLabel, Label messageLabel, Label perfectLabel){
        this.hero = hero;
        this.bonusLabel = bonusLabel;
        this.scoreLabel = scoreLabel;
        this.messageLabel = messageLabel;
        this.perfectLabel = perfectLabel;
    }

    public void animateLabelBonus(Label label, double xPosition, double durationInSeconds) {
        // Set the initial opacity to 0
        label.setOpacity(0);

        // Save the original Y position
        double originalYPosition = label.getLayoutY();

        if (xPosition != -1){
            label.setLayoutX(xPosition);
        }


        // Create the fade in transition
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(durationInSeconds / 2), label);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        // Create the fade out transition
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(durationInSeconds / 2), label);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);

        // Create translate transition for moving up
        TranslateTransition moveUp = new TranslateTransition(Duration.seconds(durationInSeconds / 2), label);
        moveUp.setByY(-20);

        // Create translate transition for moving down
        TranslateTransition moveDown = new TranslateTransition(Duration.seconds(durationInSeconds / 2), label);
        moveDown.setByY(20);

        // Create a sequential transition to play fade in and move up in sequence
        SequentialTransition fadeInSequence = new SequentialTransition(fadeIn, moveUp);

        // Create a sequential transition to play move down and fade out in sequence
        SequentialTransition fadeOutSequence = new SequentialTransition(moveDown, fadeOut);

        // Combine both sequences
        SequentialTransition combinedSequence = new SequentialTransition(fadeInSequence, fadeOutSequence);

        // Start the animation
        combinedSequence.play();

        // Reset position after animation ends
        combinedSequence.setOnFinished(event -> label.setLayoutY(originalYPosition));
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public void playGame(){}
    public void resumeGame(){}
    public void restartGame(){}
    public void reviveAndPlay(){}
    public void viewTerms(){}

    public void howToPlay(){}
    public void muteGame(){}
    public void changeCharacter(){}

}
