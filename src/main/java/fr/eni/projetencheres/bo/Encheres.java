package fr.eni.projetencheres.bo;

import java.util.GregorianCalendar;

public class Encheres {
    private int dateEnchere extends GregorianCalendar;
    private int montantEnchère;

    public Encheres(int dateEnchere, int montantEnchère) {
        this.dateEnchere = dateEnchere;
        this.montantEnchère = montantEnchère;
    }

    public int getDateEnchere() {
        return dateEnchere;
    }

    public void setDateEnchere(int dateEnchere) {
        this.dateEnchere = dateEnchere;
    }

    public int getMontantEnchère() {
        return montantEnchère;
    }

    public void setMontantEnchère(int montantEnchère) {
        this.montantEnchère = montantEnchère;
    }
}
