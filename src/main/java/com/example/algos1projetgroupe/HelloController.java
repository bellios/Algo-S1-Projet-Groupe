package com.example.algos1projetgroupe;

import game.Game;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.util.Duration;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.net.URISyntaxException;

public class HelloController {
    int balise;

    @FXML
    private MediaView backMusic;

    @FXML
    private MediaView ds;

    @FXML
    private Button startButton;

    @FXML
    private ImageView logo;

    @FXML
    private StackPane stackPane;

    @FXML
    private Button playButton;

    @FXML
    private Button solo;

    @FXML
    private Button multi;

    @FXML
    private Button graph;

    @FXML
    private Button noGraph;

    @FXML
    private HBox choiceBox;

    @FXML
    private HBox graphBox;

    @FXML
    private void startOnClick(){
        startButton.setVisible(false);
        logo.setVisible(true);
        if(backMusic.getMediaPlayer() == null){
            try{
                String fileName = getClass().getResource("/video/music.mp3").toURI().toString();
                Media media = new Media(fileName);
                MediaPlayer player = new MediaPlayer(media);
                backMusic.setMediaPlayer(player);
                player.setCycleCount(MediaPlayer.INDEFINITE);
            } catch (URISyntaxException e){
                e.printStackTrace();
            }
        }
        backMusic.getMediaPlayer().play();
        Timeline timeline = new Timeline();
        KeyFrame keyFrame1 = new KeyFrame(Duration.ZERO, e -> {
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), logo);
            translateTransition.setFromY(-400);
            translateTransition.setToY(0);
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), logo);
            scaleTransition.setFromX(0);
            scaleTransition.setFromY(0);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            ParallelTransition parallelTransition = new ParallelTransition(translateTransition, scaleTransition);
            parallelTransition.setDelay(Duration.seconds(1));
            parallelTransition.play();
        });
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(3), e -> {
            playButton.setVisible(true);
        });
        timeline.getKeyFrames().addAll(keyFrame1, keyFrame2);
        timeline.play();
    }


    public void onPlay() {
        playButton.setVisible(false);
        choiceBox.setVisible(true);
    }

    public void onSolo() {
        choiceBox.setVisible(false);
        graphBox.setVisible(true);
        balise=1;
    }

    public void onMulti() {
        choiceBox.setVisible(false);
        graphBox.setVisible(true);
        balise=2;
    }

    public void onGraph(ActionEvent event) throws IOException {
        if (balise==1){
            Stage stage = (Stage) graph.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("solo.fxml"));
            Scene scene = new Scene(root);
            stage.setFullScreen(true);
            stage.setScene(scene);
        }else if (balise == 2){
            Stage stage = (Stage) graph.getScene().getWindow();
            stage.close();
        }
    }

    public void onNoGraph(ActionEvent event) throws IOException{
        if(balise == 1) {
            Stage stage = (Stage) graph.getScene().getWindow();
            stage.close();
            Game game = new Game();
        }else if (balise==2){
            Stage stage = (Stage) graph.getScene().getWindow();
            stage.close();
        }

    }
}