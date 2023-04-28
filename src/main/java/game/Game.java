package game;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Card> cards=new ArrayList<>();
    private ArrayList<Member> players=new ArrayList<>();
    private boolean multi;
    private Card[][] plateau= new Card[4][6];
    private final int turn=10;
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
    private void initializePlayers(){
        final int num=9;
        players.add(new Player("Player 0"));
        Scanner scanner=new Scanner(System.in);
        int index;
        do {
            System.out.println("1 : Play solo\n2 : Play in multiplayer");
            index = scanner.nextInt();
        } while (index !=1&&index!=2);
        this.multi= index==2?true:false;
        do {
            System.out.println("Enter the number of player you want between 1 and "+num);
            index = scanner.nextInt();
        } while (index <1||index>num);
        if(this.multi) {
            for (int i = 1; i < index + 1; i++) {
                do {
                    System.out.println("Player " + i + " : 1 for IA, 2 for Player");
                    index = scanner.nextInt();
                } while (index != 1 && index != 2);
                if (index == 1)
                    players.add(new Ia("IA " + i));
                else if (index == 2)
                    players.add(new Player("Player " + i));
            }
        }else{

            for (int i = 1; i < index + 1; i++)
                players.add(new Ia("IA " + i));
        }
    }
    private void initCardPlateau(){
        for(int i=0;i<plateau.length;i++){
            int num = (int) (Math.random() * (cards.size() - 1));
            plateau[i][0]= cards.get(num);
            cards.remove(num);
        }
    }
    private void initCardInHands(){
        for (Member a:players) {
            for(int i=0;i<turn;i++) {
                int num = (int) (Math.random() * (cards.size() - 1));
                a.addCardToHand(cards.get(num));
                cards.remove(num);
            }
        }
    }
    //==========================================================================================================
    // Display
    //==========================================================================================================

    public void displayPlateau(){
        System.out.println("Number(Point)");
        for(int i=0;i<plateau.length;i++){
            for(int y=0;y<plateau[i].length;y++){
                String a= (plateau[i][y]==null)? ".\t":plateau[i][y].getNum()+"("+plateau[i][y].getPoint()+")\t";
                System.out.print(a);
            }
            System.out.println("\n");
        }
    }
    public void displayHand(int num){
        for (Card card:players.get(num).getHand()) {
            
        }
    }
    //==========================================================================================================
    // Game
    //==========================================================================================================
    public Game() {
        initializeCard();
        initializePlayers();
        initCardPlateau();
        initCardInHands();
        displayPlateau();
        if(this.multi){
            //play multi
        }else {
            //play solo
        }

    }
}
