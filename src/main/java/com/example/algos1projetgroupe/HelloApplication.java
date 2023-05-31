package com.example.algos1projetgroupe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        StackPane root = fxmlLoader.load();
        Scene scene = new Scene(root, 1500, 800);
        stage.setTitle("Six qui prend");
        stage.setScene(scene);
        stage.show();
        String videoFile = getClass().getResource("/video/titlescreen.mp4").toExternalForm();
        Media media = new Media(videoFile);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        Stage videoStage = new Stage();
        StackPane videoRoot = new StackPane();
        videoRoot.getChildren().add(mediaView);
        Scene videoScene = new Scene(videoRoot);
        videoStage.setFullScreen(true);
        videoStage.setScene(videoScene);
        videoStage.setFullScreenExitHint("");
        videoStage.show();
        stage.hide();
        mediaPlayer.setOnEndOfMedia(() -> {
            stage.show();
            videoStage.close();
        });
        mediaPlayer.play();
    }


    public static void main(String[] args) {
        launch();
    }
}