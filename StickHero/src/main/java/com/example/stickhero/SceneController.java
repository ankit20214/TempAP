package com.example.stickhero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public Button growButton;
    @FXML
    private ImageView heroImageView;
    @FXML
    private ImageView myBackground;
    @FXML
    private Rectangle myStick;
    @FXML
    private Rectangle myPlatform;
    @FXML
    private Label myBonusLabel;
    @FXML
    private Rectangle myPlatformCurrent;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private Label perfectLabel;
    @FXML
    private Button flipButton;

    public Hero hero;
    private Stick stick;
    private Platform platform;

    private Background background;

    private Player player;

    private Platform currentPlatform;


    public void initialize() {
        GameObjects gameObjectsContainer = new GameObjects();
        flipButton.setOnAction(event -> hero.toggleFlip());
        hero = new Hero(heroImageView, flipButton);
        platform = new Platform(myPlatform);
        currentPlatform = new Platform(myPlatformCurrent);
        background = new Background(myBackground);
        player = new Player(hero, myBonusLabel, scoreLabel, messageLabel, perfectLabel);
        stick = new Stick(myStick, hero, platform, background, player, currentPlatform);
        growButton.setOnMousePressed(e -> stick.startExtending());
        growButton.setOnMouseReleased(e -> stick.stopExtending());
    }

    public void switchToPlaying(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Playing.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToStart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Opening Screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void switchTogameOver(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Game Over.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}

