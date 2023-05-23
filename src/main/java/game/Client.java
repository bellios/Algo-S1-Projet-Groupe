package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5555);
            System.out.println("Connecté en attente de Go");

            PrintWriter outputStream = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner sc = new Scanner(System.in);
            String input;

            do {

                do {
                    input = inputStream.readLine();
                    System.out.println(input);
                }while(! "Go".equals(input));

                System.out.print("Prochaine valeur : ");
                int result = sc.nextInt();

                String data = new Integer(result).toString();
                outputStream.println(data);
                outputStream.flush();

                input = inputStream.readLine();
                System.out.println(input);
            }while(! "Gagne".equals(input) &&  ! "Perdu".equals(input));

            inputStream.close();
            outputStream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
