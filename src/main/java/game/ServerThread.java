package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
    int id ;
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
    }
}
