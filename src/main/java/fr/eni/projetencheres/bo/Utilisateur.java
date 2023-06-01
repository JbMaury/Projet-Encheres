package fr.eni.projetencheres.bo;

public class Utilisateur {
    private String pseudo;
    private int noUtilisateur;
    private String nom;
    private String prenom;
    private String codePostal;
    private String email; //Check si email == String@String.nomDeDomaine
    private String numTel; //Check si numTel = int.int.int.int.int pour être valide
    private String rue;
    private String ville;
    private String motDePasse;
    private boolean administrateur;
    private int credit;

    /*
    *   EMPTY CONSTRUCTOR
    */
    public Utilisateur(){
        super();
    }
    /*
     *   CONSTRUCTEUR WITHOUT noUtilisateur (Before DB insert)
     * @param pseudo String
     * @param nom String
     * @param prenom String
     * @param email String
     * @param numTel String
     * @param rue String
     * @param codePostal String
     * @param ville String
     * @param motDePasse String
     * @param credit int
     * @param administrateur boolean
     */
    public Utilisateur(String pseudo,
                       String nom,
                       String prenom,
                       String email,
                       String numTel,
                       String rue,
                       String codePostal,
                       String ville,
                       String motDePasse,
                       int credit,
                       boolean administrateur) {
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numTel = numTel;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.motDePasse = motDePasse;
        this.credit = credit;
        this.administrateur = administrateur;
    }
    /*
     *   CONSTRUCTEUR WITHOUT noUtilisateur (Before DB insert)
     * @param pseudo String
     * @param nom String
     * @param prenom String
     * @param email String
     * @param numTel String
     * @param rue String
     * @param codePostal String
     * @param ville String
     * @param motDePasse String
     * @param credit int
     * @param administrateur boolean
     */
    public Utilisateur(int noUtilisateur,
                       String pseudo,
                       String nom,
                       String prenom,
                       String email,
                       String numTel,
                       String rue,
                       String codePostal,
                       String ville,
                       String motDePasse,
                       int credit,
                       boolean administrateur) {
        this.noUtilisateur = noUtilisateur;
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numTel = numTel;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.motDePasse = motDePasse;
        this.credit = credit;
        this.administrateur = administrateur;
    }
    /*
    *   Getter and Setter noUtilisateur
    */
    public int getNoUtilisateur() {
        return noUtilisateur;
    }
    public void setNoUtilisateur(int noUtilisateur) {
        this.noUtilisateur = noUtilisateur;
    }

    /*
     *   Getter and Setter pseudo
     */
    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /*
     *   Getter and Setter nom
     */
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    /*
     *   Getter and Setter prenom
     */
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /*
     *   Getter and Setter email
     */
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    /*
     *   Getter and Setter telephone
     */
    public String getNumTel() {
        return numTel;
    }
    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    /*
     *   Getter and Setter rue
     */
    public String getRue() {
        return rue;
    }
    public void setRue(String rue) {
        this.rue = rue;
    }

    /*
     *   Getter and Setter codePostal
     */
    public String getCodePostal() {
        return codePostal;
    }
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    /*
     *   Getter and Setter ville
     */
    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }

    /*
     *   Getter and Setter motDePasse
     */
    public String getMotDePasse() {
        return motDePasse;
    }
    public void setMotDePasse(String motDePasse){
        this.motDePasse = motDePasse;
    }

    /*
     *   Getter and Setter credit
     */
    public int getCredit() {
        return credit;
    }
    public void setCredit(int credit) {
        this.credit = credit;
    }

    /*
     *   Getter and Setter administrateur
     */
    public boolean getAdministrateur() {
        return administrateur;
    }
    public void setAdministrateur(boolean administrateur) {
        this.administrateur = administrateur;
    }
}
