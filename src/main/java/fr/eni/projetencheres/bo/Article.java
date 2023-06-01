package fr.eni.projetencheres.bo;

import java.time.LocalDate;
import java.util.List;

public class Article extends ArticleVendu {
    public Article(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int prixInitial, Utilisateur user, Categorie cat) {
    }

    public void setNoArticle(int noArticle) {
    }

    public void setRetrait(Retrait retrait) {
    }

    public void setEncheres(List<Encheres> encheres) {
    }

    public void setAcheteur(List<Encheres> utilisateur) {
    }

    public void setPrixVentes(int montantEnchere) {
    }

    public int getPrixInitial() {
    }
}
