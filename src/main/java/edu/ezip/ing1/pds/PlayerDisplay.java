package edu.ezip.ing1.pds;

import java.io.*;
import java.net.*;

public class PlayerDisplay {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage : java PlayerDisplay <port>");
            return;
        }

        int port = Integer.parseInt(args[0]);

        if (port == 2025) {
            System.out.println("Le port 2025 est interdit.");
            return;
        }

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("PlayerDisplay en attente sur le port " + port);

            boolean fin = false;

            while (!fin) {

                Socket socket = serverSocket.accept();

                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String type = reader.readLine();

                if (!"DISPLAY".equals(type)) {
                    socket.close();
                    continue;
                }

                String motMasque = reader.readLine();
                String lettres = reader.readLine();
                String erreurs = reader.readLine();
                String etat = reader.readLine();

                System.out.println("------ ETAT DU JEU ------");
                System.out.println("Mot : " + motMasque);
                System.out.println("Lettres : " + lettres);
                System.out.println("Erreurs : " + erreurs);
                System.out.println("Etat : " + etat);
                System.out.println("-------------------------");

                if ("WIN".equals(etat) || "LOSE".equals(etat)) {
                    fin = true;
                }

                socket.close();
            }

        } catch (IOException e) {
            System.out.println("Erreur PlayerDisplay : " + e.getMessage());
        }
    }
}
