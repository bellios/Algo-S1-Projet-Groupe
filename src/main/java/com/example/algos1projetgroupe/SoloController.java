package com.example.algos1projetgroupe;

import game.*;
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
    private ImageView[][] plateau;

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
        game=new Game(true,new Player("Player " + 0),new Ia("IA " + 1));
        plateau = new ImageView[][]{{l1c1, l1c2, l1c3, l1c4, l1c5, l1c6}, {l2c1, l2c2, l2c3, l2c4, l2c5, l2c6}, {l3c1, l3c2, l3c3, l3c4, l3c5, l3c6}, {l4c1, l4c2, l4c3, l4c4, l4c5, l4c6}};
        printPlateau();


    }

    @FXML
    public void printPlateau() {
        for (int i = 0; i < Game.PLATEAU_WIDTH; i++) {
            for (int y = 0; y < Game.PLATEAU_LENGTH; y++) {
                if (game.getRessources().getPlateau()[i][y] != null) { //check si la case est remplie
                    Card card = game.getRessources().getPlateau()[i][y];
                    String imagePath = card.getImagePath();

                    if (imagePath != null) {
                        Image image = new Image(imagePath);
                        plateau[i][y].setImage(image);
                    } else {
                        plateau[i][y].setImage(null);
                    }
                } else {
                    plateau[i][y].setImage(null);
                }
                    /*plateau[i][y].setImage(new Image(game.getRessources().getPlateau()[i][y].getImagePath()));
                }else{
                    plateau[i][y].setImage(null);
                }*/
            }
        }
    }

    @FXML
    public void printHand(){
        for (Member a : game.getPlayers()) {
            for (int i = 0; i < Game.NUMBER_TURNS; i++) {
                int num = (int) (Math.random() * (game.getRessources().cardsSize() - 1));
                a.addCardToHand(game.getRessources().removeCardFromDeck(num));
            }
        }
    }
}