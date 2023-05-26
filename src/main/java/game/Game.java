package game;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Game {
    public static final int PLATEAU_SIZE=6;
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Member> players = new ArrayList<>();
    private boolean multi;
    private Card[][] plateau;
    private final int turn = 10;

    public ArrayList<Card> getCards() {
        return cards;
    }

    private ArrayList<Card> cardsChosenByPlayers = new ArrayList<>();

    public ArrayList<Card> getCardsChosenByPlayers(){return cardsChosenByPlayers;}

    public boolean isMulti() {
        return multi;
    }

    public ArrayList<Member> getPlayers() {
        return players;
    }

    public Card[][] getPlateau() {
        return plateau;
    }

    public int getTurn() {
        return turn;
    }

    //==========================================================================================================
    // Initialization
    //==========================================================================================================
    private void initializeCard() {
        for (int i = 1; i <= 104; i++) {
            int a = 0;
            if (i % 10 == 0) a += 3;//3
            else if (i % 5 == 0) a += 2;//2
            if (i % 11 == 0) a += 5;//5
            if (a == 0) a++;
            cards.add(new Card(i, a));
        }
    }

    private void initializePlayers() {
        final int num = 9;
        players.add(new Player("Player 0"));
        Scanner scanner = new Scanner(System.in);
        int index, indexPlayer, indexIA, yesNo;
        do {
            System.out.println("1 : Play solo\n2 : Play in multiplayer");
            index = scanner.nextInt();
        } while (index != 1 && index != 2);
        this.multi = index == 2 ? true : false;
        if(this.multi) {
            do {
                System.out.println("Enter the number of player you want between 1 and " + num);
                indexPlayer = scanner.nextInt();
                System.out.println("Do you want to add IA too ?\n1 : Yes\n2 : No");
                yesNo = scanner.nextInt();
                if (yesNo == 1) {
                    System.out.println("Enter the number of IA you want");
                    indexIA = scanner.nextInt();
                } else {indexIA = 0;}
                index = indexPlayer + indexIA;
            } while (index <1||index>num || indexPlayer > 9 || indexIA > 9-indexPlayer);
            for (int i = 2; i < indexPlayer + 2; i++) {
                players.add(new Player("Player " + i));
            }
            for (int i = 1; i < indexIA + 1; i++) {
                players.add(new Ia("IA " + i));
            }
        } else {
            do {
                System.out.println("Enter the number of IA you want");
                indexIA = scanner.nextInt();
            } while (indexIA <1||indexIA>num);
            for (int i = 1; i < indexIA + 1; i++)
                players.add(new Ia("IA " + i));
        }
    }

    private void initCardPlateau() {
        plateau = new Card[4][PLATEAU_SIZE];
        for (int i = 0; i < plateau.length; i++) {
            int num = (int) (Math.random() * (cards.size() - 1));
            plateau[i][0] = cards.get(num);
            cards.remove(num);
        }
    }

    private void initCardInHands() {
        for (Member a : players) {
            for (int i = 0; i < turn; i++) {
                int num = (int) (Math.random() * (cards.size() - 1));
                a.addCardToHand(cards.get(num));
                cards.remove(num);
            }
        }
    }
    //==========================================================================================================
    // Display
    //==========================================================================================================

    public void displayPlateau() {
        System.out.println("Number(Point)");
        for (int i = 0; i < plateau.length; i++) {
            System.out.print("[" + (i + 1) + "]" + " : ");
            for (int y = 0; y < plateau[i].length; y++) {
                String a = (plateau[i][y] == null) ? ".\t" : plateau[i][y].toString() + "\t";
                System.out.print(a);
            }
            System.out.println("\n");
        }
    }

    public void displayHand(int num){ // num of player
        int i=0;
        for (Card card:players.get(num).getHand()) {
            System.out.println(i+" : "+card.toString());
            i++;
        }
    }

    //==========================================================================================================
    // Game's mechanics
    //==========================================================================================================
    public void chooseCard() {
        for (Member player : players) {
            cardsChosenByPlayers.add(player.chooseCardInHand());
        }
    }

    //Créer une ArrayList avec juste les numéros de carte triés par ordre choisi par les joueurs (pas par ordre croisant hein) !
    public int whoPlaysFirst() {
        ArrayList<Card> index = cardsChosenByPlayers;
        ArrayList<Integer> sortedList = new ArrayList<>();  //Créer une liste SortedList vide
        for (Card NumCarte : index) {  //On parcourt tous les éléments de la liste Index et à chaque itération de boucle, la variable NumCarte prend la valeur de l'élément actuel de la liste Index
            sortedList.add(NumCarte.getNum());  //Ajoute la valeur du numéro de la carte à la liste SortedList
        }
        int minList = sortedList.indexOf(Collections.min(sortedList));
        return minList;
    }

    //Appelle la fonction qui permet  aux joueurs de poser leur carte au préalable sélectionnée sur le plateau
    public void playerPlaceCard() {
        int minList = whoPlaysFirst();
        boolean condition=true;
        do {
            int chosenRow=players.get(minList).placeCardOnBoard();
            System.out.println("row"+chosenRow);;
            for (int y = 0; y < plateau[chosenRow].length&&condition; y++) {  //On parcourt les colonnes du platea
                condition=placeCard(chosenRow,y,minList);
            }
            displayPlateau();
        } while (condition);
        cardsChosenByPlayers.set(minList, new Card(105,1));//On change la carte jouée sur le plateau par la carte 105
    }
    public boolean placeCard(int i, int y, int minList){
        if (plateau[i][y] == null) { //On vérifie que la case est vide
            System.out.println("ite" + y);
            if (plateau[i][y - 1].getNum() < cardsChosenByPlayers.get(minList).getNum()) {  //On vérifie que le placement du joueur est valide
                System.out.println("card placed");
                plateau[i][y] = cardsChosenByPlayers.get(minList);  //On ajoute la carte à l'emplacement choisi par le joueur
                displayPlateau();
                return false;
            } else {  //Si le placement du joueur est invalide
                System.out.println("You can't place your card here !");//On affiche un message d'erreur
                return true;
            }
        }
        return false;
    }
    public void turns(){
        for(int i=0;i<10;i++){
            displayPlateau();
            displayHand(0);
            chooseCard();
            displayPlateau();
            for (int y=0; y<players.size();y++) {
                playerPlaceCard();
            }
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
        turns();
        if (!this.multi) {

        }
    }
}
