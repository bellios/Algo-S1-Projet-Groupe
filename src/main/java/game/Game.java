package game;

import java.util.ArrayList;

public class Game {
    private ArrayList<Card> cards=new ArrayList<>();
    private void initializeCard() {
        for (int i = 1; i <= 104; i++) {
            int a=1;
            if(i%10==0)a+=2;//3
            else if(i%5==0)a++;//2
            if(i%11==0)a+=4;//5
            cards.add(new Card(i, a));
        }
    }
    public Game() {
        initializeCard();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
