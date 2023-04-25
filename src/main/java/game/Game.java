package game;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Card> cards=new ArrayList<>();
    private Member players;
    public ArrayList<Card> getCards() {
        return cards;
    }
    //==========================================================================================================
    // Initialization
    //==========================================================================================================
    private void initializeCard() {
        for (int i = 1; i <= 104; i++) {
            int a=0;
            if(i%10==0)a+=3;//3
            else if(i%5==0)a+=2;//2
            if(i%11==0)a+=5;//5
            if(a==0)a++;
            cards.add(new Card(i, a));
        }
    }
    private void initilizePlayers(){
        Scanner scanner=new Scanner(System.in);
        int index;
        do {
            System.out.println("Enter the number of player you want between 2 and 8");
            index = scanner.nextInt();
        } while (index <2||index>8);
        for(int i=0;i<index;i++){

        }
    }

    //==========================================================================================================
    // Game
    //==========================================================================================================
    public Game() {
        initializeCard();
    }


}
