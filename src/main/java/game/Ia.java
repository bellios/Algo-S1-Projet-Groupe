package game;

import java.util.Random;

public class Ia extends Member{
    public Ia(String name) {
        super(name);
    }

    @Override
    public Card chooseCardInHand(){     //Random method to select the card chosen by IA
        Random random = new Random();
        int num=random.nextInt(getHand().size());
        Card CardChoosen = getHand().get(num);
        getHand().remove(num);
        return CardChoosen;
    }

    @Override
    public void placeCardOnBoard() {

    }
}
