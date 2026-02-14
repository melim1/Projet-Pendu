package edu.ezip.ing1.pds;

import java.io.*;
import java.net.*;

public class PlayerGuesser {

    public static void main(String[] args) {

        // 1️⃣ Vérification des arguments
        if (args.length != 3) {
            System.out.println("Usage : java PlayerGuesser <ip_gamemaster> <ip_display> <port_display>");
            return;
        }

        String ipGameMaster = args[0];
        String ipDisplay = args[1];
        int portDisplay = Integer.parseInt(args[2]);

        if (portDisplay == 2025) {
            System.out.println("Erreur : le port 2025 est interdit.");
            return;
        }

        PlayerGuesser pg = new PlayerGuesser();

        try {
            // 2️⃣ Envoi automatique du HELLO
            pg.envoyerHello(ipGameMaster, ipDisplay, portDisplay);

            BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.print("Proposez une lettre : ");
                String ligne = clavier.readLine();

                if (ligne == null) break;

                // 3️⃣ Si "_" → fin du programme
                if (ligne.equals("_")) {
                    System.out.println("Fin du PlayerGuesser.");
                    break;
                }

                if (ligne.length() != 1) {
                    System.out.println("Erreur : une seule lettre.");
                    continue;
                }

                char lettre = ligne.charAt(0);

                if (lettre < 'a' || lettre > 'z') {
                    System.out.println("Erreur : lettre minuscule attendue.");
                    continue;
                }

                // 4️⃣ Envoi du GUESS
                pg.envoyerGuess(ipGameMaster, lettre);
            }

        } catch (IOException e) {
            System.out.println("Erreur réseau : " + e.getMessage());
        }
    }

    // Envoi du HELLO
    public void envoyerHello(String ipGameMaster, String ipDisplay, int portDisplay) throws IOException {

        Socket socket = new Socket(ipGameMaster, 2025);
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

        writer.println("HELLO");
        writer.println(ipDisplay);
        writer.println(portDisplay);

        writer.close();
        socket.close();
    }

    // Envoi du GUESS
    public void envoyerGuess(String ipGameMaster, char lettre) throws IOException {

        Socket socket = new Socket(ipGameMaster, 2025);
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

        writer.println("GUESS");
        writer.println(lettre);

        writer.close();
        socket.close();
    }
}
