package fr.eni.projetencheres.bo;

public class Retrait {
    private String rue;
    private int code_Postal;
    private String ville;

    public Retrait(String rue, int codePostal, String ville) {
        this.rue = rue;
        code_Postal = codePostal;
        this.ville = ville;
    }
}
