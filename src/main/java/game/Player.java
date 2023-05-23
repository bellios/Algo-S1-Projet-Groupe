package game;

import java.util.Scanner;

public class Player extends Member{

    public Player(String name) {
        super(name);
    }

    @Override
    public Card chooseCardInHand(){
        System.out.println("Choose a Card Number within your hands to play");
        Scanner scanner=new Scanner(System.in);
        int num=scanner.nextInt();
        Card CardChoosen = getHand().get(num);
        getHand().remove(num);
        return CardChoosen;
    }
}
