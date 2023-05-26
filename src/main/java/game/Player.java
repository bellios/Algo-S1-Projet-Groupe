package game;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void placeCardOnBoard(int minList) {
       ArrayList<Card> cardsChosenByPlayers = Game.getCardsChosenByPlayers();
       boolean condition = true;
        do {
            int chosenRow;
            do {
                Game.displayPlateau();
                System.out.println("Choose a row to put your card in");
                Scanner scanner = new Scanner(System.in);
                chosenRow = scanner.nextInt();
            } while (chosenRow <= Game.getPlateau().length);
            for (int y = 0; y < plateau[chosenRow].length; y++) {  //On parcourt les colonnes du platea
                if (plateau[chosenRow][y] == null) { //On vérifie que la case est vide
                    if (plateau[chosenRow][y - 1].getNum() < cardsChosenByPlayers.get(minList).getNum()) {  //On vérifie que le placement du joueur est valide
                        plateau[chosenRow][y] = cardsChosenByPlayers.get(minList);  //On ajoute la carte à l'emplacement choisi par le joueur
                    } else {  //Si le placement du joueur est invalide
                        System.out.println("You can't place your card here !");//On affiche un message d'erreur
                        condition = false;
                    }
                } else System.out.println("Bug found : the row is full, good luck !");
            }
        } while (condition);
        cardsChosenByPlayers.set(minList, new Card(105,1)); //On change la carte jouée sur le plateau par la carte 105
    }
}
