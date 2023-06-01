package game;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
	static Game game;
	public void playerPlaceCard() {
		int minList = game.whoPlaysFirst();
		boolean condition=true;
		boolean validity=checkValidity(minList);
		if (validity==true) {
			do {
				condition=placeCard(minList, players.get(minList).placeCardOnBoard());
			} while (condition);
		} else collectCards(minList, players.get(minList).collectCards_Row());
		System.out.println(game.getRessources().displayPlateau());
		game.getRessources().setCardsChosen(minList, new Card(Game.NUMBER_CARDS+1,0));//On change la carte jouée sur le plateau par la carte 105
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
					System.out.println("Card placed ! \n");  //On affiche un message pour confirmer que la carte abien été placée
					game.getRessources().setPlateau(chosenRow,y,game.getRessources().getCardsChosenByPlayers().get(minList));//On ajoute la carte à l'emplacement choisi par le joueur
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
				players.get(minList).getStack().add(game.getRessources().getPlateau()[collectCardsRow][y]);
				game.getRessources().setPlateau(collectCardsRow, y, null);
			}
		}
		game.getRessources().setPlateau(collectCardsRow,0,game.getRessources().getCardsChosenByPlayers().get(minList));
	}
	public void turns(){
		for(int i=0;i<Game.NUMBER_TURNS;i++){
			game.getRessources().displayPlateau();
			for (Member player:game.getPlayers()) {
				player.displayHand();
				game.getRessources().addCardToChosenCards(player.chooseCardInHand());
			}
			game.getRessources().displayPlateau();
			//playerPlaceCard();
			game.getRessources().clearCardChosen();
		}
	}

	public static void main(String[] args) {
		game=new Game();
		if(game.isMulti()) {

			Scanner scanner = new Scanner(System.in);
			int rep;
			do {
				System.out.println("1 : Connect to server\n2 : Create server");
				rep = scanner.nextInt();
			}while(rep!=1||rep!=2);
			if(rep==2) {
				try {
					ServerSocket server = new ServerSocket(5555);
					Ressources ressource = new Ressources();
					System.out.println("Server lancé");
					ArrayList<Socket> sockets = new ArrayList<>();
					ArrayList<ServerThread> threads = new ArrayList<>();
					int i = 0;
					for (Member player : game.getPlayers()) {
						if (player instanceof Player) {
							sockets.add(server.accept());
							threads.add(new ServerThread(sockets.get(i), game, game.getPlayers().get(i)));
							System.out.println("Client " + i + " connecté");
							i++;
						}
					}
					for (ServerThread player : threads) {
						player.start();
					}
					for (ServerThread player : threads) {
						player.join();
					}
					server.close();
					System.out.println("Server arrêté");

				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
					System.out.println("Probème sur le Server");
				}
			}else {
				Client client =new Client();
				client.start();
			}

		}
	}
}
