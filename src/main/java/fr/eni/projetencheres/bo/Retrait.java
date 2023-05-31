package fr.eni.projetencheres.bo;

public class Retrait {
    private String rue;
    private int code_Postal;
    private String ville;

    public Retrait(String rue, int codePostal, String ville) {
        this.rue = rue;
        code_Postal = codePostal;
        this.ville = ville;
    }

    public Retrait(String rue, String cp, String ville, ArticleVendu article) {
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getCode_Postal() {
        return code_Postal;
    }

    public void setCode_Postal(int code_Postal) {
        this.code_Postal = code_Postal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public ArticleVendu getArticle() {
    }

    public String getCodePostal() {
    }
}
