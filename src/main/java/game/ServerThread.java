package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class ServerThread extends Thread {
    private Socket socket ;
    private Game game;
    private Member player;
    private BufferedReader inputStream;
    private PrintWriter outputStream;

    public ServerThread( Socket socket, Member player, Game game) {
        super();
        this.socket = socket;
        this.player=player;
        this.game=game;
    }
    public void playerPlaceCard() {
        int minList = whoPlaysFirst();
        boolean condition=true;
        boolean validity=checkValidity(minList);
        if (validity==true) {
            do {
                condition=placeCard(minList, game.getPlayers().get(minList).placeCardOnBoard());
            } while (condition);
        } else collectCards(minList, game.getPlayers().get(minList).collectCards_Row());
        System.out.println(game.getRessources().displayPlateau());
        game.getRessources().setCardsChosen(minList, new Card(Game.NUMBER_CARDS+1,0,""));//On change la carte jouée sur le plateau par la carte 105
        game.getRessources().setPlayerTurn(minList,false);
    }

    public boolean placeCard(int minList, int chosenRow){
        boolean nextLine = true;
        for (int y = 0; y < Game.PLATEAU_LENGTH && nextLine; y++) {
            if (y == Game.PLATEAU_LENGTH-1) {
                collectCards(minList, chosenRow);
            }
            if (game.getRessources().getPlateau()[chosenRow][y] == null) { //On vérifie que la case est vide
                if (game.getRessources().getPlateau()[chosenRow][y - 1] == null)nextLine=false;
                else if (game.getRessources().getPlateau()[chosenRow][y - 1].getNum() < game.getRessources().getCardsChosenByPlayers().get(minList).getNum()) {  //On vérifie que le placement du joueur est valide
                    outputStream.println("Card placed ! \n");  //On affiche un message pour confirmer que la carte abien été placée
                    game.getRessources().setPlateau(chosenRow,y,game.getRessources().getCardsChosenByPlayers().get(minList));//On ajoute la carte à l'emplacement choisi par le joueur
                    return false;
                } else {  //Si le placement du joueur est invalide
                    outputStream.println("You can't place your card here ! \n");  //On affiche un message d'erreur
                    return true;
                }
            }
        }
        return true;
    }

    public boolean checkValidity (int minList) {
        for (int x = 0; x < Game.PLATEAU_WIDTH; x++) {
            boolean nextLine = true;
            for (int y = 0; y < Game.PLATEAU_LENGTH && nextLine; y++) {
                if (null == game.getRessources().getPlateau()[x][y]) {
                    if (game.getRessources().getPlateau()[x][y - 1] == null)nextLine=false;
                    else if(game.getRessources().getPlateau()[x][y-1].getNum() < game.getRessources().getCardsChosenByPlayers().get(minList).getNum()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void collectCards(int minList, int collectCardsRow) {
        for (int y = 0; y < Game.PLATEAU_LENGTH; y++) {
            if(game.getRessources().getPlateau()[collectCardsRow][y]!=null) {
                game.getPlayers().get(minList).getStack().add(game.getRessources().getPlateau()[collectCardsRow][y]);
                game.getRessources().setPlateau(collectCardsRow, y, null);
            }
        }
        game.getRessources().setPlateau(collectCardsRow,0,game.getRessources().getCardsChosenByPlayers().get(minList));
    }
    public int whoPlaysFirst() {
        ArrayList<Integer> sortedList = new ArrayList<>();  //Créer une liste SortedList vide
        for (Card NumCarte : game.getRessources().getCardsChosenByPlayers()) {  //On parcourt tous les éléments de la liste Index et à chaque itération de boucle, la variable NumCarte prend la valeur de l'élément actuel de la liste Index
            sortedList.add(NumCarte.getNum());  //Ajoute la valeur du numéro de la carte à la liste SortedList

        }
        int minList=sortedList.indexOf(Collections.min(sortedList));
        game.getRessources().setPlayerTurn(minList,true);
        return sortedList.indexOf(Collections.min(sortedList));
    }
    public void turns(){
        for(int i=0;i<Game.NUMBER_TURNS;i++){
            outputStream.println(game.getRessources().displayPlateau());
            for (Member player:game.getPlayers()) {
                player.displayHand();
                outputStream.println("testavantchose");
                game.getRessources().addCardToChosenCards(player.chooseCardInHand());
                outputStream.println("testapreschose");
            }
            outputStream.println("testfinfor");
            outputStream.println(game.getRessources().displayPlateau());
            outputStream.println("Waiting for your turn to play");
            outputStream.flush();
            do{
                whoPlaysFirst();
            }while(!player.isTurn());
            playerPlaceCard();
            game.getRessources().clearCardChosen();
        }
    }

    public void run() {
        // Faire en sorte que les action soit prise en fonction du message reçu et non d'un go qui par tous les tour du coup ça seras full if
        try {
            inputStream = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            outputStream = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            player.setOutputStream(outputStream);
            player.setInputStream(inputStream);
            outputStream.println("Go");
            outputStream.flush();
            turns();

            /*do {
                outputStream.println("Go");
                outputStream.flush();

                readValue = new Integer(inputStream.readLine()).intValue();
                //dérouler
                if(readValue == this.nb && !this.ressource.getState(1-id)) this.ressource.setState(id);
            }while(! this.ressource.getState(id) && !this.ressource.getState(1-id));

            if(this.ressource.getState(id)) {
                outputStream.println("Gagne");
                outputStream.flush();
            }
            else {
                outputStream.println("Perdu");
                outputStream.flush();
            }

            inputStream.close();
            outputStream.close();
            this.socket.close();*/

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /*int id ;
    Socket socket ;
    Ressources ressource ;
    int nb ;
    Game game;

    public ServerThread(int i, Socket socket, Ressources ressource, Game game) {
        this.id = i;
        this.socket = socket;
        this.nb = nb ;
        this.ressource = ressource;
        this.game=game;
    }

    @Override
    public void run() {
        try {
            int readValue;

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            PrintWriter outputStream = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));

            do {
                outputStream.println("Go");
                outputStream.flush();

                readValue = new Integer(inputStream.readLine()).intValue();
                if(readValue > this.nb) {
                    outputStream.println("Plus petit");
                    outputStream.flush();
                }
                else if(readValue < this.nb) {
                    outputStream.println("Plus grand");
                    outputStream.flush();
                }
                if(readValue == this.nb && !this.ressource.getState(1-id)) this.ressource.setState(id);
            }while(! this.ressource.getState(id) && !this.ressource.getState(1-id));

            if(this.ressource.getState(id)) {
                outputStream.println("Gagne");
                outputStream.flush();
            }
            else {
                outputStream.println("Perdu");
                outputStream.flush();
            }

            inputStream.close();
            outputStream.close();
            this.socket.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }*/
}
