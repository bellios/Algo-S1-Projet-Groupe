package game;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Ressources {
    private Semaphore mutex = new Semaphore(1);
    private Card[][] plateau;
    private ArrayList<Card> cards = new ArrayList<>();

    //la sema s'applique pas sur le get et les élément qui en découle
    //Du coup faut trouver un moyen de stocker les ressources de game tout en pouvant utiliser ces méthodes


    public Ressources() {
        initializeCard();
        initCardPlateau();
    }
    //==========================================================================================================
    // init
    //==========================================================================================================
    private void initializeCard() {
        for (int i = 1; i <= Game.NUMBER_CARDS; i++) {
            int a = 0;
            if (i % 10 == 0) a += 3;//3
            else if (i % 5 == 0) a += 2;//2
            if (i % 11 == 0) a += 5;//5
            if (a == 0) a++;
            cards.add(new Card(i, a));
        }
    }
    private void initCardPlateau() {
        plateau = new Card[Game.PLATEAU_WIDTH][Game.PLATEAU_LENGTH];
        for (int i = 0; i < plateau.length; i++) {
            int num = (int) (Math.random() * (cards.size() - 1));
            plateau[i][0] = cards.get(num);
            cards.remove(num);
        }
    }
    //==========================================================================================================
    // getter
    //==========================================================================================================
    public int cardsSize(){
        return cards.size();
    }
    public Card[][] getPlateau(){
        return plateau;
    }
    //==========================================================================================================
    // setter
    //==========================================================================================================
    public Card removeCardFromDeck(int num){
        Card card = null;
        try {
            this.mutex.acquire();
            card=cards.get(num);
            cards.remove(num);
            this.mutex.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return card;

    }
    public void setPlateau(int x, int y, Card card){
        try {
            this.mutex.acquire();
            plateau[x][y]=card;
            this.mutex.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    /*private boolean[] state = {false,false};
    private Semaphore mutex = new Semaphore(1);

    public boolean getState(int id) {
        try {
            this.mutex.acquire();
            if(this.state[id]) {
                this.mutex.release();
                return true ;
            }
            this.mutex.release();
            return false ;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false ;
        }
    }

    public void setState(int id) {
        try {
            this.mutex.acquire();
            this.state[id] = true ;
            this.mutex.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/


}
