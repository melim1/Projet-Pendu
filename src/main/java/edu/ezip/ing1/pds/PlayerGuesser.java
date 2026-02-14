package edu.ezip.ing1.pds;

import java.io.*;
import java.net.*;

public class PlayerGuesser {

    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.out.println("Usage : java PlayerGuesser <ip_display> <port_display>");
            return;
        }

        String ip = args[0];
        int port = Integer.parseInt(args[1]);

        if (port == 2025) {
            System.out.println("Erreur : le port 2025 est interdit.");
            return;
        }

        PlayerGuesser pg = new PlayerGuesser();

        pg.envoyerHello(ip, port);

        BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("Proposez une lettre : ");
            String ligne = clavier.readLine();

            if (ligne == null) break;

            if (ligne.length() != 1) {
                System.out.println("Erreur : une seule lettre.");
                continue;
            }

            char lettre = ligne.charAt(0);

            if (lettre < 'a' || lettre > 'z') {
                System.out.println("Erreur : lettre minuscule attendue.");
                continue;
            }

            pg.envoyerGuess(ip, lettre);
        }
    }


    public void envoyerHello(String ip, int port) throws IOException {
        Socket socket = new Socket("localhost", 2025);
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

        writer.println("HELLO");
        writer.println(ip);
        writer.println(port);

        writer.close();
        socket.close();
    }

    public void envoyerGuess(String ip, char lettre) throws IOException {
        Socket socket = new Socket("localhost", 2025);
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

        writer.println("GUESS");
        writer.println(lettre);

        writer.close();
        socket.close();
    }
}
