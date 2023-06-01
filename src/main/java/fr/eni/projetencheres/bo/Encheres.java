package fr.eni.projetencheres.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Encheres extends GregorianCalendar {
    private LocalDate dateEnchere;
    private int montantEnchere;
    private Utilisateur user;
    private ArticleVendu art;

    public Encheres(LocalDate dateEnchere, int montantEnchere, Utilisateur user, ArticleVendu art) {
        this.user = user;
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
        this.art = art;
    }

    public LocalDate getDateEnchere() {
        return dateEnchere;
    }

    public void setDateEnchere(LocalDate dateEnchere) {
        this.dateEnchere = dateEnchere;
    }

    public int getMontantEnchere() {
        return montantEnchere;
    }

    public void setMontantEnchere(int montantEnchere) {
        this.montantEnchere = montantEnchere;
    }

    public Utilisateur getUtilisateur() {
        return this.user;
    }

    public ArticleVendu getArt() {
        return art;
    }

    public void setArt(ArticleVendu art) {
        this.art = art;
    }
}
