package game;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Member {
    private String name;
    private ArrayList<Card> hand;
    private ArrayList<Card> stack;
    ArrayList<Member> players=new ArrayList<>();

    public Member(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.stack = new ArrayList<>();
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
}
