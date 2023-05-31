package fr.eni.projetencheres.dal;

import java.util.List;

import fr.eni.projet.bo.Article;
import fr.eni.projet.bo.Enchere;
import fr.eni.projetencheres.bo.ArticleVendu;
import fr.eni.projetencheres.bo.Encheres;

public interface EnchereDAO {

    public void deleteEnchere(int numUtil) throws DALException;
    public void insertEnchere(Enchere enchere) throws DALException;
    public List<Encheres> selectByNoArticle (ArticleVendu art) throws DALException;

    void insertEnchere(Encheres enchere);
}