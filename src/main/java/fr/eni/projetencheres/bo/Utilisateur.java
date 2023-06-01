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
    private boolean administrateur;
    private int credit;


    public Utilisateur(String pseudo,int noUtilisateur, String nom, String prenom, int codePostal, String email, int numTel, String rue, String ville, String motDePasse, boolean administrateur, int credit) {
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

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getNoUtilisateur() {
        return noUtilisateur;
    }

    public void setNoUtilisateur(int noUtilisateur) {
        this.noUtilisateur = noUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public boolean getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(boolean administrateur) {
        this.administrateur = administrateur;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setCodePostal(String codePostal) {
    }

    public void setTelephone(int numTel) {
        this.numTel = numTel;
    }

    public int getTelephone() {
        return this.numTel;
    }

    public boolean isAdministrateur() {
        return this.administrateur;
    }
}
