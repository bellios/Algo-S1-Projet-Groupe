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

    public void placeCardOnBoard() {
        Card[][] plateau = Game.getPlateau();
        int chosenRow;
        do {
            Game.displayPlateau();
            System.out.println("Choose a row to put your card in");
            Scanner scanner = new Scanner(System.in);
            chosenRow = scanner.nextInt();
        } while (chosenRow <= plateau.length);
        for (int i = 0; i < plateau.length; i++) { //On parcourt les lignes du plateau
            if (chosenRow == i) {  //On check si le numéro choisi par le joueur correspond à la ligne du plateau
                for (int y = 0; y < plateau[i].length; y++) {  //On parcourt les colonnes du platea
                    if (plateau[i][y] == null) { //On vérifie que la case est vide
                        if (plateau[i][y - 1].getNum() < cardsChoosenByPlayers.getNum()) {  //On vérifie que le placement du joueur est valide
                            plateau[i][y] = NumCarte.getNum();  //On ajoute la carte à l'emplacement choisi par le joueur
                            cards.remove(NumCarte.getNum()); //On retire ensuite la carte du jeu
                        } else {  //Si le placement du joueur est invalide
                            System.out.println("You can't place your card here !");  //On affiche un message d'erreur
                            // Ajouter un moyen qui permet au joueur de choisir un nouvel emplacement de carte
                        }
                    }
                }
            }
        }
        cardsChoosenByPlayers.remove(minList);
    }
}
