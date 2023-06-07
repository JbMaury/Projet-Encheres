package fr.eni.projetencheres.bo;

public class Retrait {

    private int noArticle;
    private String rue;
    private String codePostal;
    private String ville;

    /*
    *   EMPTY CONSTRUCTOR
    */
    public Retrait(){
        super();
    }
    /*
     *   FULL CONSTRUCTOR
     * @param noArticle (BO Association between Retrait -> ArticleVendu // DB Foreign Key -> ArticleVendu)
     * @param rue String
     * @param codePostal String
     * @param ville String
     */
    public Retrait(int noArticle, String rue, String codePostal, String ville) {
        this.noArticle = noArticle;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }
    /*
     *   Getter and Setter noArticle
     */
    public int getNoArticle() {
        return noArticle;
    }
    public void setNoArticle(int noArticle) {
        this.noArticle = noArticle;
    }
    /*
     *   Getter and Setter rue
     */
    public String getRue() {
        return rue;
    }
    public void setRue(String rue) {
        this.rue = rue;
    }
    /*
     *   Getter and Setter codePostal
     */
    public String getCodePostal() {
        return codePostal;
    }
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
    /*
    *   Getter and Setter ville
    */
    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }

    public String toString() {
        return "Retrait lié à l'article n°:" + noArticle + " rue :" + rue + " code Postal :" + codePostal + "ville : " + ville;
    }
}
