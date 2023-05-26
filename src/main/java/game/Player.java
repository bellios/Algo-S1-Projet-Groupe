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
       /** Card[][] plateau = Game.getPlateau();
        int chosenRow;
        do {
            Game.displayPlateau();
            System.out.println("Choose a row to put your card in");
            Scanner scanner = new Scanner(System.in);
            chosenRow = scanner.nextInt();
        } while (chosenRow <= plateau.length);
        for (int y = 0; y < plateau[chosenRow].length; y++) {  //On parcourt les colonnes du platea
            if (plateau[chosenRow][y] == null) { //On vérifie que la case est vide
                if (plateau[chosenRow][y - 1].getNum() < cardsChosenByPlayers.getNum()) {  //On vérifie que le placement du joueur est valide
                    plateau[chosenRow][y] = NumCarte.getNum();  //On ajoute la carte à l'emplacement choisi par le joueur
                    cards.remove(NumCarte.getNum()); //On retire ensuite la carte du jeu
                } else {  //Si le placement du joueur est invalide
                    System.out.println("You can't place your card here !");  //On affiche un message d'erreur
                    // Ajouter un moyen qui permet au joueur de choisir un nouvel emplacement de carte
                }
            }

        }
        cardsChosenByPlayers.remove(minList);**/
    }
}
