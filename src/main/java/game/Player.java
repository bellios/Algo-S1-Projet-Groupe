package game;

import java.io.IOException;
import java.util.Scanner;

public class Player extends Member{

    public Player(String name) {
        super(name);
    }
    @Override
    public Card chooseCardInHand(){
        outputStream.println("Choose a Card Number within your hands to play");
        outputStream.flush();
        int num=0;
        try {
            num = Integer.parseInt(inputStream.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Card CardChosen = getHand().get(num);
        getHand().remove(num);
        return CardChosen;
    }

    @Override
    public int placeCardOnBoard() {
        int chosenRow=10;
        do {
            outputStream.println("Choose a row to put your card in");
            outputStream.flush();
            try {
                chosenRow = Integer.parseInt(inputStream.readLine())-1;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (chosenRow > Game.PLATEAU_LENGTH||chosenRow<0);
        return chosenRow;
    }

    @Override
    public int collectCards_Row() {
        int collectCardsRow=10;
        do {
            outputStream.println("Choose a row to collect the cards from");
            outputStream.flush();
            try {
                collectCardsRow = Integer.parseInt(inputStream.readLine())-1;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (collectCardsRow > Game.PLATEAU_LENGTH||collectCardsRow<0);
        return collectCardsRow;
    }
}