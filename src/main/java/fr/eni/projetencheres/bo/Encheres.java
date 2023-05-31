package fr.eni.projetencheres.bo;

import java.util.GregorianCalendar;

public class Encheres {
    private int dateEnchere extends GregorianCalendar;
    private int montantEnchère;

    public Encheres(int dateEnchere, int montantEnchère) {
        this.dateEnchere = dateEnchere;
        this.montantEnchère = montantEnchère;
    }
}
