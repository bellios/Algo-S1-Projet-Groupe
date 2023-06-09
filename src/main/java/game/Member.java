package game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public abstract class Member {
    private String name;
    private ArrayList<Card> hand;
    private ArrayList<Card> stack;
    private boolean turn;
    PrintWriter outputStream;
    BufferedReader inputStream;

    public Member(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.stack = new ArrayList<>();
        outputStream = new PrintWriter(new OutputStreamWriter(System.out));
        inputStream = new BufferedReader(new InputStreamReader(System.in));
        turn=false;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setInputStream(BufferedReader inputStream) {
        this.inputStream = inputStream;
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

    public void displayHand(){ // num of player
        int i=0;
        for (Card card:getHand()) {
            outputStream.println(i+" : "+card.toString());
            outputStream.flush();
            i++;
        }
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}