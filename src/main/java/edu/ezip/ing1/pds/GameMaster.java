package edu.ezip.ing1.pds;

import java.io.*;
import java.net.*;

public class GameMaster {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage : java GameMaster <mot>");
            return;
        }

        String mot = args[0];

        GameState gameState = new GameState(mot);
        KnownDisplays knownDisplays = new KnownDisplays();

        try (ServerSocket serverSocket = new ServerSocket(2025)) {

            System.out.println("GameMaster lancé sur le port 2025");

            while (!gameState.estTermine()) {

                Socket socket = serverSocket.accept();
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String type = reader.readLine();

                if ("HELLO".equals(type)) {

                    String ip = reader.readLine();
                    int port = Integer.parseInt(reader.readLine());

                    knownDisplays.ajouter(ip, port);

                } else if ("GUESS".equals(type)) {

                    String ligne = reader.readLine();

                    if (ligne != null && ligne.length() == 1) {
                        char c = ligne.charAt(0);
                        gameState.proposerLettre(c);

                        diffuserEtat(gameState, knownDisplays);
                    }
                }

                socket.close();
            }

            System.out.println("Partie terminée.");

        } catch (IOException e) {
            System.out.println("Erreur GameMaster : " + e.getMessage());
        }
    }

    private static void diffuserEtat(GameState gameState,
                                     KnownDisplays knownDisplays) {

        for (int i = 0; i < knownDisplays.size(); i++) {

            try {

                Socket socket = new Socket();
                socket.connect(
                        new InetSocketAddress(
                                knownDisplays.getIp(i),
                                knownDisplays.getPort(i)
                        ), 1000);

                PrintWriter writer =
                        new PrintWriter(socket.getOutputStream(), true);

                DisplayMessage msg = new DisplayMessage(
                        gameState.getMotMasque(),
                        gameState.getLettresProposees(),
                        gameState.getErreurs(),
                        gameState.getEtatPartie()
                );

                writer.println(msg.toString());

                socket.close();

            } catch (IOException ignored) {
            }
        }
    }
}
