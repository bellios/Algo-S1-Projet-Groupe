package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {


    public void start() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the IP adress of the server");
            String rep = scanner.nextLine();

            Socket socket = new Socket(rep, 5555);
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
                input = inputStream.readLine();
                System.out.println(input);
                switch (input) {
                    case "Choose a row to put your card in":
                    case "Choose a Card Number within your hands to play":
                    case "Choose a row to collect the cards from":
                        System.out.println("testClient");
                        String data = sc.nextLine();
                        outputStream.println(data);
                        outputStream.flush();
                        break;
                }
            }while(! "Gagne".equals(input) &&  ! "Perdu".equals(input));

            inputStream.close();
            outputStream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        /*try {
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
    }*/

}
