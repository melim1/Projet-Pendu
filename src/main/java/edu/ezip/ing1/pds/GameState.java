package edu.ezip.ing1.pds;

import java.util.*;

public class GameState {

    private String motSecret;
    private Set<Character> lettresProposees = new HashSet<>();
    private int erreurs = 0;
    private final int MAX_ERREURS = 8;

    public GameState(String motSecret) {
        this.motSecret = motSecret;
    }

    public boolean proposerLettre(char c) {

        if (lettresProposees.contains(c)) return false;

        lettresProposees.add(c);

        if (!motSecret.contains(String.valueOf(c))) {
            erreurs++;
        }

        return true;
    }

    public String getMotMasque() {
        StringBuilder sb = new StringBuilder();

        for (char c : motSecret.toCharArray()) {
            if (lettresProposees.contains(c)) {
                sb.append(c).append(" ");
            } else {
                sb.append("_ ");
            }
        }

        return sb.toString().trim();
    }

    public String getLettresProposees() {
        StringBuilder sb = new StringBuilder();
        for (char c : lettresProposees) {
            sb.append(c).append(" ");
        }
        return sb.toString().trim();
    }

    public int getErreurs() {
        return erreurs;
    }

    public String getEtatPartie() {

        if (erreurs >= MAX_ERREURS) return "LOSE";

        boolean gagne = true;
        for (char c : motSecret.toCharArray()) {
            if (!lettresProposees.contains(c)) {
                gagne = false;
                break;
            }
        }

        if (gagne) return "WIN";

        return "PLAYING";
    }

    public boolean estTermine() {
        return !getEtatPartie().equals("PLAYING");
    }
}
