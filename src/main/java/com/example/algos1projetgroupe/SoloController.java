package com.example.algos1projetgroupe;

import game.*;
import javafx.event.ActionEvent;
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
    private ImageView[] hand;

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
        hand = new ImageView[]{hand1, hand2, hand3, hand4, hand5, hand6, hand7, hand8, hand9, hand10};
        printPlateau();
        printHand();
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
            }
        }
    }
    @FXML
    public void suprPlateau() {
        for (int i = 0; i < Game.PLATEAU_WIDTH; i++) {
            for (int y = 0; y < Game.PLATEAU_LENGTH; y++) {
                if (game.getRessources().getPlateau()[i][y] != null) { //check si la case est remplie;
                        plateau[i][y].setImage(null);
                } else {
                    plateau[i][y].setImage(null);
                }
            }
        }
    }
    @FXML
    public void printHand(){
        int i = 0;
        for (Card card : game.getPlayers().get(0).getHand()) {
            String imagePath = card.getImagePath();
            if (imagePath != null) {
                Image image = new Image(imagePath);
                hand[i].setImage(image);
            } else {
                hand[i].setImage(null);
            }
            i++;
        }
    }
    public void turn(){
        game.getRessources().addCardToChosenCards(game.getPlayers().get(1).chooseCardInHand());
        if (game.whoPlaysFirst()==1){
            game.placeCard(0,game.getPlayers().get(1).placeCardOnBoard());
            suprPlateau();
            printPlateau();
            printHand();
        }
    }

    @FXML
    public void onClick1(){
        game.getRessources().addCardToChosenCards(game.getPlayers().get(0).getHand().get(0));
        turn();
    }

    @FXML
    public void onClick2(){
        game.getRessources().addCardToChosenCards(game.getPlayers().get(0).getHand().get(1));
        turn();
    }

    @FXML
    public void onClick3(){
        game.getRessources().addCardToChosenCards(game.getPlayers().get(0).getHand().get(2));
        turn();
    }

    @FXML
    public void onClick4(){
        game.getRessources().addCardToChosenCards(game.getPlayers().get(0).getHand().get(3));
        turn();
    }

    @FXML
    public void onClick5(){
        game.getRessources().addCardToChosenCards(game.getPlayers().get(0).getHand().get(4));
        turn();
    }

    @FXML
    public void onClick6(){
        game.getRessources().addCardToChosenCards(game.getPlayers().get(0).getHand().get(5));
        turn();
    }

    @FXML
    public void onClick7(){
        game.getRessources().addCardToChosenCards(game.getPlayers().get(0).getHand().get(6));
        turn();
    }

    @FXML
    public void onClick8(){
        game.getRessources().addCardToChosenCards(game.getPlayers().get(0).getHand().get(7));
        turn();
    }

    @FXML
    public void onClick9(){
        game.getRessources().addCardToChosenCards(game.getPlayers().get(0).getHand().get(8));
        turn();
    }

    @FXML
    public void onClick10(){
        game.getRessources().addCardToChosenCards(game.getPlayers().get(0).getHand().get(9));
        turn();
    }

    @FXML
    public void onClickl1c1(){
        if (game.whoPlaysFirst()==0){
            game.placeCard(0,1);
            suprPlateau();
            printPlateau();
            printHand();
        }
    }

    @FXML
    public void onClickl2c1(){

    }

    @FXML
    public void onClickl3c1(){

    }

    @FXML
    public void onClickl4c1(){

    }

}