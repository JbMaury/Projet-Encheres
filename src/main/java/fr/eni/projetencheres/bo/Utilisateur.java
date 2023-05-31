package fr.eni.projetencheres.bo;

public class Utilisateur {
    private String pseudo;
    private int noUtilisateur;
    private String nom;
    private String prenom;
    private int codePostal;
    private String email; //Check si email == String@String.nomDeDomaine
    private int numTel; //Check si numTel = int.int.int.int.int pour être valide
    private String rue;
    private String ville;
    private String motDePasse;
    private String administrateur;
    private int credit;


    public Utilisateur(String pseudo, int noUtilisateur, String nom, String prenom, int codePostal, String email, int numTel, String rue, String ville, String motDePasse, String administrateur, int credit) {
        this.pseudo = pseudo;
        this.noUtilisateur = noUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.codePostal = codePostal;
        this.email = email;
        this.numTel = numTel;
        this.rue = rue;
        this.ville = ville;
        this.motDePasse = motDePasse;
        this.administrateur = administrateur;
        this.credit = credit;
    }
}
