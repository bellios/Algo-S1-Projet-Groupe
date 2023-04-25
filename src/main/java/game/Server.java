package game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(5555);
			int nb = (int) (Math.random()*1000);
			Ressources ressource = new Ressources();
			System.out.println("Server lancé");
			
			Socket s1 = server.accept();
			ServerThread th1 = new ServerThread(0,s1,nb,ressource);
			System.out.println("Client 1 connecté");
			
			Socket s2 = server.accept();
			ServerThread th2 = new ServerThread(1, s2,nb,ressource);
			System.out.println("Client 2 connecté");
			
			
			th1.start();
			th2.start();
			
			th1.join();
			th2.join();
			server.close();
			System.out.println("Server arrêté");
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			System.out.println("Probème sur le Server");
		}
	}
}
