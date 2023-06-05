package fr.eni.projetencheres.bo;

import fr.eni.projetencheres.bll.CategorieManager;
import fr.eni.projetencheres.dal.DALException;

import java.time.LocalDate;

public class ArticleVendu {
    private int noArticle;
    private String nomArticle;
    private String description;
    private LocalDate dateDebutEncheres;
    private LocalDate dateFinEncheres;
    private int noUtilisateur;
    private int noCategorie;
    private int miseAPrix;
    private String etatVente;
    private int prixVente;


    /*
    *   EMPTY CONSTRUCTOR
    */

    public ArticleVendu() {
        super();
    }
    /*
     *   CONSTRUCTEUR WITHOUT noArticle (Before DB insert), prixVente & etatVente
     * @param nomArticle String
     * @param description String
     * @param dateDebutEncheres LocalDate
     * @param dateFinEncheres LocalDate
     * @param miseAPrix int
     * @param noUtilisateur int (BO Association between ArticleVendu -> Utilisateur // DB Foreign Key -> Utilisateur)
     * @param noCategorie int (BO Association between ArticleVendu -> Categorie // DB Foreign Key -> Categorie)
     */

    public ArticleVendu(String nomArticle,
                        String description,
                        LocalDate dateDebutEncheres,
                        LocalDate dateFinEncheres,
                        int miseAPrix,
                        int noUtilisateur,
                        int noCategorie) {
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.miseAPrix = miseAPrix;
        this.noUtilisateur = noUtilisateur;
        this.noCategorie = noCategorie;
    }
    /*
    *   CONSTRUCTEUR WITHOUT noArticle (Before DB insert)
    * @param nomArticle String
    * @param description String
    * @param dateDebutEncheres LocalDate
    * @param dateFinEncheres LocalDate
    * @param miseAPrix int
    * @param prixVente int
    * @param etatVente String
    * @param noUtilisateur int (BO Association between ArticleVendu -> Utilisateur // DB Foreign Key -> Utilisateur)
    * @param noCategorie int (BO Association between ArticleVendu -> Categorie // DB Foreign Key -> Categorie)
    */

    public ArticleVendu(String nomArticle,
                        String description,
                        LocalDate dateDebutEncheres,
                        LocalDate dateFinEncheres,
                        int miseAPrix,
                        int prixVente,
                        String etatVente,
                        int noUtilisateur,
                        int noCategorie) {
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.miseAPrix = miseAPrix;
        this.prixVente = prixVente;
        this.etatVente = etatVente;
        this.noUtilisateur = noUtilisateur;
        this.noCategorie = noCategorie;
    }
    /*
    *   CONSTRUCTEUR COMPLET
    * @param noArticle int (Id Primary Key in DB (Auto-increment))
    */

    public ArticleVendu(int noArticle,
                        String nomArticle,
                        String description,
                        LocalDate dateDebutEncheres,
                        LocalDate dateFinEncheres,
                        int miseAPrix,
                        int prixVente,
                        String etatVente,
                        int noUtilisateur,
                        int noCategorie) {
        this.noArticle = noArticle;
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.miseAPrix = miseAPrix;
        this.prixVente = prixVente;
        this.etatVente = etatVente;
        this.noUtilisateur = noUtilisateur;
        this.noCategorie = noCategorie;
    }

    /*
    * GETTER / SETTER ######################################
    * */
//      Getter et Setter noArticle

    public int getNoArticle() {
        return noArticle;
    }

    public void setNoArticle(int noArticle) {
        this.noArticle = noArticle;
    }

//    Getter et Setter nomArticle

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

//    Getter et Setter description

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    Getter et Setter dateDebutEncheres

    public LocalDate getDateDebutEncheres() {
        return dateDebutEncheres;
    }

    public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
        this.dateDebutEncheres = dateDebutEncheres;
    }

//    Getter et Setter dateFinEncheres

    public LocalDate getDateFinEncheres() {
        return dateFinEncheres;
    }

    public void setDateFinEncheres(LocalDate dateFinEncheres) {
        this.dateFinEncheres = dateFinEncheres;
    }

//    Getter et Setter miseAPrix

    public int getMiseAPrix() {
        return miseAPrix;
    }

    public void setMiseAPrix(int miseAPrix) {
        this.miseAPrix = miseAPrix;
    }

//    Getter et Setter etatVente

    public String getEtatVente() {
        return etatVente;
    }

    public void setEtatVente(String etatVente) {
        this.etatVente = etatVente;
    }

//    Getter et Setter prixVente

    public int getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }

//    Getter et Setter noUtilisateur
    public int getNoUtilisateur() {
        return noUtilisateur;
    }
    public void setNoUtilisateur(int noUtilisateur) {
        this.noUtilisateur = noUtilisateur;
    }
    //    Getter et Setter noCategorie
    public int getNoCategorie() {
        return noCategorie;
    }
    public void setNoCategorie(int noCategorie) {
        this.noCategorie = noCategorie;
    }

    public String toString() {
        CategorieManager categorieManager = new CategorieManager();
        try {
            String categorie = categorieManager.getCategorieById(noCategorie).getLibelle();
            return "Article : [" + nomArticle + "] " + " description : [" + description + " categorie : [" + categorie +
                    " Mise à prix : [" + miseAPrix + "€ Date de début : [" + dateDebutEncheres + "] + Date de fin : [" + dateFinEncheres + "] ";
        } catch (DALException e) {
            throw new RuntimeException(e);
        }

    }

}
