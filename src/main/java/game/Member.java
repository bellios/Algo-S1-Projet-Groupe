package game;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public abstract class Member {
    private String name;
    private ArrayList<Card> hand;
    private ArrayList<Card> stack;
    PrintWriter outputStream;

    public Member(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.stack = new ArrayList<>();
        outputStream = new PrintWriter(new OutputStreamWriter(System.out));
    }
    public void setOutputStream(PrintWriter outputStream) {
        this.outputStream = outputStream;
    }
    public void addCardToHand(Card card){
        hand.add(card);
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public abstract Card chooseCardInHand();

    public String getName() {
        return name;
    }

    public abstract int placeCardOnBoard();

    public abstract int collectCards_Row();

    public ArrayList<Card> getStack() {
        return stack;
    }

    public int getTotalStack() {
        int stacks = 0;
        for (Card cards : stack) {
            stacks += cards.getPoint();
        }
        return stacks;
    }
    public String displayHand(){ // num of player
        int i=0;
        String a = "Your cards\n";
        for (Card card: getHand()) {
            a+=(i+" : "+card.toString());
            i++;
        }
        return a;
    }
}