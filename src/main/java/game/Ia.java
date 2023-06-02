package game;

import java.util.Random;
import java.util.Scanner;

public class Ia extends Member{
    public Ia(String name) {
        super(name);
    }

    @Override
    public Card chooseCardInHand(){     //Random method to select the card chosen by IA
        Random random = new Random();
        int num=random.nextInt(getHand().size());
        Card CardChosen = getHand().get(num);
        getHand().remove(num);
        return CardChosen;
    }

    @Override
    public int placeCardOnBoard() {
        Random random = new Random();
        return random.nextInt(Game.PLATEAU_WIDTH);
    }

    @Override
    public int collectCards_Row() {
        Random random = new Random();
        return random.nextInt(Game.PLATEAU_WIDTH);
    }


}