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
}
