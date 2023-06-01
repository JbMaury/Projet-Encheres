package fr.eni.projetencheres.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Encheres extends GregorianCalendar {
    private Date dateEnchere;
    private int montantEnchere;

    public Encheres(Utilisateur user, Date dateEnchere, int montantEnchere) {
        this.user = user;
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
    }

    public Encheres(LocalDate now, int montantEnchere, Utilisateur user, ArticleVendu art) {
        this.user = user;
    }

    public Date getDateEnchere() {
        return dateEnchere;
    }

    public void setDateEnchere(Date dateEnchere) {
        this.dateEnchere = dateEnchere;
    }

    public int getMontantEnchere() {
        return montantEnchere;
    }

    public void setMontantEnchere(int montantEnchere) {
        this.montantEnchere = montantEnchere;
    }

    public List<Utilisateur> getUtilisateur() {
        List<Utilisateur> listeUtilisateur = new ArrayList<>();
        return listeUtilisateur;
    }
}
