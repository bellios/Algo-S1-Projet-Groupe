package com.example.algos1projetgroupe;

import game.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.web.WebView;

public class SoloController {
    private Game game;
    private ImageView[][] plateau;
    private ImageView[] hand;
    private int turn=10;

    @FXML
    private Slider slider;

    @FXML
    private VBox choseIAVBox;

    @FXML
    private Button OkButton;

    @FXML
    private WebView artificeTwo;

    @FXML
    private WebView artificeOne;

    @FXML
    private Label resultLabel;

    @FXML
    private VBox resultVBox;

    @FXML
    private VBox globalVBox;

    @FXML
    private VBox cardRowVBox;

    @FXML
    private ImageView cardChoseRow;

    @FXML
    private HBox handbox;

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
        artificeOne.getEngine().loadContent("<html><body><img src="+ getClass().getResource("/image/artifice.gif") +" style=\"width: auto; height: auto; object-fit: contain;\"></body></html>");
        artificeTwo.getEngine().loadContent("<html><body><img src="+ getClass().getResource("/image/artifice.gif") +" style=\"width: auto; height: auto; object-fit: contain;\"></body></html>");
        plateau = new ImageView[][]{{l1c1, l1c2, l1c3, l1c4, l1c5, l1c6}, {l2c1, l2c2, l2c3, l2c4, l2c5, l2c6}, {l3c1, l3c2, l3c3, l3c4, l3c5, l3c6}, {l4c1, l4c2, l4c3, l4c4, l4c5, l4c6}};
        hand = new ImageView[]{hand1, hand2, hand3, hand4, hand5, hand6, hand7, hand8, hand9, hand10};
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
    public void printHand(){
        int i = 0;
        for (Card card : game.getPlayers().get(0).getHand()) {
            String imagePath = card.getImagePath();
            hand[i].setImage(null);
            if (imagePath != null) {
                Image image = new Image(imagePath);
                hand[i].setImage(image);
            }
            i++;
        }
        if(i<10) handbox.getChildren().remove(i);
    }
    public void turn() {
        if(turn>0){
            turn--;
            //peux pas play après 5 card posé
            //si card play trop basse l'ia pose pas sa carte
            for (int i=1;i< game.getPlayers().size();i++) {
                game.getRessources().addCardToChosenCards(game.getPlayers().get(i).chooseCardInHand());
            }
            System.out.println("turn");
            everyonePlay(Game.PLATEAU_WIDTH);
            printHand();
            if(turn==0){
                globalVBox.setVisible(false);
                resultVBox.setVisible(true);
                resultLabel.setText(game.winning());

            }
        }
    }
    public void everyonePlay(int a){
        for (Member player : game.getPlayers()) {
            int minList=game.whoPlaysFirst();
            System.out.println("every : "+minList);
            game.getRessources().setPlayerTurn(minList,true);
            if(!game.checkValidity(minList)&&minList==0){
                System.out.println("pas possible");
                if(a<Game.PLATEAU_WIDTH) {
                    game.collectCards(0,a);
                    game.getRessources().setCardsChosen(minList, new Card(Game.NUMBER_CARDS+1,0, ""));
                    cardRowVBox.setVisible(false);
                    cardChoseRow.setImage(null);
                }else {
                    cardChoseRow.setImage(new Image(game.getRessources().getCardsChosenByPlayers().get(minList).getImagePath()));
                    cardRowVBox.setVisible(true);
                    return;
                }
            }else{
                System.out.println("easy");
                game.easyPlaceCard(minList);
            }
            printPlateau();
            game.getRessources().setPlayerTurn(minList,false);
        }
        game.getRessources().clearCardChosen();
    }

    @FXML
    public void onClick1(){
        if(!game.getPlayers().get(0).isTurn()) {
            game.getRessources().addCardToChosenCards(game.getPlayers().get(0).getHand().get(0));
            game.getPlayers().get(0).getHand().remove(0);
            turn();
        }
    }

    @FXML
    public void onClick2(){
        if(!game.getPlayers().get(0).isTurn()) {
            game.getRessources().addCardToChosenCards(game.getPlayers().get(0).getHand().get(1));
            game.getPlayers().get(0).getHand().remove(1);
            turn();
        }
    }

    @FXML
    public void onClick3(){
        if(!game.getPlayers().get(0).isTurn()) {
            game.getRessources().addCardToChosenCards(game.getPlayers().get(0).getHand().get(2));
            game.getPlayers().get(0).getHand().remove(2);
            turn();
        }
    }

    @FXML
    public void onClick4(){
        if(!game.getPlayers().get(0).isTurn()) {
            game.getRessources().addCardToChosenCards(game.getPlayers().get(0).getHand().get(3));
            game.getPlayers().get(0).getHand().remove(3);
            turn();
        }
    }

    @FXML
    public void onClick5(){
        if(!game.getPlayers().get(0).isTurn()) {
            game.getRessources().addCardToChosenCards(game.getPlayers().get(0).getHand().get(4));
            game.getPlayers().get(0).getHand().remove(4);
            turn();
        }
    }

    @FXML
    public void onClick6(){
        if(!game.getPlayers().get(0).isTurn()) {
            game.getRessources().addCardToChosenCards(game.getPlayers().get(0).getHand().get(5));
            game.getPlayers().get(0).getHand().remove(5);
            turn();
        }
    }

    @FXML
    public void onClick7(){
        if(!game.getPlayers().get(0).isTurn()) {
            game.getRessources().addCardToChosenCards(game.getPlayers().get(0).getHand().get(6));
            game.getPlayers().get(0).getHand().remove(6);
            turn();
        }
    }

    @FXML
    public void onClick8(){
        if(!game.getPlayers().get(0).isTurn()) {
            game.getRessources().addCardToChosenCards(game.getPlayers().get(0).getHand().get(7));
            game.getPlayers().get(0).getHand().remove(7);
            turn();
        }
    }

    @FXML
    public void onClick9(){
        if(!game.getPlayers().get(0).isTurn()) {
            game.getRessources().addCardToChosenCards(game.getPlayers().get(0).getHand().get(8));
            game.getPlayers().get(0).getHand().remove(8);
            turn();
        }
    }

    @FXML
    public void onClick10(){
        if(!game.getPlayers().get(0).isTurn()) {
            game.getRessources().addCardToChosenCards(game.getPlayers().get(0).getHand().get(9));
            game.getPlayers().get(0).getHand().remove(9);
            turn();
        }
    }

    @FXML
    public void onChoseL1(){
        if(game.getPlayers().get(0).isTurn())
            everyonePlay(0);
    }

    @FXML
    public void onChoseL2(){
        if(game.getPlayers().get(0).isTurn())
            everyonePlay(1);
    }

    @FXML
    public void onChoseL3(){
        if(game.getPlayers().get(0).isTurn())
            everyonePlay(2);
    }

    @FXML
    public void onChoseL4(){
        if(game.getPlayers().get(0).isTurn())
            everyonePlay(3);
    }

    @FXML
    private void onOkClick(){
        choseIAVBox.setVisible(false);
        ArrayList<Member> members=new ArrayList<>();
        members.add(new Player("Player " + 0));
        for(int i=0;i<slider.getValue();i++)
            members.add(new Ia("Ia " + (i+1)));
        game=new Game(true,members);
        printPlateau();
        printHand();
        globalVBox.setVisible(true);
    }
}