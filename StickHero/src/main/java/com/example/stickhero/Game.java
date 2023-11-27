package com.example.stickhero;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Game extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Game.class.getResource("Playing.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getRoot().setFocusTraversable(true);
        String css = Objects.requireNonNull(this.getClass().getResource("stickHero.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Stick Hero Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}