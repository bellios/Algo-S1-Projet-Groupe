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

    //Créer une fonction qui permet à un joueur de placer la carte qu'il a choisie sur le plateau de jeu
    //Voir avec Bastien si c'est bien "null" et pas "'.'" cf displayPlateau qui complète le vide du plateau par des "'.'"
    public Player placeCardOnBoard() {
        Card[][] plateau = Game.getPlateau();
        System.out.println("Choose a row to put your card in");
        Scanner scanner = new Scanner(System.in);
        int ChosenRow = scanner.nextInt();  //Le joueur choisit la ligne sur laquelle il souhaite placer sa carte
        for (int i = 0; i < plateau.length; i++) { //On parcourt les lignes du plateau
            if(ChosenRow == i) {  //On check si le numéro choisi par le joueur correspond à la ligne du plateau
                for (int y = 0; y < plateau[i].length; y++) {  //On parcourt les colonnes du platea
                    if(plateau[i][y] == null) { //On vérifie que la case est vide
                        if(plateau[i][y-1].getNum() < NumCarte.getNum()) {  //On vérifie que le placement du joueur est valide
                            plateau[i][y] = NumCarte.getNum();  //On ajoute la carte à l'emplacement choisi par le joueur
                            cards.remove(NumCarte.getNum()); //On retire ensuite la carte du jeu
                        } else {  //Si le placement du joueur est invalide
                            System.out.println("Vous ne pouvez pas placer votre carte à cet endroit, choisissez un nouvel emplacement");  //On affiche un message d'erreur
                            // Ajouter un moyen qui permet au joueur de choisir un nouvel emplacement de carte
                        }
                    }
                }
            }
        }
        return null;
    }
}
