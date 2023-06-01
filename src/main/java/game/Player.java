package game;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Player extends Member{

    public Player(String name) {
        super(name);
    }
    @Override
    public Card chooseCardInHand(){
        System.out.println("Choose a Card Number within your hands to play");
        outputStream.println("Choose a Card Number within your hands to play");
        outputStream.flush();
        Scanner scanner=new Scanner(System.in);
        int num=scanner.nextInt();
        Card CardChosen = getHand().get(num);
        getHand().remove(num);
        return CardChosen;
    }

    @Override
    public int placeCardOnBoard() {
        int chosenRow=10;
        do {
            System.out.println("Choose a row to put your card in");
            outputStream.println("Choose a row to put your card in");
            outputStream.flush();
            Scanner scanner = new Scanner(System.in);
            chosenRow = scanner.nextInt()-1;
        } while (chosenRow > Game.PLATEAU_LENGTH||chosenRow<0);
        return chosenRow;
    }

    @Override
    public int collectCards_Row() {
        int collectCardsRow=10;
        do {
            System.out.println("Choose a row to collect the cards from");
            outputStream.println("Choose a row to collect the cards from");
            outputStream.flush();
            Scanner scanner = new Scanner(System.in);
            collectCardsRow = scanner.nextInt()-1;
        } while (collectCardsRow > Game.PLATEAU_LENGTH||collectCardsRow<0);
        return collectCardsRow;
    }
}