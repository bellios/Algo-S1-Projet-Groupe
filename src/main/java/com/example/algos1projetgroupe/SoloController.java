package com.example.algos1projetgroupe;

import game.Game;
import javafx.scene.image.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SoloController {
    private Game game;

    private List<Image> cards = new ArrayList<>();
    private List<Integer> onScreen = new ArrayList<>();
    private List<Integer> handAI = new ArrayList<>();
    private List<Integer> rowOne = new ArrayList<>();
    private List<Integer> rowTwo = new ArrayList<>();
    private List<Integer> rowThree = new ArrayList<>();
    private List<Integer> rowFour = new ArrayList<>();


    @FXML
    private ImageView hand1;

    @FXML
    private ImageView hand2;

    @FXML
    private ImageView hand3;

    @FXML
    private ImageView hand4;

    @FXML
    private ImageView hand5;

    @FXML
    private ImageView hand6;

    @FXML
    private ImageView hand7;

    @FXML
    private ImageView hand8;

    @FXML
    private ImageView hand9;

    @FXML
    private ImageView hand10;

    @FXML
    private ImageView l1c1;

    @FXML
    private ImageView l1c2;

    @FXML
    private ImageView l1c3;

    @FXML
    private ImageView l1c4;

    @FXML
    private ImageView l1c5;

    @FXML
    private ImageView l1c6;

    @FXML
    private ImageView l2c1;

    @FXML
    private ImageView l2c2;

    @FXML
    private ImageView l2c3;

    @FXML
    private ImageView l2c4;

    @FXML
    private ImageView l2c5;

    @FXML
    private ImageView l2c6;

    @FXML
    private ImageView l3c1;

    @FXML
    private ImageView l3c2;

    @FXML
    private ImageView l3c3;

    @FXML
    private ImageView l3c4;

    @FXML
    private ImageView l3c5;

    @FXML
    private ImageView l3c6;

    @FXML
    private ImageView l4c1;

    @FXML
    private ImageView l4c2;

    @FXML
    private ImageView l4c3;

    @FXML
    private ImageView l4c4;

    @FXML
    private ImageView l4c5;

    @FXML
    private ImageView l4c6;

    @FXML
    public void initialize() {
        Game.setGraph(true);
        game = new Game();

    }



}
