package fr.eni.projetencheres.bo;

import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class ArticleVendu {
    private int noArticle;
    private String nomArticle;
    private String description;
    private LocalDate dateDebutEncheres;
    private LocalDate dateFinEncheres;
    private Utilisateur utilisateur;
    private Categorie categorie;
    private int miseAPrix;
    private String etatVente;
    private int prixVente;


    public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int miseAPrix, String etatVente, int prixVente) {
        this.noArticle = noArticle;
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.miseAPrix = miseAPrix;
        this.etatVente = etatVente;
        this.prixVente = prixVente;
    }


    public ArticleVendu(int noArticle, String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int miseAPrix, Utilisateur utilisateur, Categorie categorie) {

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
        return description;
    }

    public void setDescription(String description) {
        description = description;
    }

    public LocalDate getDateDebutEncheres() {
        return dateDebutEncheres;
    }

    public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
        this.dateDebutEncheres = dateDebutEncheres;
    }

    public LocalDate getDateFinEncheres() {
        return dateFinEncheres;
    }

    public void setDateFinEncheres(LocalDate dateFinEncheres) {
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

    public void setEncheres(List<Encheres> encheres) {
    }
    public void ajouterEncheres(Utilisateur user, Encheres enchere) {
    }
    public void setRetrait(Retrait retrait) {
    }

    public void setAcheteur(List<Encheres> utilisateur) {
    }

    public Utilisateur getVendeur() {
        Utilisateur user = new Utilisateur();
        return user;
    }

    public Categorie getCategorie() {
        Categorie cat = new Categorie();
        return cat;
    }
}
