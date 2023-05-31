package fr.eni.projetencheres.bo;

import java.util.Date;
import java.util.GregorianCalendar;

public class Encheres extends GregorianCalendar {
    private Date dateEnchere;
    private int montantEnchère;

    public Encheres(Date dateEnchere, int montantEnchère) {
        this.dateEnchere = dateEnchere;
        this.montantEnchère = montantEnchère;
    }

    public Date getDateEnchere() {
        return dateEnchere;
    }

    public void setDateEnchere(Date dateEnchere) {
        this.dateEnchere = dateEnchere;
    }

    public int getMontantEnchère() {
        return montantEnchère;
    }

    public void setMontantEnchère(int montantEnchère) {
        this.montantEnchère = montantEnchère;
    }
}
