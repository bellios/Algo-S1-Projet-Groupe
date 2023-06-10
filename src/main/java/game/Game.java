package game;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Collections;

public class Game {
    public static final int PLATEAU_WIDTH=4;
    public static final int PLATEAU_LENGTH=6;
    public static final int NUMBER_CARDS=104;
    public static final int NUMBER_TURNS=10;
    private static boolean graph = false;

    private Ressources ressources;


    public ArrayList<Member> getPlayers() {
        return ressources.getPlayers();
    }

    public Ressources getRessources() {
        return ressources;
    }

    public static void setGraph(boolean graph) {Game.graph = graph;}

//==========================================================================================================
    // Initialization
    //==========================================================================================================

    private void initializePlayers(boolean multi) {
        final int num = 9;
        ressources.getPlayers().add(new Player("Player 0"));
        Scanner scanner = new Scanner(System.in);
        int index, indexPlayer, indexIA, yesNo;
        if(multi) {
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
                ressources.getPlayers().add(new Player("Player " + i));
            }
            for (int i = 1; i < indexIA + 1; i++) {
                ressources.getPlayers().add(new Ia("IA " + i));
            }
        } else {
            do {
                System.out.println("Enter the number of IA you want");
                indexIA = scanner.nextInt();
            } while (indexIA <1||indexIA>num);
            for (int i = 1; i < indexIA + 1; i++)
                ressources.getPlayers().add(new Ia("IA " + i));
        }
    }

    private void initCardInHands() {
        for (Member a : ressources.getPlayers()) {
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
        for (Member player : ressources.getPlayers()) {
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
    /*public void playerPlaceCard() {
        int minList = whoPlaysFirst();
        boolean condition=true;
        boolean validity=checkValidity(minList);
        if (validity==true) {
            do {
                condition=easyPlaceCard(minList);
                //condition=placeCard(minList, ressources.getPlayers().get(minList).placeCardOnBoard());
            } while (condition);
        } else collectCards(minList, ressources.getPlayers().get(minList).collectCards_Row());
        System.out.println(ressources.displayPlateau());
        ressources.setCardsChosen(minList, new Card(NUMBER_CARDS+1,0, ""));//On change la carte jouée sur le plateau par la carte 105
    }

    public boolean placeCard(int minList, int chosenRow){
        boolean nextLine = true;
        for (int y = 0; y < Game.PLATEAU_LENGTH && nextLine; y++) {
            if (y == Game.PLATEAU_LENGTH-1) {
                collectCards(minList, chosenRow);
            }
            if (getRessources().getPlateau()[chosenRow][y] == null) { //On vérifie que la case est vide
                if (getRessources().getPlateau()[chosenRow][y - 1] == null)nextLine=false;
                else if (getRessources().getPlateau()[chosenRow][y - 1].getNum() < getRessources().getCardsChosenByPlayers().get(minList).getNum()) {  //On vérifie que le placement du joueur est valide
                    System.out.println("Card placed ! \n");  //On affiche un message pour confirmer que la carte abien été placée
                    getRessources().setPlateau(chosenRow,y,getRessources().getCardsChosenByPlayers().get(minList));//On ajoute la carte à l'emplacement choisi par le joueur
                    return false;
                } else {  //Si le placement du joueur est invalide
                    System.out.println("You can't place your card here ! \n");  //On affiche un message d'erreur
                    return true;
                }
            }
        }
        return true;
    }*/
    public boolean checkValidity (int minList) {
        for (int x = 0; x < Game.PLATEAU_WIDTH; x++) {
            boolean nextLine = true;
            for (int y = 0; y < Game.PLATEAU_LENGTH && nextLine; y++) {
                if (null == getRessources().getPlateau()[x][y]) {
                    if (getRessources().getPlateau()[x][y - 1] == null) nextLine=false;
                    else if (getRessources().getPlateau()[x][y-1].getNum() < getRessources().getCardsChosenByPlayers().get(minList).getNum()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void easyPlaceCard(int minLists) {
        int minList=whoPlaysFirst();
        boolean collect=true;
        int smallDiff = 105,x1=0,y1=0;
        for (int x = 0; x < Game.PLATEAU_WIDTH; x++) {
            boolean nextLine = true;
            for (int y = 0; y < Game.PLATEAU_LENGTH && nextLine; y++) {
                if (getRessources().getPlateau()[x][y] == null) {
                    if (getRessources().getPlateau()[x][y - 1] == null) nextLine = false;
                    else if (getRessources().getPlateau()[x][y - 1].getNum() < getRessources().getCardsChosenByPlayers().get(minList).getNum()) {
                        if (getRessources().getCardsChosenByPlayers().get(minList).getNum() - getRessources().getPlateau()[x][y - 1].getNum() < smallDiff) {
                            smallDiff = getRessources().getCardsChosenByPlayers().get(minList).getNum() - getRessources().getPlateau()[x][y - 1].getNum();
                            x1=x; y1=y;
                            collect=false;
                        }
                    }
                }
            }
        }
        if(collect)
            collectCards(minList, ressources.getPlayers().get(minList).collectCards_Row());
        else if (y1 == 5) {
            collectCards(minList, x1);
            getRessources().setPlateau(x1,0,getRessources().getCardsChosenByPlayers().get(minList));
        } else {
            System.out.println("Card placed ! \n");
            getRessources().setPlateau(x1,y1,getRessources().getCardsChosenByPlayers().get(minList));
            System.out.println("c : "+getRessources().getCardsChosenByPlayers().get(minList));
        }
        ressources.setCardsChosen(minList, new Card(NUMBER_CARDS+1,0, ""));//On change la carte jouée sur le plateau par la carte 105
    }

    public void collectCards(int minList, int collectCardsRow) {
        for (int y = 0; y < Game.PLATEAU_LENGTH; y++) {
            if(getRessources().getPlateau()[collectCardsRow][y]!=null) {
                ressources.getPlayers().get(minList).getStack().add(getRessources().getPlateau()[collectCardsRow][y]);
                getRessources().setPlateau(collectCardsRow, y, null);
            }
        }
        getRessources().setPlateau(collectCardsRow,0,getRessources().getCardsChosenByPlayers().get(minList));
    }

    public int whoWinFirst() {
        ArrayList<Integer> sortedList = new ArrayList<>();
        for (Member player : ressources.getPlayers()) {
            sortedList.add(player.getTotalStack());
        }
        return sortedList.indexOf(Collections.min(sortedList));
    }

    public String winning() {
        int i = 1;
        String str="";
        Iterator<Member> iterator = ressources.getPlayers().iterator();
        while (iterator.hasNext()) {
            int indexPlayer = whoWinFirst();
            str+=i + " : " + ressources.getPlayers().get(indexPlayer).getName() + " has " + ressources.getPlayers().get(indexPlayer).getTotalStack() + " points at the end of the game !\n";
            i ++;
            ressources.getPlayers().remove(indexPlayer);
        }
        return str;
    }

    public void turns(){
        for(int i=0;i<NUMBER_TURNS;i++){
            System.out.println(ressources.displayPlateau());
            ressources.getPlayers().get(0).displayHand();
            chooseCard();
            System.out.println(ressources.displayPlateau());
            for (int y=0; y<ressources.getPlayers().size();y++) {
                System.out.println("p : "+y);
                easyPlaceCard(whoWinFirst());
            }
            ressources.clearCardChosen();
        }
    }
    //==========================================================================================================
    // Game
    //==========================================================================================================
    public Game(boolean graph,ArrayList<Member> members){
        this.graph=graph;
        ressources=new Ressources();
        for(Member member:members)
            ressources.getPlayers().add(member);
        if (!graph) initializePlayers(false);
        initCardInHands();
    }
    public Game(boolean multi) {
        ressources=new Ressources();
        if (!graph) initializePlayers(multi);
        initCardInHands();
        if (!multi) {
            turns();
            System.out.println(winning());
        }
    }
}
