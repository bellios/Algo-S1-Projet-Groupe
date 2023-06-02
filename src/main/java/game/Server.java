package game;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
	static Game game;

	public static Game getGame() {
		return game;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int index;
		boolean multi, rep;
		do {
			System.out.println("1 : Play solo\n2 : Play in multiplayer");
			index = scanner.nextInt();
		} while (index != 1 && index != 2);
		multi = index == 2 ? true : false;
		if(multi) {
			scanner = new Scanner(System.in);
			System.out.println("1 : Connect to server\n2 : Create server");
			rep = scanner.nextInt()== 2 ? true : false;
			if(rep) {
				try {
					System.out.println("test");
					game=new Game(multi);
					ServerSocket server = new ServerSocket(5555);
					Ressources ressource = new Ressources();
					System.out.println("Server lancé");
					ArrayList<Socket> sockets = new ArrayList<>();
					ArrayList<ServerThread> threads = new ArrayList<>();
					int i = 0;
					for (Member player : game.getPlayers()) {
						if (player instanceof Player) {
							sockets.add(server.accept());
							threads.add(new ServerThread(sockets.get(i), game.getPlayers().get(i), game));
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
		}else{
			game=new Game(multi);
		}
	}
}
