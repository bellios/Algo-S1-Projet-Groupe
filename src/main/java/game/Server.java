package game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {


	public static void main(String[] args) {
		Game game=new Game();
		if(game.isMulti()) {
			try {
				ServerSocket server = new ServerSocket(5555);
				Ressources ressource = new Ressources();
				System.out.println("Server lancé");
				ArrayList<Socket> sockets=new ArrayList<>();
				ArrayList<ServerThread> threads=new ArrayList<>();
				int i=0;
				for (Member player:game.getPlayers()) {
					if(player instanceof Player) {
						sockets.add(server.accept());
						threads.add(new ServerThread(i, sockets.get(i), game));
						System.out.println("Client " + i + " connecté");
						i++;
					}
				}
				for (ServerThread player:threads) {
					player.start();
				}
				for (ServerThread player:threads) {
					player.join();
				}
				server.close();
				System.out.println("Server arrêté");

			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
				System.out.println("Probème sur le Server");
			}
		}
	}
}
