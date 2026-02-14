package edu.ezip.ing1.pds;

public class DisplayMessage {

    private String motMasque;
    private String lettres;
    private int erreurs;
    private String etat;

    public DisplayMessage(String motMasque, String lettres, int erreurs, String etat) {

        if (motMasque == null || motMasque.isEmpty())
            throw new IllegalArgumentException();

        if (erreurs < 0 || erreurs > 8)
            throw new IllegalArgumentException();

        if (!etat.equals("PLAYING") &&
                !etat.equals("WIN") &&
                !etat.equals("LOSE"))
            throw new IllegalArgumentException();

        this.motMasque = motMasque;
        this.lettres = lettres;
        this.erreurs = erreurs;
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "DISPLAY\n"
                + motMasque + "\n"
                + lettres + "\n"
                + erreurs + "\n"
                + etat;
    }
}
