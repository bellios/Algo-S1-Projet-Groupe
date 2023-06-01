package game;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Game {
    public static final int PLATEAU_WIDTH=4;
    public static final int PLATEAU_LENGTH=6;
    public static final int NUMBER_CARDS=104;
    public static final int NUMBER_TURNS=10;

    private ArrayList<Member> players = new ArrayList<>();
    private boolean multi;

    private Ressources ressources;

    public boolean isMulti() {
        return multi;
    }

    public ArrayList<Member> getPlayers() {
        return players;
    }

    public Ressources getRessources() {
        return ressources;
    }
    //==========================================================================================================
    // Initialization
    //==========================================================================================================

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

    private void initCardInHands() {
        for (Member a : players) {
            for (int i = 0; i < NUMBER_TURNS; i++) {
                int num = (int) (Math.random() * (ressources.cardsSize() - 1));
                a.addCardToHand(ressources.removeCardFromDeck(num));
            }
        }
    }


    //==========================================================================================================
    // Game's mechanics
    //==========================================================================================================
    public void chooseCard() {
        for (Member player : players) {
            ressources.addCardToChosenCards(player.chooseCardInHand());
        }
    }

    //Créer une ArrayList avec juste les numéros de carte triés par ordre choisi par les joueurs (pas par ordre croisant hein) !
    public int whoPlaysFirst() {
        ArrayList<Integer> sortedList = new ArrayList<>();  //Créer une liste SortedList vide
        for (Card NumCarte : ressources.getCardsChosenByPlayers()) {  //On parcourt tous les éléments de la liste Index et à chaque itération de boucle, la variable NumCarte prend la valeur de l'élément actuel de la liste Index
            sortedList.add(NumCarte.getNum());  //Ajoute la valeur du numéro de la carte à la liste SortedList
        }
        return sortedList.indexOf(Collections.min(sortedList));
    }


    //Appelle la fonction qui permet  aux joueurs de poser leur carte au préalable sélectionnée sur le plateau
    public void playerPlaceCard() {
        int minList = whoPlaysFirst();
        boolean condition=true;
        boolean validity=checkValidity(minList);
        if (validity==true) {
            do {
                condition=placeCard(minList, players.get(minList).placeCardOnBoard());
            } while (condition);
        } else collectCards(minList, players.get(minList).collectCards_Row());
        System.out.println(ressources.displayPlateau());
        ressources.setCardsChosen(minList, new Card(NUMBER_CARDS+1,0));//On change la carte jouée sur le plateau par la carte 105
    }

    public boolean placeCard(int minList, int chosenRow){
        boolean nextLine = true;
        for (int y = 0; y < PLATEAU_LENGTH && nextLine; y++) {
            if (y == PLATEAU_LENGTH-1) {
                collectCards(minList, chosenRow);
            }
            if (ressources.getPlateau()[chosenRow][y] == null) { //On vérifie que la case est vide
                if (ressources.getPlateau()[chosenRow][y - 1] == null)nextLine=false;
                else if (ressources.getPlateau()[chosenRow][y - 1].getNum() < ressources.getCardsChosenByPlayers().get(minList).getNum()) {  //On vérifie que le placement du joueur est valide
                    System.out.println("Card placed ! \n");  //On affiche un message pour confirmer que la carte abien été placée
                    ressources.setPlateau(chosenRow,y,ressources.getCardsChosenByPlayers().get(minList));//On ajoute la carte à l'emplacement choisi par le joueur
                    return false;
                } else {  //Si le placement du joueur est invalide
                    System.out.println("You can't place your card here ! \n");  //On affiche un message d'erreur
                    return true;
                }
            }
        }
        return true;
    }

    public boolean checkValidity (int minList) {
        for (int x = 0; x < PLATEAU_WIDTH; x++) {
            boolean nextLine = true;
            for (int y = 0; y < PLATEAU_LENGTH && nextLine; y++) {
                if (null == ressources.getPlateau()[x][y]) {
                    if (ressources.getPlateau()[x][y - 1] == null)nextLine=false;
                    else if(ressources.getPlateau()[x][y-1].getNum() < ressources.getCardsChosenByPlayers().get(minList).getNum()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void collectCards(int minList, int collectCardsRow) {
        for (int y = 0; y < PLATEAU_LENGTH; y++) {
            if(ressources.getPlateau()[collectCardsRow][y]!=null) {
                players.get(minList).getStack().add(ressources.getPlateau()[collectCardsRow][y]);
                ressources.setPlateau(collectCardsRow, y, null);
            }
        }
        ressources.setPlateau(collectCardsRow,0,ressources.getCardsChosenByPlayers().get(minList));
    }

    public int whoWinFirst() {
        ArrayList<Integer> sortedList = new ArrayList<>();
        for (Member player : players) {
            sortedList.add(player.getTotalStack());
        }
        return sortedList.indexOf(Collections.min(sortedList));
    }

    public void winning() {
        int i = 1;
        for (Member player : players) {
            int indexPlayer = whoWinFirst();
            System.out.println(i + " : " + players.get(indexPlayer).getName() + " has " + players.get(indexPlayer).getStack() + " points at the end of the game !");
            i ++;
            players.remove(indexPlayer);
        }
    }

    public void turns(){
        for(int i=0;i<NUMBER_TURNS;i++){
            System.out.println(ressources.displayPlateau());
            players.get(0).displayHand();
            chooseCard();
            System.out.println(ressources.displayPlateau());
            for (int y=0; y<players.size();y++) {
                playerPlaceCard();
            }
            ressources.clearCardChosen();
        }
    }
    //==========================================================================================================
    // Game
    //==========================================================================================================
    public Game() {
        ressources=new Ressources();
        initializePlayers();
        initCardInHands();
        if (!this.multi) {
            turns();
        }
        winning();
    }
}
