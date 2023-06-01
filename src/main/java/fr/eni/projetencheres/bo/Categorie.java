package fr.eni.projetencheres.bo;

public class Categorie {
    private int noCategorie;
    private String libelle;

    /*
    * EMPTY CONSTRUCTOR
    */
    public Categorie() {
        super();
    }

    /*
     * FULL CONSTRUCTOR
     * @param noCategorie int
     * @param libelle
     */

    public Categorie(int noCategorie, String libelle) {
        this.noCategorie = noCategorie;
        this.libelle = libelle;
    }
    /*
    *   Getter and Setter noCategorie
    */

    public int getNoCategorie() {
        return noCategorie;
    }

    public void setNoCategorie(int noCategorie) {
        this.noCategorie = noCategorie;
    }

    /*
     *   Getter and Setter libelle
     */

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
