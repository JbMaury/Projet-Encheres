package fr.eni.projetencheres.bo;

import java.util.Date;
import java.util.GregorianCalendar;

public class Encheres extends GregorianCalendar {
    private Date dateEnchere;
    private int montantEnchere;

    public Encheres(Date dateEnchere, int montantEnchere) {
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
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
}
