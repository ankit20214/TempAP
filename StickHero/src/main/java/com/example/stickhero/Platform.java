package com.example.stickhero;

import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Platform extends GameObjects{
    private double width;
    private double height;
    private double middle;
    private Rectangle rectangle;


    public Platform(Rectangle rectangle){
        this.rectangle = rectangle;
        this.middle = rectangle.getWidth() / 2;
        this.width = rectangle.getWidth();
        this.setCoordinateX(rectangle.getLayoutX());
        this.setCoordinateY(rectangle.getLayoutY());
    }
    
    public double getWidth() {
        return this.rectangle.getWidth();
    }


    public void update() {
        double targetX = 42;
        double distanceToMove = targetX - rectangle.getLayoutX();
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), rectangle);
        transition.setByX(distanceToMove); // Move by the calculated distance
        transition.play();
    }

    public void moveOut(){
        double targetX = -1000;
        double distanceToMove = targetX - rectangle.getLayoutX();
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), rectangle);
        transition.setByX(distanceToMove); // Move by the calculated distance
        transition.play();
    }


    public void setWidth(float width) {
        this.rectangle.setWidth(width);
        this.width = rectangle.getWidth();
    }

    public double getHeight() {
        return this.rectangle.getHeight();
    }

    public void setHeight(float height) {
        this.rectangle.setHeight(height);
        this.height = this.rectangle.getHeight();
    }

    public double getMiddle() {
        return middle;
    }
    

    public void spawnPlatform(){}
    public void move(){}
}
