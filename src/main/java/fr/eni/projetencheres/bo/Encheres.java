package fr.eni.projetencheres.bo;

import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Encheres extends GregorianCalendar {
    private Date dateEnchere;
    private int montantEnchere;

    public Encheres(Date dateEnchere, int montantEnchere) {
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
    }

    public Encheres(LocalDate now, int montantEnchere, Utilisateur user, ArticleVendu art) {
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

    public List<Encheres> getUtilisateur() {
    }
}
