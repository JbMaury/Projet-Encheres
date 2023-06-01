package fr.eni.projetencheres.bo;

import java.time.LocalDate;

public class Enchere {
    private LocalDate dateEnchere;
    private int montantEnchere;
    private int noUtilisateur;
    private int noArticle;

    /*
    *   EMPTY CONSTRUCTOR
    */

    public Enchere(){
        super();
    }

    /*
     *   FULL CONSTRUCTOR
     * @param noUtilisateur int (BO Association between Enchere -> Utilisateur // DB Foreign Key -> Utilisateur)
     * @param noArticle int (BO Association between Enchere -> ArticleVendu // DB Foreign Key -> ArticleVendu)
     * @param dateEnchere LocalDate
     * @param montantEnchere int
     */

    public Enchere(int noUtilisateur, int noArticle, LocalDate dateEnchere, int montantEnchere) {
        this.noUtilisateur= noUtilisateur;
        this.noArticle = noArticle;
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
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
     *   Getter and Setter noArticle
     */
    public int getNoArticle() {
        return noArticle;
    }
    public void setNoArticle(int noArticle) {
        this.noArticle = noArticle;
    }
    /*
     *   Getter and Setter dateEnchere
     */
    public LocalDate getDateEnchere() {
        return dateEnchere;
    }
    public void setDateEnchere(LocalDate dateEnchere) {
        this.dateEnchere = dateEnchere;
    }
    /*
     *   Getter and Setter montantEnchere
     */
    public int getMontantEnchere() {
        return montantEnchere;
    }
    public void setMontantEnchere(int montantEnchere) {
        this.montantEnchere = montantEnchere;
    }

}
