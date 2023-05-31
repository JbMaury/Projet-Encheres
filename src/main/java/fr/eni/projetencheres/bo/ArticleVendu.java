package fr.eni.projetencheres.bo;

import java.util.GregorianCalendar;

public class ArticleVendu {
    private int noArticle;
    private String nomArticle;
    private String Description;
    private int dateDebutEncheres extends GregorianCalendar;
    private int dateFinEncheres extends GregorianCalendar;

    private int miseAPrix;
    private String etatVente;
    private int prixVente;


    public ArticleVendu(int noArticle, String nomArticle, String description, int dateDebutEncheres, int dateFinEncheres, int miseAPrix, String etatVente, int prixVente) {
        this.noArticle = noArticle;
        this.nomArticle = nomArticle;
        Description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.miseAPrix = miseAPrix;
        this.etatVente = etatVente;
        this.prixVente = prixVente;
    }

    public int getNoArticle() {
        return noArticle;
    }

    public void setNoArticle(int noArticle) {
        this.noArticle = noArticle;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getDateDebutEncheres() {
        return dateDebutEncheres;
    }

    public void setDateDebutEncheres(int dateDebutEncheres) {
        this.dateDebutEncheres = dateDebutEncheres;
    }

    public int getDateFinEncheres() {
        return dateFinEncheres;
    }

    public void setDateFinEncheres(int dateFinEncheres) {
        this.dateFinEncheres = dateFinEncheres;
    }

    public int getMiseAPrix() {
        return miseAPrix;
    }

    public void setMiseAPrix(int miseAPrix) {
        this.miseAPrix = miseAPrix;
    }

    public String getEtatVente() {
        return etatVente;
    }

    public void setEtatVente(String etatVente) {
        this.etatVente = etatVente;
    }

    public int getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }
}
